package com.mkl.gmall.constant;

/**
 * @author 马锴梁
 * @version 1.0
 * @date 2019/12/14 10:55
 */
public class CartConstant {
    public final static String TEMP_CART_KEY_PREFIX = "cart:temp:";//后面加cartKey
    public final static String USER_CART_KEY_PREFIX = "cart:user:";//后面加用户id
    public final static String CART_CHECKED_KEY = "checked";//购物车在redis中存储哪些被选中用的key
}
