package com.ichsy.hrys.entity;

/**
 * author: ihesen on 2017/3/8 10:41
 * email: hesen@ichsy.com
 */

public class MyPriceItem {

    public String type;
    public String price;
    public String description;

    /**临时标识，用于处理是否显示编辑浮层*/
    public boolean showEditLayer = false;
}
