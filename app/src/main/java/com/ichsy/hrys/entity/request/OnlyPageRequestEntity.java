package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.entity.PageOption;

/**
 * 功能：请求参数只有分页信息的的请求实体
 * ＊创建者：赵然 on 16/5/23 17:11
 * ＊
 */
public class OnlyPageRequestEntity extends BaseRequest {
    public PageOption pageOption = new PageOption();
}
