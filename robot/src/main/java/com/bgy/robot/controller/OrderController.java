package com.bgy.robot.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.bgy.robot.entity.OrderInfo;
import com.bgy.robot.service.OrderService;
import com.bgy.robot.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  Order前端控制器
 * </p>
 *
 * @author Judith
 * @since 2018-12-05
 */
@RestController
@RequestMapping("/order")
public class OrderController extends BaseController {

    @Autowired
    private OrderService orderService;

    /**
     * 根据状态获取订单列表
     * @param status
     * @return
     * @throws Exception
     */
    @PostMapping("/list")
    public JsonResult findByStatus(@RequestParam(value = "status", required = false,defaultValue = "0") Integer status,
                                   @RequestParam(value = "pageNum",required = false, defaultValue = "1") int pageNum,
                                   @RequestParam(value = "pageSize",required = false,defaultValue = "10") int pageSize) throws Exception {
        IPage<OrderInfo> orderInfos = orderService.findByStatus(status,pageNum,pageSize);
        return renderSuccess(orderInfos.getRecords());
    }

    /**
     * 根据订单id删除订单
     * 菜品被销售后，执行删除操作，删除订单
     * @param orderId
     * @return
     * @throws Exception
     */
    @PostMapping("/delete")
    public JsonResult delete(@RequestParam(value = "orderId",required = true)String orderId) throws Exception {
        orderService.deleteOrderById(orderId);
        return renderSuccess("删除成功");
    }

    /**
     * 更新订单状态
     * @return
     * @throws Exception
     */
    @PostMapping("/update-starus")
    public JsonResult update(@RequestParam(value = "orderId",required = true) String orderId,
                             @RequestParam(value = "status",required = true) Integer status) throws Exception {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setOrderId(orderId);
        orderInfo.setStatus(status);
        orderService.update(orderInfo);
        return renderSuccess("更新成功");
    }
}

