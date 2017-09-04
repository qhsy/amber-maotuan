package com.ichsy.hrys.entity.response;

/**
 * 返回请求的基类
 */
public class BaseResponse {

    /**
     * 成功：1 失败：错误码
     */
    public int status;
    /**
     * 返回的消息描述
     */
    private String error ;


    public String getError() {
        if (null == error) error = "";
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
