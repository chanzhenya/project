package com.bgy.device.mapper;

import com.bgy.device.entity.CookDishes;
import com.bgy.device.utils.MyMapper;

public interface CookDishesMapper extends MyMapper<CookDishes> {

    public void deleteByState();
}