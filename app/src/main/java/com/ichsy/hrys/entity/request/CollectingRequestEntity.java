package com.ichsy.hrys.entity.request;

/**
 * author: ihesen on 2016/5/18 19:34
 * email: hesen@ichsy.com
 */
public class CollectingRequestEntity extends BaseRequest {

    /**
     * 帖子ID/商品ID
     */
    public String collectionID;
    /**
     * 1;添加
     * 0:取消
     */
    public String operationType;
    /**
     * 收藏类型
     * 0:帖子
     * 1:商品
     */
    public String selectedType;

}
