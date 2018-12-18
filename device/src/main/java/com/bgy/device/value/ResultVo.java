package com.bgy.device.value;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultVo implements Serializable {

    private Integer code;

    private String msg;

    private Object data;
}
