package com.atguigu.gmall.search.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.mkl.gmall.constant.EsConstant;
import com.mkl.gmall.search.SearchProductService;
import com.mkl.gmall.to.es.EsProduct;
import com.mkl.gmall.vo.search.SearchParam;
import com.mkl.gmall.vo.search.SearchResponse;
import com.mkl.gmall.vo.search.SearchResponseAttrVo;
import io.searchbox.client.JestClient;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;
import io.searchbox.core.search.aggregation.ChildrenAggregation;
import io.searchbox.core.search.aggregation.MetricAggregation;
import io.searchbox.core.search.aggregation.TermsAggregation;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.search.join.ScoreMode;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.nested.NestedAggregationBuilder;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
@Component
public class SearchProductServiceImpl implements SearchProductService {
    @Autowired
    JestClient jestClient;

    @Override
    public SearchResponse searchProduct(SearchParam searchParam) {
        //1、构建检索条件
        String dsl = buildDsl(searchParam);

        log.error("商品检索的详细数据{}",dsl);


        Search search = new Search.Builder(dsl).addIndex(EsConstant.PRODUCT_ES_INDEX)
                .addType(EsConstant.PRODUCT_INFO_ES_TYPE)
                .build();

        SearchResult execute = null;
        try {
            //2、检索
            execute = jestClient.execute(search);
        } catch (IOException e) {
            e.printStackTrace();
        }

        //3、将返回的SearchResult转为SearchResponse
        SearchResponse searchResponse = buildSearchResponse(execute);


        searchResponse.setPageNum(searchParam.getPageNum());
        searchResponse.setPageSize(searchParam.getPageSize());

        return searchResponse;
    }

    /**
     * 构建检索结果
     * @param execute
     * @return
     */
    private SearchResponse buildSearchResponse(SearchResult execute) {
        SearchResponse searchResponse = new SearchResponse();

        MetricAggregation aggregations = execute.getAggregations();


        //===================以下是分析聚合品牌信息===============================
        TermsAggregation brand_agg = aggregations.getTermsAggregation("brand_agg");
        List<String> brandNames = new ArrayList<>();

        brand_agg.getBuckets().forEach((bucket)->{
            String keyAsString = bucket.getKeyAsString();
            brandNames.add(keyAsString);
        });
        SearchResponseAttrVo attrVo = new SearchResponseAttrVo();

        attrVo.setName("品牌");
        attrVo.setValue(brandNames);
        //searchResponse.setBrand();//可供选择的品牌
        searchResponse.setBrand(attrVo);
        //=====================品牌提取完成===========================


        //=============以下提取分类信息======================
        TermsAggregation category_agg = aggregations.getTermsAggregation("category_agg");
        List<String> categoryValues = new ArrayList<>();

        category_agg.getBuckets().forEach((bucket)->{
            String categoryName = bucket.getKeyAsString();
            TermsAggregation categoryId_agg = bucket.getTermsAggregation("categoryId_agg");
            String categoryId = categoryId_agg.getBuckets().get(0).getKeyAsString();

            Map<String,String> map = new HashMap<>();
            map.put("id",categoryId);
            map.put("name",categoryName);
            String cateInfo = JSON.toJSONString(map);
            categoryValues.add(cateInfo);
        });

        SearchResponseAttrVo catelog = new SearchResponseAttrVo();
        catelog.setName("分类");
        catelog.setValue(categoryValues);

        searchResponse.setCatelog(catelog);
        //==================分类信息提取完成========================


        //==================提取聚合的属性信息=============
        TermsAggregation termsAggregation = aggregations.getChildrenAggregation("attr_agg")
                .getTermsAggregation("attrName_agg");
        List<SearchResponseAttrVo> attrList = new ArrayList<>();


        termsAggregation.getBuckets().forEach((bucket)->{
            SearchResponseAttrVo vo = new SearchResponseAttrVo();
            //属性的名字
            String attrName = bucket.getKeyAsString();
            vo.setName(attrName);

            //属性的id
            TermsAggregation attrIdAgg = bucket.getTermsAggregation("attrId_agg");
            vo.setProductAttributeId(Long.parseLong(attrIdAgg.getBuckets().get(0).getKeyAsString()));

            //属性的所涉及的所有值
            TermsAggregation attrValueAgg = bucket.getTermsAggregation("attrValue_agg");
            List<String> valuesList = new ArrayList<>();
            attrValueAgg.getBuckets().forEach((vauleBucket)->{
                valuesList.add(vauleBucket.getKeyAsString());
            });
            vo.setValue(valuesList);
            attrList.add(vo);
        });

        searchResponse.setAttrs(attrList);
        //==================提取聚合的属性信息完成=============


        //===============提取检索到的商品数据=========================
        List<SearchResult.Hit<EsProduct, Void>> hits = execute.getHits(EsProduct.class);
        List<EsProduct> esProducts = new ArrayList<>();
//        searchResponse.setProducts();//将查到的记录分封装
        hits.forEach((hit)->{
            EsProduct source = hit.source;
            //提取到高亮结果
            String title = hit.highlight.get("skuProductInfos.skuTitle").get(0);
            //设置高亮结果
            source.setName(title);
            esProducts.add(source);
        });
        searchResponse.setProducts(esProducts);
        //==============提取检索到的商品数据完成============






        searchResponse.setTotal(execute.getTotal());



        return searchResponse;
    }

    /**
     * 构建检索DSL语句
     * @param searchParam
     * @return
     */
    private String buildDsl(SearchParam searchParam) {

        SearchSourceBuilder builder = new SearchSourceBuilder();

        BoolQueryBuilder boolQuery = QueryBuilders.boolQuery();
        //1、查询
        //1.1）、检索
        if(!StringUtils.isEmpty(searchParam.getKeyword())){
            MatchQueryBuilder matchQuery = QueryBuilders.matchQuery("skuProductInfos.skuTitle", searchParam.getKeyword());
            NestedQueryBuilder nestedQuery = QueryBuilders.nestedQuery("skuProductInfos", matchQuery, ScoreMode.None);
            boolQuery.must(nestedQuery);
        }

        //1.2）、过滤
        if(searchParam.getCatelog3()!=null&&searchParam.getCatelog3().length>0){
            //按照三级分类的条件过滤
            boolQuery.filter(QueryBuilders.termsQuery("productCategoryId",searchParam.getCatelog3()));
        }
        if(searchParam.getBrand()!=null&&searchParam.getBrand().length>0){
            //按照品牌的条件过滤
            boolQuery.filter(QueryBuilders.termsQuery("brandName.keyword",searchParam.getBrand()));
        }
        if(searchParam.getProps()!=null&&searchParam.getProps().length>0){
            //按照所有的筛选属性进行过滤
            String[] props = searchParam.getProps();
            for (String prop : props) {
                String[] split = prop.split(":");
                //2:4g-3g； 2号属性的值是4g或者3g
                BoolQueryBuilder must = QueryBuilders.boolQuery()
                        .must(QueryBuilders.matchQuery("attrValueList.productAttributeId", split[0]))
                        .must(QueryBuilders.termsQuery("attrValueList.value.keyword", split[1].split("-")));
                NestedQueryBuilder query = QueryBuilders.nestedQuery("attrValueList", must, ScoreMode.None);
                boolQuery.filter(query);
            }
        }
        if(searchParam.getPriceFrom()!=null || searchParam.getPriceTo()!=null){
            //价格区间过滤
            RangeQueryBuilder rangeQuery = QueryBuilders.rangeQuery("price");
            if(searchParam.getPriceFrom()!=null){
                rangeQuery.gte(searchParam.getPriceFrom());
            }
            if(searchParam.getPriceTo()!=null){
                rangeQuery.lte(searchParam.getPriceTo());
            }
            boolQuery.filter(rangeQuery);
        }


        //1.2.1)、按照属性过滤、按照品牌过滤、按照分类过滤



        builder.query(boolQuery);




        //2、高亮
        if(!StringUtils.isEmpty(searchParam.getKeyword())){
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("skuProductInfos.skuTitle");
            highlightBuilder.preTags("<b style='color:red'>");
            highlightBuilder.postTags("</b>");
            builder.highlighter(highlightBuilder);
        }


        //3、聚合
        //按照品牌的
        TermsAggregationBuilder brand_agg = AggregationBuilders.terms("brand_agg").field("brandName.keyword");
        brand_agg.subAggregation(AggregationBuilders.terms("brandId").field("brandId"));
        builder.aggregation(brand_agg);


        //按照分类的
        TermsAggregationBuilder category_agg = AggregationBuilders.terms("category_agg").field("productCategoryName.keyword");
        category_agg.subAggregation(AggregationBuilders.terms("categoryId_agg").field("productCategoryId"));
        builder.aggregation(category_agg);

        //按照属性的
        NestedAggregationBuilder attr_agg = AggregationBuilders.nested("attr_agg", "attrValueList");
        TermsAggregationBuilder attrName_agg = AggregationBuilders.terms("attrName_agg").field("attrValueList.name");
        //聚合看attrValue的值
        attrName_agg.subAggregation(AggregationBuilders.terms("attrValue_agg").field("attrValueList.value.keyword"));
        //聚合看attrId的值
        attrName_agg.subAggregation(AggregationBuilders.terms("attrId_agg").field("attrValueList.productAttributeId"));
        attr_agg.subAggregation(attrName_agg);
        builder.aggregation(attr_agg);


        //4、分页
        builder.from((searchParam.getPageNum()-1)*searchParam.getPageSize());
        builder.size(searchParam.getPageSize());

        //5、排序
        if(!StringUtils.isEmpty(searchParam.getOrder())){
            //0：综合排序  1：销量  2：价格
            //order=1:asc
            String order = searchParam.getOrder();
            String[] split = order.split(":");
            if(split[0].equals("0")){
                //综合排序，默认顺序
            }
            if(split[0].equals("1")){
                //销量
                FieldSortBuilder sale = SortBuilders.fieldSort("sale");
                if(split[1].equalsIgnoreCase("asc")){
                    sale.order(SortOrder.ASC);
                }else{
                    sale.order(SortOrder.DESC);
                }
                builder.sort(sale);
            }
            if(split[0].equals("2")){
                FieldSortBuilder price = SortBuilders.fieldSort("price");
                if(split[1].equalsIgnoreCase("asc")){
                    price.order(SortOrder.ASC);
                }else{
                    price.order(SortOrder.DESC);
                }
                builder.sort(price);
            }

        }




        return builder.toString();
    }
}
