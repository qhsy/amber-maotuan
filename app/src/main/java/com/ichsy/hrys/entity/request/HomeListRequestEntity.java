package com.ichsy.hrys.entity.request;

import com.ichsy.hrys.entity.PageOption;

/**
 * author: ihesen on 2016/5/9 13:54
 * email: hesen@ichsy.com
 */
public class HomeListRequestEntity extends BaseRequest {
    public PageOption pageOption = new PageOption();
    public int deviceScreenWidth;
}
