package com.bgy.robot.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Judith
 * @since 2018-12-05
 */
@Data
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String orderId;

    private String tableNo;

    private String dishId;

    private String dishName;

    private Integer status;

    private LocalDateTime createTime;
}
