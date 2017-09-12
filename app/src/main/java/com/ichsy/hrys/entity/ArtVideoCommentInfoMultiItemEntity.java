package com.ichsy.hrys.entity;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 视频详细页和全部回复 公用一个
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

    /**
     * 全部回复
     */
    //顶部原评论
    public static final int ORIGN_REPLY = NO_DATA + 1;

    //分割线
    public static final int ITEM_DIVIDER = ORIGN_REPLY + 1;


    //是否收藏
    public boolean collected;

    public int commentCount;//评论数量

    public ArtVideoCommentInfo videoCommentInfo = new ArtVideoCommentInfo(); //评论信息

    public ArtVideoInfo videoInfo = new ArtVideoInfo(); //视频信息

    public List<ArtVideoReplyInfo> commentReplyList = new ArrayList<>(); //回复列表

    public ArtVideoReplyInfo videoReplyInfo = new ArtVideoReplyInfo(); // 回复相信息

    public ArtPageResults pageResults = new ArtPageResults(); //分页

    public ArtVideoCommentInfoMultiItemEntity(int itemType) {
        super(itemType);
    }
}
