package com.ichsy.hrys.entity.response;

import com.ichsy.hrys.entity.ArtPageResults;
import com.ichsy.hrys.entity.ArtVideoInfo;
import com.ichsy.hrys.entity.ArtVideoPromotionDetail;

import java.util.ArrayList;
import java.util.List;

/**
 * author: zhu on 2017/8/25 15:17
 * email: mackkilled@gmail.com
 */
//首页视频列表
public class ArtGetVideoListResult extends BaseResponse{
    /**
     * 分页
     */
    public ArtPageResults pageResults = new ArtPageResults();

    /**
     * banner
     */
    public List<ArtVideoPromotionDetail> promotionPhotoList;

    /**
     * video 列表
     */
    public List<ArtVideoInfo> videoList = new ArrayList<>();

}
