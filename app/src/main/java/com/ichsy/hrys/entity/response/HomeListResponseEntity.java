package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtHomeContent;
import com.ichsy.hrys.entity.PageResults;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 * author: ihesen on 2016/5/9 13:53
 * email: hesen@ichsy.com
 */
public class HomeListResponseEntity extends BaseResponse {
    /**
     * 内容列表
     */
    public List<ArtHomeContent> homeContent = new ArrayList<>();
    /**
     * 分页信息
     */
    public PageResults pageResults = new PageResults();
}
