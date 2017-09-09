package com.ichsy.hrys.common.utils;

import com.ichsy.hrys.common.utils.http.RequestListener;
import com.ichsy.hrys.common.utils.http.retrofit.RequestController;
import com.ichsy.hrys.common.utils.http.retrofit.RequestService;
import com.ichsy.hrys.common.utils.http.retrofit.RequestSubscriber;
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
import com.ichsy.hrys.entity.response.BaseResponse;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 功能： 所有的请求 统一管理
 *
 */
public class RequestUtils {

    /**
     * 获取验证码接口
     */
    public static void getVerifyCode(String reuqestUnicode, GetVerifyCodeRequestEntity requestEntity, RequestListener listener) {
        sendRequest(
                reuqestUnicode
                , RequestService.getInstance().getVerifyCode(requestEntity)
                , ServiceConfig.GETVERIFYCODE
                ,listener);
    }

    /**
     * 验证码登陆接口
     * @param loginRequestEntity
     * @param listener
     */

    public static void loginInByVerifyCode(String reuqestUnicode, LoginRequestEntity loginRequestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().loginIn(loginRequestEntity)
                ,ServiceConfig.LOGININ
                ,listener);

    }

    /**
     * 微信登录接口
     */
    public static void loginWithWX(String reuqestUnicode, LoginWithWXRequestEntity requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().loginWithWX(requestEntity)
                ,ServiceConfig.LOGINWITHWX
                ,listener);
    }

    /**
     * 绑定手机号接口
     */
    public static void bandPhone(String reuqestUnicode, BandPhoneRequestEntity requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().bandPhone(requestEntity)
                ,ServiceConfig.BANDPHONE
                ,listener);

    }

    /**
     * 获取检查更新
     */
    public static void checkUpdate(String  reuqestUnicode, CheckUpdateRequestEntity requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().checkUpdate(requestEntity)
                ,ServiceConfig.CHECKUPDATE
                ,listener);
    }

    /**
     * 获取APP配置信息
     */
    public static void getAppConfig(String reuqestUnicode, GetAppConfigRequestEntity requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().getAppConfig(requestEntity)
                ,ServiceConfig.GETAPPCONFIG
                ,listener);
    }

    /**
     * 个人主页
     */
    public static  void getPersonalInfo(String reuqestUnicode, ArtPersonalHomepageInput requestEntity , RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().getPersonalInfo(requestEntity)
                ,ServiceConfig.GET_PERSONAL_INFO
                ,listener);
    }

    /**
     * 获取收藏列表
     */
    public static void getCollectionList(String reuqestUnicode, OnlyPageRequestEntity requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().getCollectionlist(requestEntity)
                ,ServiceConfig.GET_COLLECTE_VIDEOLIST
                ,listener);
    }

    /**
     *获取消息列表的请求
     */
    public static  void getNotifactionList(String reuqestUnicode, OnlyPageRequestEntity requestEntity , RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().getNotifactionList(requestEntity)
                ,ServiceConfig.GET_NOTIFACTION_MESSAG
                ,listener);
    }

    /**
     * 是否收藏视频
     * @param reuqestUnicode
     * @param requestEntity
     * @param listener
     */
    public static void videoCollection(String reuqestUnicode, ArtVideoOperationOfCollectingInput requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().taskCollection(requestEntity)
                ,ServiceConfig.VIDEO_COLLECTION
                ,listener);
    }

    /**
     * 获取用户信息页面 --- 我的页面
     */
    public  static  void getUserAccountInfo(String reuqestUnicode,RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().getUserAccountInfo(new BaseRequest())
                ,ServiceConfig.GETUSERACCOUNTINFO
                ,listener);

    }

    /**
     * 修改用户信息
     */

    public static  void modifyUserInfo(String reuqestUnicode, ModifyUserInfoRequestEntity requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().modifyUserInfo(requestEntity)
                ,ServiceConfig.MODIFYUSERINFO
                ,listener);
    }

    /**
     * 获取领域标签
     * @param reuqestUnicode
     * @param requestEntity
     * @param listener
     */
    public static void getDomainLabls(String reuqestUnicode, OnlyPageRequestEntity requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().getDomainLabls(requestEntity)
                ,ServiceConfig.GETDOMAINLABLE
                ,listener);
    }

    /**
     * 获取首页视屏列表
     */
    public static void getHomeItemPage(String reuqestUnicode, ArtGetVideoListInputEntity requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().getHomePageInfo(requestEntity)
                ,ServiceConfig.GET_VIDEO_LIST
                ,listener);
    }

    /**
     * 获取视频详细信息
     */
    public static void getVideoInfo(String reuqestUnicode, ArtGetVideoInfoInput requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().getVideoInfo(requestEntity)
                ,ServiceConfig.GET_VIDEO_INFO
                ,listener);
    }

    /**
     * 评论视频
     * @param reuqestUnicode
     * @param requestEntity
     * @param listener
     */
    public static void sendVideoComment(String reuqestUnicode, ArtSendVideoCommentInput requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().sendVideoComment(requestEntity)
                ,ServiceConfig.SEND_VIDEO_COMMENT
                ,listener);
    }

    /**
     * 删除评论回复接口
     * @param reuqestUnicode
     * @param requestEntity
     * @param listener
     */
    public static void deleteVideoComment(String reuqestUnicode, ArtDeleteVideoCommentInput requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().deleteVideoComment(requestEntity)
                ,ServiceConfig.DELETE_VIDEO_COMMENT
                ,listener);
    }

    /**
     * 是点赞视频
     * @param reuqestUnicode
     * @param requestEntity
     * @param listener
     */
    public static void videoThumbsUpDown(String reuqestUnicode, ArtCommentThumbsUpDownInput requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().taskThumbsUpDown(requestEntity)
                ,ServiceConfig.VIDEO_THUMBSUPDOWN
                ,listener);
    }

    /**
     * 获取回复列表
     * @param reuqestUnicode
     * @param requestEntity
     * @param listener
     */
    public static void getVideoCommentListInfo(String reuqestUnicode, ArtGetVideoCommentInfoInput requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().getVideoCommentInfo(requestEntity)
                ,ServiceConfig.GET_COMMENT_ALL_REPLY
                ,listener);
    }

    /**
     * 统计播放次数
     * @param reuqestUnicode
     * @param requestEntity
     * @param listener
     */
    public static void getVideoPlayCount(String reuqestUnicode, ArtCensusVideoPlayInput requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().getVideoPlayCount(requestEntity)
                ,ServiceConfig.VIDEO_PLAYCOUNT
                ,listener);
    }

    /**
     * 统计分享次数
     * @param reuqestUnicode
     * @param requestEntity
     * @param listener
     */
    public static void getVideoShareCount(String reuqestUnicode, ArtVideoShareInput requestEntity, RequestListener listener){
        sendRequest(
                reuqestUnicode
                ,RequestService.getInstance().getVideoShareCount(requestEntity)
                ,ServiceConfig.VIDEO_SHARECOUNT
                ,listener);
    }


    /**
     * 发送请求
     * @param obervable 调用请求方法返回的被观察者
     * @param url  请求接口地址
     * @param listener  请求回调
     */
    private static void sendRequest(String reuqestUnicode, Observable<? extends BaseResponse> obervable, String url, RequestListener listener){
        RequestSubscriber requestSubscriber = new RequestSubscriber(reuqestUnicode,url, listener);
        RequestController.getInstance().addRequest(reuqestUnicode+url,requestSubscriber);

        obervable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(requestSubscriber);
    }
    /**
     * 移除当前view相关的所有的请求
     * @param reuqestUnicode
     */
    public static void cancleRelativeRequest(String reuqestUnicode){
        RequestController.getInstance().removeAllRelativeRequest(reuqestUnicode);
    }


    /**
     * 取消当前view中得单个请求
     * @param reuqestUnicode
     * @param url
     */
    public static void cancleRequest(String reuqestUnicode, String url){
        RequestController.getInstance().removeRequest(reuqestUnicode+url);
    }

}
