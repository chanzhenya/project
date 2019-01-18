package com.bgy.robot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bgy.robot.entity.RobotInfo;
import com.bgy.robot.enums.RobotStatus;
import com.bgy.robot.mapper.RobotInfoMapper;
import com.bgy.robot.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Judith
 * @date 2019/1/4
 */
@Service
public class RobotServiceImpl extends ServiceImpl<RobotInfoMapper, RobotInfo> implements RobotService {

    @Autowired
    private RobotInfoMapper robotInfoMapper;

    @Override
    public List<RobotInfo> findByRobotStatus(RobotStatus robotStatus) {
        List<RobotInfo> result = robotInfoMapper.findByRobotStatus(robotStatus.getCode());
        return result;
    }

    @Override
    public void updateRobot(RobotInfo robotInfo) {
        RobotInfo tmp = robotInfoMapper.findByRobotId(robotInfo.getRobotId());
        if(tmp != null) {
            robotInfoMapper.updateByRobotId(robotInfo);
        } else {
            robotInfoMapper.insert(robotInfo);

        }
    }

    @Override
    public List<RobotInfo> findAll() {
        return robotInfoMapper.findAll();
    }
}
