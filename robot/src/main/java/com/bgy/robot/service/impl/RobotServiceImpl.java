package com.bgy.robot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bgy.robot.entity.RobotInfo;
import com.bgy.robot.enums.RobotStatus;
import com.bgy.robot.mapper.RobotInfoMapper;
import com.bgy.robot.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Judith
 * @date 2019/1/4
 */
@Service
public class RobotServiceImpl extends ServiceImpl<RobotInfoMapper, RobotInfo> implements RobotService {

    @Autowired
    private RobotInfoMapper robotInfoMapper;

    @Override
    public RobotInfo findByRobotStatus(RobotStatus robotStatus) {
        RobotInfo robotInfo = new RobotInfo();
        robotInfo.setRobotStatus(robotStatus.getCode());
        QueryWrapper<RobotInfo> queryWrapper = new QueryWrapper<>(robotInfo);
        RobotInfo result = robotInfoMapper.selectOne(queryWrapper);
        return result;
    }

    @Override
    public void updateRobot(RobotInfo robotInfo) {
        robotInfoMapper.updateById(robotInfo);
    }
}
