package com.ichsy.hrys.entity.request;

/**
 * 获取任务相关的帖子列表(请求实体)
 * author: ihesen on 2016/8/17 14:25
 * email: hesen@ichsy.com
 */
public class ProductTaskRequestEntity extends OnlyPageRequestEntity {

    public int deviceScreenWidth;
    /**
     * 商品编号
     */
    public String productNo;
}
