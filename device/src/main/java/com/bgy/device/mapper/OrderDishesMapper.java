package com.bgy.device.mapper;

import com.bgy.device.entity.OrderDishes;
import com.bgy.device.utils.MyMapper;

public interface OrderDishesMapper extends MyMapper<OrderDishes> {

    public void truncateTable();
}