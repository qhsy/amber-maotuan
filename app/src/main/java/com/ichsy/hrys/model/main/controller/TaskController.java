package com.ichsy.hrys.model.main.controller;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.ichsy.centerbus.CenterEventBus;
import com.ichsy.hrys.common.utils.IntentUtils;
import com.ichsy.hrys.common.utils.LoginUtils;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.http.SimpleRequestListener;
import com.ichsy.hrys.config.constants.IntConstant;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.ArtVideoInfo;
import com.ichsy.hrys.entity.ArtVideoPromotionDetail;
import com.ichsy.hrys.entity.request.ArtCommentThumbsUpDownInput;
import com.ichsy.hrys.entity.request.ArtVideoOperationOfCollectingInput;
import com.ichsy.hrys.entity.response.ArtCommentThumbsUpDownResult;
import com.ichsy.hrys.entity.response.BaseResponse;
import com.ichsy.hrys.model.base.CommonWebViewActivity;
import com.ichsy.hrys.model.details.VideoDetailActivity;
import com.ichsy.hrys.model.login.LoginEvent;
import com.ichsy.hrys.model.login.LoginParams;
import com.ichsy.hrys.model.person.PersonalInfoActivity;

/**
 * Created by zhu on 2017/8/26.
 */

public class TaskController {

    /**
     * 跳转视频详细页
     */
    public static void openVideoDetail(Activity context, ArtVideoInfo data) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(StringConstant.TASK_OBJ, data);
        IntentUtils.startActivity(context, VideoDetailActivity.class, bundle);
    }

    /**
     * 跳转个人主页
     */
    public static void openPersionInfo(Activity context, String userCode) {
        Bundle bundle = new Bundle();
        bundle.putString(StringConstant.USERID, userCode);
        IntentUtils.startActivity(context, PersonalInfoActivity.class, bundle);
    }

    /**
     * 控制跳转（首页banner）
     */
    public static void forword(Activity activity, ArtVideoPromotionDetail taskInfo) {
        switch (taskInfo.getGotoType()) {
            //视频详情
            case StringConstant.VIDEO_DETAIL:
                Bundle b = new Bundle();
                ArtVideoInfo data = new ArtVideoInfo();
                data.setVideoNumber(taskInfo.getVideoId());
                b.putSerializable(StringConstant.TASK_OBJ, data);
                IntentUtils.startActivity(activity, VideoDetailActivity.class, b);
                break;
            //web页
            case StringConstant.WEBVIEW:
                Bundle bundle = new Bundle();
                bundle.putString(StringConstant.PARAMS_URL, taskInfo.getGotoUrl());
                bundle.putInt(StringConstant.PARAMS_URLTYPE, IntConstant.WEBVIEWURL_TYPE_SHOWTITLE);
                IntentUtils.startActivity(activity, CommonWebViewActivity.class, bundle);
                break;
        }
    }

    /**
     * 是否收藏视频
     *
     * @return 是否发送请求--- false：未登录没发送  true：已登录 发送
     * @boolean collectOption 收藏或者取消的操作（true：收藏操作 false：取消收藏）
     */
    public static boolean collectTask(Context context, String requestUnicode, String taskCode, final boolean collectOption, final CollectCallBack callBack) {
        if (LoginUtils.isLogin(context)) {
            ArtVideoOperationOfCollectingInput entity = new ArtVideoOperationOfCollectingInput();
            entity.collectionID = taskCode;
            if (collectOption) {
                entity.operationType = 1;
            } else {
                entity.operationType = 0;
            }
            RequestUtils.videoCollection(requestUnicode, entity, new SimpleRequestListener() {

                @Override
                public void onHttpRequestComplete(String url, HttpContext httpContext) {
                    BaseResponse result = httpContext.getResponseObject();
                    callBack.onResult(result != null && result.status == 1, collectOption);
                }
            });
            return true;
        } else {
            LoginParams params = new LoginParams(context, LoginEvent.LOGIN);
            CenterEventBus.getInstance().postTask(params);
            return false;
        }
    }

    /**
     * 是否点赞
     *
     */
    public static boolean zanTask(Context context, String requestUnicode, String commentId, final boolean zanOption, final CollectCallBack callBack) {
        if (LoginUtils.isLogin(context)) {
            ArtCommentThumbsUpDownInput entity = new ArtCommentThumbsUpDownInput ();
            entity.commentId = commentId;
            if (zanOption) {
                entity.thumbsType = "1";
            } else {
                entity.thumbsType = "0";
            }
            RequestUtils.videoThumbsUpDown(requestUnicode, entity, new SimpleRequestListener() {

                @Override
                public void onHttpRequestComplete(String url, HttpContext httpContext) {
                    ArtCommentThumbsUpDownResult result = httpContext.getResponseObject();
                    callBack.onResult(result != null && result.status == 1, zanOption);
                }
            });
            return true;
        } else {
            LoginParams params = new LoginParams(context, LoginEvent.LOGIN);
            CenterEventBus.getInstance().postTask(params);
            return false;
        }
    }

    public interface CollectCallBack {
        /**
         * 收藏回调
         *
         * @param collcetResult 是否收藏,点赞成功
         * @param collectOption 是什么操作（true:收藏操作  false:取消收藏操作）
         */
        void onResult(boolean collcetResult, boolean collectOption);
    }
}
