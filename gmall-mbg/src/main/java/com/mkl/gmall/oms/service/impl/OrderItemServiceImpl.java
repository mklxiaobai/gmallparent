package com.mkl.gmall.oms.service.impl;

import com.mkl.gmall.oms.entity.OrderItem;
import com.mkl.gmall.oms.mapper.OrderItemMapper;
import com.mkl.gmall.oms.service.OrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单中所包含的商品 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
@Service
public class OrderItemServiceImpl extends ServiceImpl<OrderItemMapper, OrderItem> implements OrderItemService {

}
