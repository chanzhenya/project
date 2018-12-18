package com.bgy.device.mapper;

import com.bgy.device.entity.TableInfo;
import com.bgy.device.utils.MyMapper;
import com.bgy.device.value.TableVo;

import java.util.List;

public interface TableInfoMapper extends MyMapper<TableInfo> {

    public List<TableVo> findAll();

    public void deleteAll();
}