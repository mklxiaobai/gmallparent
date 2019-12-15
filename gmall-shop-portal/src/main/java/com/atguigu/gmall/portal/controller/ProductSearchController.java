package com.atguigu.gmall.portal.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.mkl.gmall.search.SearchProductService;
import com.mkl.gmall.vo.search.SearchParam;
import com.mkl.gmall.vo.search.SearchResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 商品检索的controller
 */
@Api(tags = "检索功能")
@RestController
public class ProductSearchController {


    @Reference
    SearchProductService searchProductService;


    @ApiOperation("商品检索")
    @GetMapping("/search")
    public SearchResponse productSearchResponse(SearchParam searchParam){

        /**
         * 检索商品
         */
        SearchResponse searchResponse = searchProductService.searchProduct(searchParam);

        return searchResponse;
    }

}
