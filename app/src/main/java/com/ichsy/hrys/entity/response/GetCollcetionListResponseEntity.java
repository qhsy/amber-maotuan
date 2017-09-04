package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtSimplePost;
import com.ichsy.hrys.entity.ArtVideoInfo;
import com.ichsy.hrys.entity.PageResults;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：获取收藏列表的请求返回实体
 * ＊创建者：赵然 on 16/5/23 17:10
 * ＊
 */
public class GetCollcetionListResponseEntity extends  BaseResponse {
    /**
     *翻页信息
     */
    public PageResults pageResults = new PageResults();

    // 老接口 不知道有什么用
    public List<ArtSimplePost> collectePostList = new ArrayList<>();

    /**
     * 我的收藏
     */
    public List<ArtVideoInfo> collecteVideoList = new ArrayList<>();

}

