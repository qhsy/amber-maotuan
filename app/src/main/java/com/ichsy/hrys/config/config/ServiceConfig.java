package com.ichsy.hrys.config.config;

/**
 * author: zhu on 2017/4/12 14:09
 * email: mackkilled@gmail.com
 */

public interface ServiceConfig {
    /**
     * 获取验证码接口
     */
    String GETVERIFYCODE = "/api/artUserController/getVerifyCode";
    /**
     * 验证码登录接口
     */
    String LOGININ = "/api/artUserController/loginByPassword";

    /**
     * 获取用户信息 --我的页面
     */
    String GETUSERACCOUNTINFO = "/api/artUserController/getUserAccountInfo";

    /**
     * 微信登录接口
     */
    String LOGINWITHWX = "/api/artUserController/loginWithWX";
    /**
     * 绑定手机号
     */
    String BANDPHONE = "/api/artUserController/bandMobileNum";

    /**
     * app更新
     */
    String CHECKUPDATE = "/api/artUserController/versionInfo";
    /**
     * 配置接口
     */
    String GETAPPCONFIG = "/api/artUserController/getConfig";

    /**
     * 个人主页信息
     */
    String GET_PERSONAL_INFO = "/api/artshortvideo/homepage";
    /**
     * 我的收藏接口
     */
    String GET_COLLECTE_VIDEOLIST = "/api/artshortvideo/getCollecteVideoList";
    /**
     * 我的消息
     */
    String GET_NOTIFACTION_MESSAG = "/api/artshortvideo/getVideoNotifactionMessag";

    /**
     * 修改用户信息
     */
    String MODIFYUSERINFO = "/api/artUserController/modifyUserMessage";

    /**
     * 获取领域标签
     */
    String GETDOMAINLABLE = "/api/artTaskController/getdomainList";

    /**
     * 是否收藏video
     */
    String VIDEO_COLLECTION = "/api/artshortvideo/collectingVideo";

    /**
     * 是点赞video
     */
    String VIDEO_THUMBSUPDOWN = "/api/artshortvideo/commetThumbsUpDown";

    /**
     * 获取首页视频列表
     */
    String GET_VIDEO_LIST = "/api/artshortvideo/getVideoList";

    /**
     * 获取视频详细信息
     */
    String GET_VIDEO_INFO = "/api/artshortvideo/getVideoInfo";

    /**
     * 评论视频
     */
    String SEND_VIDEO_COMMENT = "/api/artshortvideo/sendVideoComment";

    /**
     *删除评论回复接口
     */
    String DELETE_VIDEO_COMMENT="/api/artshortvideo/deleteVideoComment";

    /**
     * 统计视频播放次数接口
     */
    String VIDEO_PLAYCOUNT = "/api/artshortvideo/censusVideoPlay";

    /**
     * 统计视频分享次数接口
     */
    String VIDEO_SHARECOUNT = "/api/artshortvideo/videoShare";

}
