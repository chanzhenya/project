package com.bgy.robot.entity;

import com.bgy.robot.enums.RobotStatus;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Judith
 * @date 2019/1/4
 */
@Data
public class RobotInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String robotId;

    private Integer robotStatus = RobotStatus.WAITING.getCode();
}
