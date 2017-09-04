package com.ichsy.hrys.entity.request;

/**
 * 是否收藏任务请求实体
 * author: ihesen on 2016/8/17 10:26
 * email: hesen@ichsy.com
 */
public class ArtVideoOperationOfCollectingInput extends BaseRequest {
    /**
     * 0:取消关注 1:关注
     */
    public int operationType;
    /**
     * 任务ID
     */
    public String collectionID;
}
