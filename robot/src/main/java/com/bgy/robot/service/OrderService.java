package com.bgy.robot.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bgy.robot.entity.OrderInfo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Judith
 * @since 2018-12-05
 */
public interface OrderService extends IService<OrderInfo> {

    public IPage<OrderInfo> findByStatus(Integer status, Integer pageNum, Integer pageSize) throws Exception;

    public void deleteOrderById(String id) throws Exception;

    public void update(OrderInfo orderInfo) throws Exception;

    public void insert(OrderInfo orderInfo) throws Exception;
}
