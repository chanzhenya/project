package com.bgy.robot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bgy.robot.entity.OrderInfo;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Judith
 * @since 2018-12-05
 */
public interface OrderMapper extends BaseMapper<OrderInfo> {

    public OrderInfo findById(String orderId);

    public List<OrderInfo> findByStatus(Integer status);

    public void updateStatus(OrderInfo orderInfo);

    public void deleteOrderById(String orderId);

    public List<OrderInfo> findAll();
}


