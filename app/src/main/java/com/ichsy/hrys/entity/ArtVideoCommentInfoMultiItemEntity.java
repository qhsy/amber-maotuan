package com.ichsy.hrys.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * author: zhu on 2017/8/28 10:34
 * email: mackkilled@gmail.com
 */

public class ArtVideoCommentInfoMultiItemEntity extends MultiItemEntity{

    //视频信息
    public static final int PUBLISH_INFO = 0;

    //评论列表
    public static final int COMMENT_LIST = PUBLISH_INFO + 1;

    //评论数目
    public static final int COMMENT_NUM = COMMENT_LIST + 1;

    //空页面
    public static final int NO_DATA = COMMENT_NUM + 1;

    //是否收藏
    public boolean collected;

    public int commentCount;//评论数量

    public ArtVideoCommentInfo videoCommentInfo = new ArtVideoCommentInfo(); //评论信息

    public ArtVideoInfo videoInfo = new ArtVideoInfo(); //视频信息

    public List<ArtVideoReplyInfo> commentReplyList = new ArrayList<>(); //回复列表

    public ArtVideoCommentInfoMultiItemEntity(int itemType) {
        super(itemType);
    }
}
