package com.mkl.gmall.cart.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class CartResponse implements Serializable {

    private Cart cart;//整个购物车
    private CartItem cartItem;//某项购物项
    private String cartKey;
}
