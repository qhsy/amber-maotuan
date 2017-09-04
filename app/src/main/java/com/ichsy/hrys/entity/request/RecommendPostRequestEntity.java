package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.entity.PageOption;

/**
 * author: ihesen on 2016/5/18 11:42
 * email: hesen@ichsy.com
 */
public class RecommendPostRequestEntity extends BaseRequest {

    /**
     * 全部返回的tab标签id
     */
    public String lableID;
    public PageOption pageOption = new PageOption();
    /**
     * 0:精选 1：全部和关注
     */
    public int selectType;
    /**
     * 0:全部 1：关注
     */
    public String tabID;

    public int deviceScreenWidth;

}
