package com.bgy.device.mapper;

import com.bgy.device.value.AttributeVo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttributeMapperTest {

    @Autowired
    private AttributeMapper attributeMapper;

    @Test
    public void findAllContainType() {
        List<AttributeVo> list = attributeMapper.findAllContainType();
        Assert.assertNotEquals(0,list.size());
    }
}