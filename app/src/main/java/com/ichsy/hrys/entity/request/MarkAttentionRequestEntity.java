package com.ichsy.hrys.entity.request;

/**
 * author: ihesen on 2016/5/18 19:37
 * email: hesen@ichsy.com
 */
public class MarkAttentionRequestEntity extends BaseRequest {

    /**
     * 0:取消关注
     * 1:关注
     */
    public int operationType;
    /**
     * 添加关注的用户id信息
     */
    public String userID;
}
