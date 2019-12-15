package com.atguigu.gmall.portal.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.mkl.gmall.cart.service.CartService;
import com.mkl.gmall.cart.vo.CartResponse;
import com.mkl.gmall.to.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

/**
 * 购物车
 */
@Api(tags = "购物车")
@RequestMapping("/cart")
@RestController
public class CartController {


    @Reference
    CartService cartService;

    /**
     * 返回当前添加的购物项的详细信息
     * @param skuId
     * @param cartKey
     * @param accessToken
     * @return
     */
    @ApiOperation("添加商品到购物车")
    @ApiImplicitParams(
            value = {
                    @ApiImplicitParam(name = "skuId",value = "商品skuId"),
                    @ApiImplicitParam(name = "num",value = "数量"),
                    @ApiImplicitParam(name = "cartKey",value = "离线购物车的key"),
                    @ApiImplicitParam(name = "accessToken",value = "登录后的访问令牌")
            }
    )
    @PostMapping("/add")
    public CommonResult addToCart(@RequestParam("skuId") Long skuId,
                                  @RequestParam(value = "num",defaultValue = "1") Integer num,
                                  @RequestParam(value = "cartKey",required = false) String cartKey,
                                  @RequestParam(value = "accessToken",required = false) String accessToken) throws ExecutionException, InterruptedException {

        CartResponse cartResponse = cartService.addToCart(skuId,num,cartKey,accessToken);
        return new CommonResult().success(cartResponse);
    }

    /**
     * 修改购物项数量
     * @param skuId
     * @param num
     * @param cartKey
     * @param accessToken
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @ApiOperation("更新购物车")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuId",value = "商品的skuId"),
            @ApiImplicitParam(name = "num",value = "数量",defaultValue = "1"),
            @ApiImplicitParam(name = "cartKey",value = "离线购物车key，可以没有"),
            @ApiImplicitParam(name = "accessToken",value = "登录后的访问令牌，没登录不用传")
    })
    @PostMapping("/update")
    public CommonResult updateCartItemNum(@RequestParam("skuId") Long skuId,
                                  @RequestParam(value = "num",defaultValue = "1") Integer num,
                                  @RequestParam(value = "cartKey",required = false) String cartKey,
                                  @RequestParam(value = "accessToken",required = false) String accessToken) throws ExecutionException, InterruptedException {

        CartResponse cartResponse = cartService.updateCartItem(skuId,num,cartKey,accessToken);
        return new CommonResult().success(cartResponse);
    }


    /**
     * 查看购物车
     * @param cartKey
     * @param accessToken
     * @return
     */
    @GetMapping("/list")
    public CommonResult cartList(@RequestParam(value = "cartKey",required = false) String cartKey,
                                 @RequestParam(value = "accessToken",required = false) String accessToken){


        CartResponse cartResponse = cartService.listCart(cartKey,accessToken);
        return new CommonResult().success(cartResponse);
    }


    /**
     * 删除购物项
     * @param skuId
     * @param cartKey
     * @param accessToken
     * @return
     */
    @GetMapping("/del")
    public CommonResult cartDel(@RequestParam("skuId") Long skuId,
                                @RequestParam(value = "cartKey",required = false) String cartKey,
                                @RequestParam(value = "accessToken",required = false) String accessToken){

        CartResponse cartResponse = cartService.delCartItem(skuId,cartKey,accessToken);
        return new CommonResult().success(cartResponse);
    }

    /**
     * 清理购物车
     * @param cartKey
     * @param accessToken
     * @return
     */

    @GetMapping("/clear")
    public CommonResult cartClear(
                                @RequestParam(value = "cartKey",required = false) String cartKey,
                                @RequestParam(value = "accessToken",required = false) String accessToken){

        CartResponse cartResponse = cartService.clearCart(cartKey,accessToken);
        return new CommonResult().success(cartResponse);
    }


    /**
     * 选中/不选中
     * @param skuIds  1,2,3,4
     * @param ops
     * @param cartKey
     * @param accessToken
     * @return
     */
    @ApiOperation("购物项选中/不选中")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "skuIds",value = "所有操作商品的skuId的集合，多个商品的id用,分割"),
            @ApiImplicitParam(name = "ops",value = "1：选中，0：不选中"),
            @ApiImplicitParam(name = "cartKey",value = "离线购物车key，可以没有"),
            @ApiImplicitParam(name = "accessToken",value = "登录后的访问令牌，没登录不用传")
    })
    @PostMapping("/check")
    public CommonResult cartCheck(@RequestParam("skuIds") String skuIds,
                                  @RequestParam(value = "ops",defaultValue = "1") Integer ops,
                                  @RequestParam(value = "cartKey",required = false) String cartKey,
                                  @RequestParam(value = "accessToken",required = false) String accessToken){


        CartResponse cartResponse = cartService.checkCartItems(skuIds,ops,cartKey,accessToken);
        return new CommonResult().success(cartResponse);
    }


}
