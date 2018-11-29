package com.bgy.device.service.impl;

import com.bgy.device.entity.Attribute;
import com.bgy.device.service.AttributeService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AttributeServiceImplTest {

    @Autowired
    private AttributeService attributeService;

    @Test
    public void findAll() {

    }
}