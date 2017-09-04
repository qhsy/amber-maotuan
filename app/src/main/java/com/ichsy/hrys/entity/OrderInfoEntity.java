package com.ichsy.hrys.entity;

/**
 * 功能：订单的实体
 * ＊创建者：赵然 on 16/5/12 16:26
 */
public class OrderInfoEntity {

    /**
     * 订单编号
     */
    private String orderId;
    /**
     * 订单标题
     */
    private String goodsTittle;
    /**
     * 订单价格
     */
    private double goodsPrice;
    /**
     * 订单图片地址
     */
    private String goodsPicUrl;
    /**
     * 订单状态
     * 0:全部状态,1:待付款,2:待发货,3:已发货,4:交易成功，5：交易失败
     */
    private int orderStatus;
    /**
     * 下单时间
     */
    private long orderTime;


    private String oderDetailUrl;

    public String getOrderDetailUrl() {
        if (null == oderDetailUrl) oderDetailUrl = "";

        return oderDetailUrl;
    }

    public void setOrderDetailUrl(String orderDetailUrl) {
        this.oderDetailUrl = orderDetailUrl;
    }

    public String getOrderId() {
        if (null == orderId) orderId = "";
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getGoodsTittle() {
        if (null == goodsTittle) goodsTittle = "";
        return goodsTittle;
    }

    public void setGoodsTittle(String goodsTittle) {
        this.goodsTittle = goodsTittle;
    }

    public double getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(double goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsPicUrl() {

        if (null == goodsPicUrl) goodsPicUrl = "";

        return goodsPicUrl;
    }

    public void setGoodsPicUrl(String goodsPicUrl) {
        this.goodsPicUrl = goodsPicUrl;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public long getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(long orderTime) {
        this.orderTime = orderTime;
    }
}
