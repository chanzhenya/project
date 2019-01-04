package com.bgy.robot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bgy.robot.entity.DeleteLog;
import com.bgy.robot.entity.OrderInfo;
import com.bgy.robot.mapper.DeleteLogMapper;
import com.bgy.robot.mapper.OrderMapper;
import com.bgy.robot.service.OrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bgy.robot.utils.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Judith
 * @since 2018-12-05
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, OrderInfo> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private DeleteLogMapper deleteLogMapper;

    @Override
    public IPage<OrderInfo> findByStatus(Integer status,Integer pageNum,Integer pageSize) throws Exception {
        Page<OrderInfo> page = new Page<>(pageNum,pageSize);
        OrderInfo orderInfo = new OrderInfo();
        if(status != 0) {
            orderInfo.setStatus(status);
        }
        QueryWrapper<OrderInfo> orderInfoQueryWrapper = new QueryWrapper<>(orderInfo);
        IPage<OrderInfo> iPage = orderMapper.selectPage(page,orderInfoQueryWrapper);
        return iPage;
    }

    @Override
    @Transactional
    public void deleteOrderById(String id) throws Exception {
        OrderInfo order = orderMapper.findById(id);
        insertDeleteLog(order);
        orderMapper.deleteOrderById(id);
    }

    @Override
    @Transactional
    public void update(OrderInfo orderInfo) throws Exception {
        orderMapper.updateStatus(orderInfo);
    }

    @Override
    public void insert(OrderInfo orderInfo) throws Exception {
        orderMapper.insert(orderInfo);
    }

    private void insertDeleteLog(OrderInfo order) {
        DeleteLog deleteLog = new DeleteLog();
        deleteLog.setLogId(String.valueOf(IdUtil.genItemId()));
        deleteLog.setOrderId(order.getOrderId());
        deleteLog.setStatus(order.getStatus());
        deleteLog.setDishName(order.getDishName());
        deleteLog.setDishId(order.getDishId());
        deleteLog.setTableNo(order.getTableNo());
        deleteLog.setDeleteTime(LocalDateTime.now());

        deleteLogMapper.insert(deleteLog);
    }
}
