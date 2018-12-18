package com.bgy.device.value;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Judith
 * @date 2018/12/13
 */
@Data
public class PageData implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long total;

    private Object result;

    public PageData(Long total, Object result) {
        this.total = total;
        this.result = result;
    }
}
