package com.bgy.device.service.impl;

import com.bgy.device.entity.TableInfo;
import com.bgy.device.mapper.TableInfoMapper;
import com.bgy.device.service.TableService;
import com.bgy.device.value.TableVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Judith
 * @date 2018/12/14
 */
@Service
public class TableServiceImpl implements TableService {

    @Autowired
    private TableInfoMapper tableMapper;

    @Override
    public List<TableVo> findAll() {
        return tableMapper.findAll();
    }

    @Override
    @Transactional
    public void updateTable(TableVo tableVo) {
        TableInfo table = new TableInfo();
        BeanUtils.copyProperties(tableVo,table);
        tableMapper.updateByPrimaryKeySelective(table);
    }

    @Override
    @Transactional
    public void delete(List<TableVo> tableVoList) {
        for (TableVo tableVo:tableVoList) {
            tableMapper.deleteByPrimaryKey(tableVo.getTableId());
        }
    }
}
