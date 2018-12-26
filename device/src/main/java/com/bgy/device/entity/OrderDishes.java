package com.bgy.device.entity;

import javax.persistence.*;

@Table(name = "order_dishes")
public class OrderDishes {
    @Id
    private String id;

    /**
     * 菜品编号
     */
    @Column(name = "productId")
    private String productid;

    @Column(name = "dishId")
    private String dishid;

    /**
     * 订单编号
     */
    @Column(name = "orderId")
    private String orderid;

    /**
     * 餐桌编号
     */
    @Column(name = "tableId")
    private String tableid;

    /**
     * 分店编号
     */
    @Column(name = "branchId")
    private String branchid;

    /**
     * 创建时间
     */
    @Column(name = "createTime")
    private String createtime;

    /**
     * 结束时间
     */
    @Column(name = "finishTime")
    private String finishtime;

    /**
     * 状态。
0，待分发；
10，已分发，待发盘；
20，已发盘，待烹饪；
30，烹饪中；
40，烹饪完成运输中；
50，上菜中；
60，云轨运输中；
70，下菜中；
80，上餐桌
     */
    private Integer state;

    /**
     * 删除标志;默认为0；为1时代表逻辑删除
     */
    @Column(name = "deleteFlag")
    private Integer deleteflag;

    /**
     * 炒锅ID
     */
    @Column(name = "potId")
    private String potid;

    /**
     * 盘子ID
     */
    @Column(name = "RFId")
    private String rfid;

    /**
     * 倍速链ID
     */
    @Column(name = "belitId")
    private String belitid;

    /**
     * 状态更新时间
     */
    @Column(name = "updateTime")
    private String updatetime;

    /**
     * 同类炒锅个数
     */
    @Column(name = "potNumber")
    private Integer potnumber;

    /**
     * 收到订单时间
     */
    private String time0;

    /**
     * 发盘时间
     */
    private String time1;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 获取菜品编号
     *
     * @return productId - 菜品编号
     */
    public String getProductid() {
        return productid;
    }

    /**
     * 设置菜品编号
     *
     * @param productid 菜品编号
     */
    public void setProductid(String productid) {
        this.productid = productid;
    }

    /**
     * @return dishId
     */
    public String getDishid() {
        return dishid;
    }

    /**
     * @param dishid
     */
    public void setDishid(String dishid) {
        this.dishid = dishid;
    }

    /**
     * 获取订单编号
     *
     * @return orderId - 订单编号
     */
    public String getOrderid() {
        return orderid;
    }

    /**
     * 设置订单编号
     *
     * @param orderid 订单编号
     */
    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    /**
     * 获取餐桌编号
     *
     * @return tableId - 餐桌编号
     */
    public String getTableid() {
        return tableid;
    }

    /**
     * 设置餐桌编号
     *
     * @param tableid 餐桌编号
     */
    public void setTableid(String tableid) {
        this.tableid = tableid;
    }

    /**
     * 获取分店编号
     *
     * @return branchId - 分店编号
     */
    public String getBranchid() {
        return branchid;
    }

    /**
     * 设置分店编号
     *
     * @param branchid 分店编号
     */
    public void setBranchid(String branchid) {
        this.branchid = branchid;
    }

    /**
     * 获取创建时间
     *
     * @return createTime - 创建时间
     */
    public String getCreatetime() {
        return createtime;
    }

    /**
     * 设置创建时间
     *
     * @param createtime 创建时间
     */
    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    /**
     * 获取结束时间
     *
     * @return finishTime - 结束时间
     */
    public String getFinishtime() {
        return finishtime;
    }

    /**
     * 设置结束时间
     *
     * @param finishtime 结束时间
     */
    public void setFinishtime(String finishtime) {
        this.finishtime = finishtime;
    }

    /**
     * 获取状态。
0，待分发；
10，已分发，待发盘；
20，已发盘，待烹饪；
30，烹饪中；
40，烹饪完成运输中；
50，上菜中；
60，云轨运输中；
70，下菜中；
80，上餐桌
     *
     * @return state - 状态。
0，待分发；
10，已分发，待发盘；
20，已发盘，待烹饪；
30，烹饪中；
40，烹饪完成运输中；
50，上菜中；
60，云轨运输中；
70，下菜中；
80，上餐桌
     */
    public Integer getState() {
        return state;
    }

    /**
     * 设置状态。
0，待分发；
10，已分发，待发盘；
20，已发盘，待烹饪；
30，烹饪中；
40，烹饪完成运输中；
50，上菜中；
60，云轨运输中；
70，下菜中；
80，上餐桌
     *
     * @param state 状态。
0，待分发；
10，已分发，待发盘；
20，已发盘，待烹饪；
30，烹饪中；
40，烹饪完成运输中；
50，上菜中；
60，云轨运输中；
70，下菜中；
80，上餐桌
     */
    public void setState(Integer state) {
        this.state = state;
    }

    /**
     * 获取删除标志;默认为0；为1时代表逻辑删除
     *
     * @return deleteFlag - 删除标志;默认为0；为1时代表逻辑删除
     */
    public Integer getDeleteflag() {
        return deleteflag;
    }

    /**
     * 设置删除标志;默认为0；为1时代表逻辑删除
     *
     * @param deleteflag 删除标志;默认为0；为1时代表逻辑删除
     */
    public void setDeleteflag(Integer deleteflag) {
        this.deleteflag = deleteflag;
    }

    /**
     * 获取炒锅ID
     *
     * @return potId - 炒锅ID
     */
    public String getPotid() {
        return potid;
    }

    /**
     * 设置炒锅ID
     *
     * @param potid 炒锅ID
     */
    public void setPotid(String potid) {
        this.potid = potid;
    }

    /**
     * 获取盘子ID
     *
     * @return RFId - 盘子ID
     */
    public String getRfid() {
        return rfid;
    }

    /**
     * 设置盘子ID
     *
     * @param rfid 盘子ID
     */
    public void setRfid(String rfid) {
        this.rfid = rfid;
    }

    /**
     * 获取倍速链ID
     *
     * @return belitId - 倍速链ID
     */
    public String getBelitid() {
        return belitid;
    }

    /**
     * 设置倍速链ID
     *
     * @param belitid 倍速链ID
     */
    public void setBelitid(String belitid) {
        this.belitid = belitid;
    }

    /**
     * 获取状态更新时间
     *
     * @return updateTime - 状态更新时间
     */
    public String getUpdatetime() {
        return updatetime;
    }

    /**
     * 设置状态更新时间
     *
     * @param updatetime 状态更新时间
     */
    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    /**
     * 获取同类炒锅个数
     *
     * @return potNumber - 同类炒锅个数
     */
    public Integer getPotnumber() {
        return potnumber;
    }

    /**
     * 设置同类炒锅个数
     *
     * @param potnumber 同类炒锅个数
     */
    public void setPotnumber(Integer potnumber) {
        this.potnumber = potnumber;
    }

    /**
     * 获取收到订单时间
     *
     * @return time0 - 收到订单时间
     */
    public String getTime0() {
        return time0;
    }

    /**
     * 设置收到订单时间
     *
     * @param time0 收到订单时间
     */
    public void setTime0(String time0) {
        this.time0 = time0;
    }

    /**
     * 获取发盘时间
     *
     * @return time1 - 发盘时间
     */
    public String getTime1() {
        return time1;
    }

    /**
     * 设置发盘时间
     *
     * @param time1 发盘时间
     */
    public void setTime1(String time1) {
        this.time1 = time1;
    }
}