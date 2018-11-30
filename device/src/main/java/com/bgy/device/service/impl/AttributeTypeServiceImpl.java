package com.bgy.device.service.impl;

import com.bgy.device.entity.AttributeType;
import com.bgy.device.mapper.AttributeTypeMapper;
import com.bgy.device.service.AttributeTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AttributeTypeServiceImpl implements AttributeTypeService {

    @Autowired
    private AttributeTypeMapper attributeTypeMapper;

    @Override
    public List<AttributeType> findAll() {
        return attributeTypeMapper.selectAll();
    }

    @Override
    public void save(AttributeType attributeType) {
        if(attributeType.getAttributeTypeId() != null) {
            attributeTypeMapper.updateByPrimaryKeySelective(attributeType);
        } else {
            attributeTypeMapper.insert(attributeType);
        }
    }

    @Override
    public void delete(List<AttributeType> attributeTypes) throws Exception {
        for (AttributeType attributeType:attributeTypes) {
            attributeTypeMapper.deleteByPrimaryKey(attributeType);
        }
    }

    @Override
    public AttributeType findById(Integer id) {
        return attributeTypeMapper.selectByPrimaryKey(id);
    }
}
