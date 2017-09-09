package com.ichsy.hrys.common.utils.http.retrofit;


import com.ichsy.hrys.config.config.ServiceConfig;
import com.ichsy.hrys.entity.request.ArtCensusVideoPlayInput;
import com.ichsy.hrys.entity.request.ArtCommentThumbsUpDownInput;
import com.ichsy.hrys.entity.request.ArtDeleteVideoCommentInput;
import com.ichsy.hrys.entity.request.ArtGetVideoCommentInfoInput;
import com.ichsy.hrys.entity.request.ArtGetVideoInfoInput;
import com.ichsy.hrys.entity.request.ArtGetVideoListInputEntity;
import com.ichsy.hrys.entity.request.ArtPersonalHomepageInput;
import com.ichsy.hrys.entity.request.ArtSendVideoCommentInput;
import com.ichsy.hrys.entity.request.ArtVideoOperationOfCollectingInput;
import com.ichsy.hrys.entity.request.ArtVideoShareInput;
import com.ichsy.hrys.entity.request.BandPhoneRequestEntity;
import com.ichsy.hrys.entity.request.BaseRequest;
import com.ichsy.hrys.entity.request.CheckUpdateRequestEntity;
import com.ichsy.hrys.entity.request.GetAppConfigRequestEntity;
import com.ichsy.hrys.entity.request.GetVerifyCodeRequestEntity;
import com.ichsy.hrys.entity.request.LoginRequestEntity;
import com.ichsy.hrys.entity.request.LoginWithWXRequestEntity;
import com.ichsy.hrys.entity.request.ModifyUserInfoRequestEntity;
import com.ichsy.hrys.entity.request.OnlyPageRequestEntity;
import com.ichsy.hrys.entity.response.ArtCommentThumbsUpDownResult;
import com.ichsy.hrys.entity.response.ArtGetPersonalInfoResult;
import com.ichsy.hrys.entity.response.ArtGetVideoCommentInfoResult;
import com.ichsy.hrys.entity.response.ArtGetVideoInfoResult;
import com.ichsy.hrys.entity.response.ArtGetVideoListResult;
import com.ichsy.hrys.entity.response.ArtGetVideoNotifactionMessagResult;
import com.ichsy.hrys.entity.response.BaseResponse;
import com.ichsy.hrys.entity.response.CheckUpdateResponseEntity;
import com.ichsy.hrys.entity.response.GetAppConfigResponseEntity;
import com.ichsy.hrys.entity.response.GetCollcetionListResponseEntity;
import com.ichsy.hrys.entity.response.GetDomainLableListResponseEntity;
import com.ichsy.hrys.entity.response.GetUserAccountInfoResponseEntity;
import com.ichsy.hrys.entity.response.GetVerifyCodeResponseEntity;
import com.ichsy.hrys.entity.response.LoginResponseEntity;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * 请求工具类
 * <p/>
 * Created by 赵然 on 15/12/31.
 */
public interface RequestInterface {

    //获取验证码接口
    @POST(ServiceConfig.GETVERIFYCODE)
    Observable<GetVerifyCodeResponseEntity> getVerifyCode(@Body GetVerifyCodeRequestEntity entity);

    //验证码登录接口
    @POST(ServiceConfig.LOGININ)
    Observable<LoginResponseEntity> loginIn(@Body LoginRequestEntity entity);

    //获取用户信息--我的栏
    @POST(ServiceConfig.GETUSERACCOUNTINFO)
    Observable<GetUserAccountInfoResponseEntity> getUserAccountInfo(@Body BaseRequest requestEntity);
    /**
     * 微信登录接口
     */
    @POST(ServiceConfig.LOGINWITHWX)
    Observable<LoginResponseEntity> loginWithWX(@Body LoginWithWXRequestEntity requestEntity);
    /**
     * 绑定手机号接口
     */
    @POST(ServiceConfig.BANDPHONE)
    Observable<LoginResponseEntity> bandPhone(@Body BandPhoneRequestEntity requestEntity);

    //检查更新的接口
    @POST(ServiceConfig.CHECKUPDATE)
    Observable<CheckUpdateResponseEntity> checkUpdate(@Body CheckUpdateRequestEntity requestEntity);

    //配置接口
    @POST(ServiceConfig.GETAPPCONFIG)
    Observable<GetAppConfigResponseEntity> getAppConfig(@Body GetAppConfigRequestEntity requestEntity);

    //获取个人主页
    @POST(ServiceConfig.GET_PERSONAL_INFO)
    Observable<ArtGetPersonalInfoResult> getPersonalInfo(@Body ArtPersonalHomepageInput requestEntity);

    //获取收藏列表
    @POST(ServiceConfig.GET_COLLECTE_VIDEOLIST)
    Observable<GetCollcetionListResponseEntity> getCollectionlist(@Body OnlyPageRequestEntity requestEntity);

    //获取消息列表
    @POST(ServiceConfig.GET_NOTIFACTION_MESSAG)
    Observable<ArtGetVideoNotifactionMessagResult> getNotifactionList(@Body OnlyPageRequestEntity requestEntity);

    /**
     * 修改用户信息
     *
     * @param request
     * @return
     */
    @POST(ServiceConfig.MODIFYUSERINFO)
    Observable<BaseResponse> modifyUserInfo(@Body ModifyUserInfoRequestEntity request);
    /**
     * 获取领域标签
     */
    @POST(ServiceConfig.GETDOMAINLABLE)
    Observable<GetDomainLableListResponseEntity> getDomainLabls(@Body OnlyPageRequestEntity requestEntity);

    /**
     * 是否收藏video
     */
    @POST(ServiceConfig.VIDEO_COLLECTION)
    Observable<BaseResponse> taskCollection(@Body ArtVideoOperationOfCollectingInput requestEntity);

    /**
     * 是赞video
     */
    @POST(ServiceConfig.VIDEO_THUMBSUPDOWN)
    Observable<ArtCommentThumbsUpDownResult> taskThumbsUpDown(@Body ArtCommentThumbsUpDownInput requestEntity);

    // 获取首页列表接口
    @POST(ServiceConfig.GET_VIDEO_LIST)
    Observable<ArtGetVideoListResult> getHomePageInfo(@Body ArtGetVideoListInputEntity entity);

    // 获取视屏详细信息
    @POST(ServiceConfig.GET_VIDEO_INFO)
    Observable<ArtGetVideoInfoResult> getVideoInfo(@Body ArtGetVideoInfoInput entity);

    // 评论视频详细页
    @POST(ServiceConfig.SEND_VIDEO_COMMENT)
    Observable<BaseResponse> sendVideoComment(@Body ArtSendVideoCommentInput entity);

    // 删除评论回复接口
    @POST(ServiceConfig.SEND_VIDEO_COMMENT)
    Observable<BaseResponse> deleteVideoComment(@Body ArtDeleteVideoCommentInput entity);

    // 全部回复
    @POST(ServiceConfig.GET_COMMENT_ALL_REPLY)
    Observable<ArtGetVideoCommentInfoResult> getVideoCommentInfo(@Body ArtGetVideoCommentInfoInput entity);

    // 统计视频播放次数
    @POST(ServiceConfig.VIDEO_PLAYCOUNT)
    Observable<BaseResponse> getVideoPlayCount(@Body ArtCensusVideoPlayInput entity);

    // 统计视频分享次数
    @POST(ServiceConfig.VIDEO_SHARECOUNT)
    Observable<BaseResponse> getVideoShareCount(@Body ArtVideoShareInput entity);
}
