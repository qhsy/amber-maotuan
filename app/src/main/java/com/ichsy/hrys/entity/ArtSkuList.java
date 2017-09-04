package com.ichsy.hrys.entity;

/**
 * author: ihesen on 2016/5/18 15:55
 * email: hesen@ichsy.com
 */
public class ArtSkuList {

    public String skuCode;
    private String skuImageUrl;
    public String skuPrice;
    public String skuValue;

    public String getSkuImageUrl() {
        if (null == skuImageUrl) {
            skuImageUrl = "";
        }
        return skuImageUrl;
    }

    public void setSkuImageUrl(String skuImageUrl) {
        this.skuImageUrl = skuImageUrl;
    }
    /**
     * 返利金额
     */
    public String rebatePrice;
}
