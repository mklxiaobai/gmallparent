package com.mkl.gmall.oms.service.impl;

import com.mkl.gmall.oms.entity.CartItem;
import com.mkl.gmall.oms.mapper.CartItemMapper;
import com.mkl.gmall.oms.service.CartItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 购物车表 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
@Service
public class CartItemServiceImpl extends ServiceImpl<CartItemMapper, CartItem> implements CartItemService {

}
