package com.bgy.device.service;

import com.bgy.device.entity.Attribute;
import com.bgy.device.value.AttributeVo;

import java.util.List;

public interface AttributeService {

    public List<AttributeVo> findAllDetail();

    public List<Attribute> findAll();

    public void save(Attribute attribute);

    public void delete(List<Integer> attributes) throws Exception;

}
