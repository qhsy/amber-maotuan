package com.ichsy.hrys.entity;

import java.math.BigDecimal;

/**
 * author: ihesen on 2016/11/3 15:30
 * email: hesen@ichsy.com
 */

public class ArtSaleTaskOrderInfo {

    /**
     * 商品返利佣金
     */
    public BigDecimal productRebate;
    /**
     * 预计可提现时间,各状态下的时间 非时间差
     */
    public long presentTime;
    /**
     * 商品数量
     */
    public int productNum;
    /**
     * 商品总计返利金额
     */
    public BigDecimal sumRebate;
    /**
     *商品价格
     */
    public BigDecimal marketPrice;

    /**
     * 商品主图
     */
    private String mainpicUrl;
    /**
     * 商品编号
     */
   private String productCode;
    /**
     *商品名称
     */
    private String productName;
    /**
     * 订单状态
     * "已收货(ocsd439310011004),已付款(ocsd439310011002),未付款(ocsd439310011001),已发货(ocsd439310011003),全部传空"
     */
    private String orderStats;
    /**
     * 用户手机号
     */
    private String userPhone;

    public String getMainpicUrl() {
        if (null == mainpicUrl) {
            mainpicUrl = "";
        }
        return mainpicUrl;
    }

    public void setMainpicUrl(String mainpicUrl) {
        this.mainpicUrl = mainpicUrl;
    }

    public String getProductCode() {
        if (null == productCode) {
            productCode = "";
        }
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductName() {
        if (null == productName) {
            productName = "";
        }
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOrderStats() {
        if (null == orderStats) {
            orderStats = "";
        }
        return orderStats;
    }

    public void setOrderStats(String orderStats) {
        this.orderStats = orderStats;
    }

    public String getUserPhone() {
        if (null == userPhone) {
            userPhone = "";
        }
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
