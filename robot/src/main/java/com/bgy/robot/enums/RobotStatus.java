package com.bgy.robot.enums;

import lombok.Getter;

/**
 * @author Judith
 * @date 2019/1/4
 */
@Getter
public enum RobotStatus {
    WAITING("空闲",0),

    WORKING("任务中",1);

    private String message;

    private Integer code;

    RobotStatus(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
