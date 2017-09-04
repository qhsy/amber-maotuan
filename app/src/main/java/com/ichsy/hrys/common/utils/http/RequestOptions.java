package com.ichsy.hrys.common.utils.http;

/**
 * 请求所需要的参数
 *
 * @author liuyuhang
 */
public class RequestOptions {

    private int timeout = 0;
    private Object tag;
    private String httpsCer;// https证书位置
    private String httpsCerPassWord;

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public Object getTag() {
        return tag;
    }

    public void setTag(Object tag) {
        this.tag = tag;
    }

    public String getHttpsCer() {
        return httpsCer;
    }

    public void setHttpsCer(String httpsCer) {
        this.httpsCer = httpsCer;
    }

    public String getHttpsCerPassWord() {
        return httpsCerPassWord;
    }

    public void setHttpsCerPassWord(String httpsCerPassWord) {
        this.httpsCerPassWord = httpsCerPassWord;
    }

}
