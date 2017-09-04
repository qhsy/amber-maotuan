package com.ichsy.hrys.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2017/1/9 15:12
 * email: hesen@ichsy.com
 */

public class ArtSimpleUserInfo {

    /**
     * 达人领域
     */
    public List<String> domainLabelList = new ArrayList<>();
    /**
     * 性别
     */
    public String sex;
    /**
     * 用户编号
     */
    public String userCode;
    /**
     * 头像压缩图
     */
    public String userIconThumburl;
    /**
     * 头像
     */
    public String userIconurl;
    /**
     * 用户介绍
     */
    public String userIntroduction;
    /**
     * 用户姓名
     */
    public String userName;
}
