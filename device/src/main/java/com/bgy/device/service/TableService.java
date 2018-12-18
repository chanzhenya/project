package com.bgy.device.service;

import com.bgy.device.value.TableVo;

import java.util.List;

/**
 * @author Judith
 * @date 2018/12/14
 */
public interface TableService {

    public List<TableVo> findAll();

    public void updateTable(TableVo tableVo);

    public void delete(List<TableVo> tableVoList);
}
