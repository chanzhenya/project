package com.bgy.device.service.impl;

import com.bgy.device.entity.DeviceAndDish;
import com.bgy.device.entity.DishProductRelation;
import com.bgy.device.mapper.DeviceAndDishMapper;
import com.bgy.device.mapper.DishProductRelationMapper;
import com.bgy.device.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Judith
 * @date 2018/12/13
 */
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishProductRelationMapper dishMapper;

    @Override
    public List<DishProductRelation> findAll() {
        return dishMapper.selectAll();
    }

    @Override
    public void updateDish(DishProductRelation relation) {
        dishMapper.updateByPrimaryKeySelective(relation);
    }

    @Override
    public void deleteDish(List<DishProductRelation> relationList) {
        for (DishProductRelation relation:relationList) {
            dishMapper.deleteByPrimaryKey(relation);
        }
    }
}
