package com.bgy.robot.enums;

import lombok.Getter;

/**
 * @author Judith
 * @date 2019/1/4
 */
@Getter
public enum RobotStatus {
    WAITING("原地等待中...",1),

    WORKING("接待客户中...",0);

    private String message;

    private Integer code;

    RobotStatus(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
