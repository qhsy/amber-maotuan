package com.ichsy.hrys.entity.response;

import com.ichsy.hrys.entity.ArtPageResults;
import com.ichsy.hrys.entity.ArtShareModel;
import com.ichsy.hrys.entity.ArtVideoCommentInfo;
import com.ichsy.hrys.entity.ArtVideoInfo;

import java.util.List;

/**
 * Created by zhu on 2017/8/26.
 */

public class ArtGetVideoInfoResult extends BaseResponse{
    public ArtPageResults pageResults = new ArtPageResults(); //分页

    public boolean collected;

    public int commentCount;//评论数量 ,

    public ArtShareModel shareModel; // 分享信息 ,

    public List<ArtVideoCommentInfo> videoCommentList; //评论列表

    public ArtVideoInfo videoInfo; //视频信息

}
