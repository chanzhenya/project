package com.bgy.device.service;

import com.bgy.device.entity.AttributeType;

import java.util.List;

public interface AttributeTypeService {

    public List<AttributeType> findAll();

    public void save(AttributeType attributeType);

    public void delete(List<AttributeType> attributeTypes) throws Exception;

    public AttributeType findById(Integer id);
}
