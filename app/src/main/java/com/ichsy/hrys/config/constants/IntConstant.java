package com.ichsy.hrys.config.constants;

/**
 * author: zhu on 2017/4/12 14:09
 * email: mackkilled@gmail.com
 */

public interface IntConstant {
    /***
     * 调用系统相机
     */
    int CAMERA = 10001;
    /***
     * 调用系统相册
     */
    int PICTURE = 10006;
    /***
     * 跳转 视频选择
     */
    int VIDEO = 10007;
    /**
     *跳转到裁剪界面的 裁剪类型 0： 头像  1：个人背景图
     */
    int CLIPIMAGETYPE_ICON = 0 ;
    int CLIPIMAGETYPE_USERMSGBG = 1 ;
    /**
     * 跳转到 commonWebView 界面  链接类型  0：直接展示 1：订单详情界面 2:显示标题带返回按钮,如果不手动设置title默认读取网页中title
     */
    int WEBVIEWURL_TYPE_NORMAL = 0;
    int WEBVIEWURL_TYPE_SHOWTITLE = 1;
    /**
     * 跳转到任务列表界面 展示的页面
     */
    int TO_TASKLIS_SHOW_JOIN = 0;
    int TO_TASKLIS_SHOW_COLLECTION = 1;
    /**
     * 修改个人信息的请求类型
     */
    int MODIFYUSERINFO_ALL = 2;
    int MODIFYUSERINFO_BASEINFO = 0;
    int MODIFYUSERINFO_IMAGEINFO = 1;

    /**您的性别不符合要求*/
    int ERROR_SEX = 29191164;
    /**您尚未绑定社交媒体，是否立刻绑定？*/
    int ERROR_UNBIND = 29191165;
    /**您尚未完善资料，是否立刻填写？*/
    int ERROR_UNCOMPLETED = 29191166;
    /**您的微博粉丝数量与商家的要求不符，是否继续报名？*/
    int ERROR_FANS = 29191167;

}
