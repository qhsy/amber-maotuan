package com.ichsy.hrys.entity.response;

import com.ichsy.hrys.entity.ArtPageResults;
import com.ichsy.hrys.entity.ArtShareModel;
import com.ichsy.hrys.entity.ArtSimplePost;
import com.ichsy.hrys.entity.ArtUserInfo;
import com.ichsy.hrys.entity.ArtUserOfferInfo;
import com.ichsy.hrys.entity.ArtVideoInfo;
import com.ichsy.hrys.entity.UserImageInfoEntity;
import com.ichsy.hrys.entity.UserSocialMediaEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能： 获取帖子列表界面的请求返回实体
 * ＊创建者：赵然 on 16/5/23 20:36
 * ＊
 */
public class ArtGetPersonalInfoResult extends BaseResponse {
    /**
     * 个人形象实体
     */
    public UserImageInfoEntity artPersonalImage = new UserImageInfoEntity();
    /**
     * 是否关注 0:未关注，1:关注，2：自己
     */
    public int attentionStatus;
    /**
     * 个人主页内容列表
     */
    public List<ArtSimplePost> contentList = new ArrayList<>();
    /**
     * 分页
     */
    public ArtPageResults pageresults = new ArtPageResults();
    /**
     * 分享信息
     */
    public ArtShareModel shareModel = new ArtShareModel();
    /**
     * 影响力实体
     */
    public List<UserSocialMediaEntity> socialMediaList = new ArrayList<>();
    /**
     * 用户基本信息
     */
    public ArtUserInfo userInfo = new ArtUserInfo();
    /**
     * 达人服务报价实体
     */
    public ArtUserOfferInfo userOfferInfo = new ArtUserOfferInfo();

    /**
     * 视频列表
     */
    public List<ArtVideoInfo> videoContentList = new ArrayList<>();
}
