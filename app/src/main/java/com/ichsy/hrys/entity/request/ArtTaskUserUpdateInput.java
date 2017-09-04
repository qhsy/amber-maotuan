package com.ichsy.hrys.entity.request;

/**
 * 内容任务 添加备注信息实体
 * author: ihesen on 2016/12/7 10:45
 * email: hesen@ichsy.com
 */

public class ArtTaskUserUpdateInput extends BaseRequest {

    /**
     * 备注信息
     */
    public String remark;
    /**
     * 任务编号
     */
    public String taskCode;
}
