package com.ichsy.hrys.entity;

import java.io.Serializable;

/**
 * author: ihesen on 2016/5/17 20:38
 * email: hesen@ichsy.com
 */
public class ArtSimpleGoodsInfo implements Serializable {

    private String goodsCode;
    private String goodsMarketPrice;
    private String goodsPicUrl;
    private String goodsTittle;
    private String minGoodsPrice;
    private String productDetailUrl;

    /** v1.1.6 作为销售任务订单列表商品简讯中 佣金返利字段使用 销售任务订单列表接口中返回*/
    private String productRebate;

    public String getProductRebate() {
        if (productRebate == null) {
            productRebate = "";
        }
        return productRebate;
    }

    public void setProductRebate(String productRebate) {
        this.productRebate = productRebate;
    }

    public String getGoodsCode() {
        if (goodsCode == null) {
            goodsCode = "";
        }
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsMarketPrice() {
        if (goodsMarketPrice == null) {
            goodsMarketPrice = "";
        }
        return goodsMarketPrice;
    }

    public void setGoodsMarketPrice(String goodsMarketPrice) {
        this.goodsMarketPrice = goodsMarketPrice;
    }

    public String getGoodsPicUrl() {
        if (goodsPicUrl == null) {
            goodsPicUrl = "";
        }
        return goodsPicUrl;
    }

    public void setGoodsPicUrl(String goodsPicUrl) {
        this.goodsPicUrl = goodsPicUrl;
    }

    public String getGoodsTittle() {
        if (goodsTittle == null) {
            goodsTittle = "";
        }
        return goodsTittle;
    }

    public void setGoodsTittle(String goodsTittle) {
        this.goodsTittle = goodsTittle;
    }

    public String getMinGoodsPrice() {
        if (minGoodsPrice == null) {
            minGoodsPrice = "";
        }
        return minGoodsPrice;
    }

    public void setMinGoodsPrice(String minGoodsPrice) {
        this.minGoodsPrice = minGoodsPrice;
    }

    public String getProductDetailUrl() {
        return productDetailUrl;
    }

    public void setProductDetailUrl(String productDetailUrl) {
        this.productDetailUrl = productDetailUrl;
    }
}
