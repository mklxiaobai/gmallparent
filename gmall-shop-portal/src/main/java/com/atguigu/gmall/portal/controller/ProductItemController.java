package com.mkl.gmall.portal.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.mkl.gmall.pms.service.ProductService;
import com.mkl.gmall.to.CommonResult;
import com.mkl.gmall.to.es.EsProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadPoolExecutor;

@RestController
public class ProductItemController {

    @Reference
    ProductService productService;

    @Qualifier("mainThreadPoolExecutor")
    @Autowired
    ThreadPoolExecutor threadPoolExecutor;

    @Qualifier("otherThreadPoolExecutor")
    @Autowired
    ThreadPoolExecutor otherThreadPoolExecutor;

    /**
     * 数据库（商品的基本信息表、商品的属性表、商品的促销表）和  es(info/attr/sale)
     *
     * 查加缓存
     * 1、第一次查。肯定长。
     * @return
     */
    public EsProduct productInfo2(Long id){


        CompletableFuture.supplyAsync(()->{
            return "";
        },threadPoolExecutor).whenComplete((r,e)->{
            System.out.println("处理结果"+r);
            System.out.println("处理异常"+e);
        });
        //1、商品基本数据（名字介绍等） 100ms   异步


        //2、商品的属性数据  300ms
        new Thread(()->{
            System.out.println("查属性信息");
        }).start();

        //3、商品的营销数据  SmsService 1s 500ms
        new Thread(()->{
            System.out.println("查营销信息");
        }).start();
        //4、商品的配送数据  WuliuService 2s  700ms
        new Thread(()->{
            System.out.println("查配送信息");
        }).start();
        //5、商品的增值服务数据  SaleService  1s 1s
        new Thread(()->{
            System.out.println("查增值信息");
        }).start();

        //otherThreadPoolExecutor.submit()

        //8s  2.5s； 需要速度快。 开启异步化 最多1s，取决最长的服务调用。
        //高并发系统的优化
        //1、加缓存
        //2、开异步
        return null;
    }

    /**
     * 商品的详情
     * @param id
     * @return
     */
    @GetMapping("/item/{id}.html")
    public CommonResult productInfo(@PathVariable("id") Long id){

        EsProduct esProduct = productService.productAllInfo(id);
        return new CommonResult().success(esProduct);
    }


    @GetMapping("/item/sku/{id}.html")
    public CommonResult productSkuInfo(@PathVariable("id") Long id){

        EsProduct esProduct = productService.produSkuInfo(id);
        return new CommonResult().success(esProduct);
    }
}
