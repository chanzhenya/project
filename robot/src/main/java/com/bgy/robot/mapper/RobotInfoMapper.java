package com.bgy.robot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bgy.robot.entity.RobotInfo;

import java.util.List;

/**
 * @author Judith
 * @date 2019/1/4
 */
public interface RobotInfoMapper extends BaseMapper<RobotInfo> {

    public RobotInfo findByRobotId(String robotId);

    public List<RobotInfo> findByRobotStatus(Integer status);

    public void updateByRobotId(RobotInfo robotInfo);

    public List<RobotInfo> findAll();
}
