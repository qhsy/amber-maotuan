package com.ichsy.hrys.entity.request;

/**
 * 接单请求实体
 * author: ihesen on 2016/8/23 17:22
 * email: hesen@ichsy.com
 */
public class TaskApplicationRequestEntity extends BaseRequest {

    /**
     * 任务编号
     */
    public String taskCode;

    /**
     * 是否强制报名
     */
    public boolean forceApplicationFlag = false;

}
