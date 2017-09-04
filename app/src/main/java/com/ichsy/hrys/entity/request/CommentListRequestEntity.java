package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.entity.PageOption;

/**
 * 评论列表请求参数实体
 * author: ihesen on 2016/5/12 15:36
 * email: hesen@ichsy.com
 */
public class CommentListRequestEntity extends BaseRequest {

    public PageOption pageOption = new PageOption();
    public String postID;
}
