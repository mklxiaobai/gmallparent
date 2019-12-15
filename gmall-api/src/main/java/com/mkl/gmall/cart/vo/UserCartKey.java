package com.mkl.gmall.cart.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserCartKey {

    private boolean login;//用户是否登录
    private Long userId;//用户如果登录的id
    private String tempCartKey;//用户没有登录而且没有购物车的临时购物车key
    private String finalCartKey;//用户最终用哪个购物车
}
