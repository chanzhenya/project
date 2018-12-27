package com.czy.sell.service;

import com.czy.sell.dto.OrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author Judith
 * @date 2018/12/24
 */
public interface OrderService {

    /**
     * 创建订单
     */
    public OrderDTO create(OrderDTO orderDTO);

    /**
     * 查询单个订单
     */
    public OrderDTO findOne(String orderId);

    /**
     * 查询订单列表
     */
    public Page<OrderDTO> findList(String buyerOpenid, Pageable pageable);

    /**
     * 取消订单
     */
    public OrderDTO cancel(OrderDTO orderDTO);

    /**
     * 完结订单
     */
    public OrderDTO finish(OrderDTO orderDTO);

    /**
     * 支付订单
     */
    public OrderDTO pay(OrderDTO orderDTO);
}
