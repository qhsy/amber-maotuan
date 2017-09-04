package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.entity.PageOption;

/**
 * 任务列表请求实体
 * author: ihesen on 2016/8/16 14:24
 * email: hesen@ichsy.com
 */
public class TaskListRequestEntity extends BaseRequest {

    public PageOption pageOption = new PageOption();

    public String screeningConditions;
    /**
     * "0:精选 1:全部 2:商品
     */
    public String selectType;
}
