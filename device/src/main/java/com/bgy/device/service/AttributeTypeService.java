package com.bgy.device.service;

import com.bgy.device.entity.AttributeType;

import java.util.List;

public interface AttributeTypeService {

    public List<AttributeType> findAll();

    public void save(AttributeType attributeType);

    public boolean delete(List<AttributeType> attributeTypes);

    public AttributeType findById(Integer id);
}
