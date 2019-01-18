package com.bgy.robot.service;

import com.bgy.robot.entity.RobotInfo;
import com.bgy.robot.enums.RobotStatus;

import java.util.List;

/**
 * @author Judith
 * @date 2019/1/4
 */
public interface RobotService {

    public List<RobotInfo> findByRobotStatus(RobotStatus robotStatus);

    public void updateRobot(RobotInfo robotInfo);

    public List<RobotInfo> findAll();
}
