package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.entity.PageOption;

/**
 * author: ihesen on 2016/5/17 17:37
 * email: hesen@ichsy.com
 */
public class PostListOfDiscoverRequestEntity extends BaseRequest {

    /**
     * 分类标签ID
     */
    public String lableID;
    /**
     * 获取类型获取类型
     */
    public int selectType = 0;
    public PageOption pageOption = new PageOption();
}
