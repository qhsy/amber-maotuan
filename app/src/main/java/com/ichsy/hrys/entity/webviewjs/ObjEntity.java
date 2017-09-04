package com.ichsy.hrys.entity.webviewjs;

public class ObjEntity {

    /**
     * 任务ID
     */
    private String taskCode;
    /**
     * 商品ID
     */
    private String goodsCode;
    /**
     * 帖子 ID
     */
    private String postID;

    /**
     * 任务类型
     */
    private String taskType;

    public String getPostID() {
        return postID;
    }

    public void setPostID(String postID) {
        this.postID = postID;
    }

    public String getTaskCode() {
        return taskCode;
    }

    public void setTaskCode(String taskCode) {
        this.taskCode = taskCode;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }
}
