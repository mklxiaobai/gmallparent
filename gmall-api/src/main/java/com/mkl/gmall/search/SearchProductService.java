package com.mkl.gmall.search;

import com.mkl.gmall.vo.search.SearchParam;
import com.mkl.gmall.vo.search.SearchResponse;
import com.mkl.gmall.vo.search.SearchResponse;

/**
 * 商品检索服务
 */
public interface SearchProductService {
    SearchResponse searchProduct(SearchParam searchParam);
}
