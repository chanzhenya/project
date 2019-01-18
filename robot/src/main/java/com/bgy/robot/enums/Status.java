package com.bgy.robot.enums;

import lombok.Getter;

@Getter
public enum Status {
    //初始化，退菜
    CATERING("配餐中",1),
    ON_THE_PLATE("放盘",2),
    IN_TRANSIT("运输中",3),
    USER_VALIDATION("用户确认",4);


    private String message;

    private Integer code;

    Status(String message, Integer code) {
        this.message = message;
        this.code = code;
    }
}
