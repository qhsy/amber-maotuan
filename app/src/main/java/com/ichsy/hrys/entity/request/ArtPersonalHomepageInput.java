package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.entity.PageOption;

/**
 * 功能：获取帖子列表接口的请求实体i
 * ＊创建者：赵然 on 16/5/23 20:34
 * ＊
 */
public class ArtPersonalHomepageInput extends  BaseRequest {
    public PageOption pageOption = new PageOption();
    public String userCode;
    public int deviceScreenWidth;
}
