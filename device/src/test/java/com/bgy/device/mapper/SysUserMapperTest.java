package com.bgy.device.mapper;

import com.bgy.device.entity.SysUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author Judith
 * @date 2019/1/17 19:53
 * @description
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SysUserMapperTest {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Test
    public void findByUsername() {
        SysUser sysUser = sysUserMapper.findByUsername("admin");
        Assert.assertNotNull(sysUser);
    }
}