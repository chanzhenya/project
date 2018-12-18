package com.bgy.device.mapper;

import com.bgy.device.entity.DishProductRelation;
import com.bgy.device.utils.MyMapper;

import java.util.List;

public interface DishProductRelationMapper extends MyMapper<DishProductRelation> {

    public List<DishProductRelation> findByDishId(String dishId);

    public void deleteAll();
}