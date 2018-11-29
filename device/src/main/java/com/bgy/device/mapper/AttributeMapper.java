package com.bgy.device.mapper;

import com.bgy.device.entity.Attribute;
import com.bgy.device.utils.MyMapper;
import com.bgy.device.value.AttributeVo;

import java.util.List;

public interface AttributeMapper extends MyMapper<Attribute> {

    public List<AttributeVo> findAllContainType();
}