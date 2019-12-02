package com.mkl.gmall.oms.service.impl;

import com.mkl.gmall.oms.entity.Order;
import com.mkl.gmall.oms.mapper.OrderMapper;
import com.mkl.gmall.oms.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author mkl
 * @since 2019-11-28
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

}
