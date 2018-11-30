package com.bgy.device.service.impl;

import com.bgy.device.entity.Attribute;
import com.bgy.device.mapper.AttributeMapper;
import com.bgy.device.service.AttributeService;
import com.bgy.device.value.AttributeVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AttributeServiceImpl implements AttributeService {

    @Autowired
    private AttributeMapper attributeMapper;

    @Override
    public List<AttributeVo> findAllDetail() {
        return attributeMapper.findAllContainType();
    }

    @Override
    public List<Attribute> findAll() {
        return attributeMapper.selectAll();
    }

    @Override
    public void save(Attribute attribute) {
        if(attribute.getAttributeId() != null) {
            attributeMapper.updateByPrimaryKeySelective(attribute);
        } else {
            attributeMapper.insert(attribute);
        }
    }

    @Override
    public void delete(List<Integer> ids) throws Exception {
        for(Integer id:ids) {
            attributeMapper.deleteByPrimaryKey(id);
        }
    }
}
