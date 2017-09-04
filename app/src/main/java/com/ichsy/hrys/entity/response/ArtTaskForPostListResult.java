package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.ArtSimpleGoodsInfo;
import com.ichsy.hrys.entity.ArtSimplePost;
import com.ichsy.hrys.entity.PageResults;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取任务相关的帖子列表(响应实体)
 * author: ihesen on 2016/8/17 14:29
 * email: hesen@ichsy.com
 */
public class ArtTaskForPostListResult extends BaseResponse {

    public List<ArtSimplePost> artSimplePost = new ArrayList<>();
    public ArtSimpleGoodsInfo goodsSimpDetail = new ArtSimpleGoodsInfo();
    public PageResults pageResults = new PageResults();
    /**
     * 帖子总数
     */
    public int totalNum;

}
