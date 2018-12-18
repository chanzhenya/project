package com.bgy.device.service;

import com.bgy.device.entity.DishProductRelation;

import java.util.List;

/**
 * @author Judith
 * @date 2018/12/13
 */
public interface DishService {

    public List<DishProductRelation> findAll();

    public void updateDish(DishProductRelation relation);

    public void deleteDish(List<DishProductRelation> relationList);
}
