package com.czy.sell.dto;

import com.czy.sell.entity.OrderDetail;
import com.czy.sell.enums.OrderStatusEnum;
import com.czy.sell.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Judith
 * @date 2018/12/24
 */
@Data
public class OrderDTO {

    /** 订单id */
    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家电话
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    private String buyerOpenid;

    /**
     * 订单总金额
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为新下单
     */
    private Integer orderStatus;

    /**
     * 支付状态, 默认未支付
     */
    private Integer payStatus;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date updateTime;

    private List<OrderDetail> orderDetailList = new ArrayList<>();

}
