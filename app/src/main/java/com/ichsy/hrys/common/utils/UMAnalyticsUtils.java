package com.ichsy.hrys.common.utils;

import android.content.Context;

import com.ichsy.hrys.config.constants.UMShareConstant;
import com.umeng.analytics.MobclickAgent;

import java.util.Map;

import zz.mk.utilslibrary.LogUtil;
import zz.mk.utilslibrary.system.AppUtils;

/**
 * author: zhu on 2017/4/21 10:33
 * email: mackkill@gmail.com
 */

public class UMAnalyticsUtils {

    public static String channelId = "";
    /**
     * 初始化
     * @param  context
     */
    public static void  initUM(Context context){
        channelId = AppUtils.getChannel(context);
        LogUtil.zLog().e("channel Code : " + channelId);
        MobclickAgent.UMAnalyticsConfig config = new MobclickAgent.UMAnalyticsConfig(
                context
                , UMShareConstant.UMKEY
                , channelId
                , MobclickAgent.EScenarioType.E_UM_NORMAL);
        MobclickAgent.startWithConfigure(config);
        MobclickAgent.openActivityDurationTrack(false);
    }

    /**
     * activity 中调用的开始统计页面时长 统计页面
     * @param context 上下文
     * @param isContainFragment   activity中是否包含fragment 包含则 不统计页面 后续调用其他方法fragment中统计页面
     * @param pageId
     */

    public static void onActivityPageResume(Context context, boolean isContainFragment, String pageId){

        if (!isContainFragment){

            MobclickAgent.onPageStart(pageId);
        }

        MobclickAgent.onResume(context);

    }

    /**
     *   * activity 中调用的结束统计页面时长 统计页面
     * @param context 上下文
     * @param isContainFragment   activity中是否包含fragment 包含则 不统计页面 后续调用其他方法fragment中统计页面
     * @param pageId
     */

    public static void onActivityPagePause(Context context, boolean isContainFragment, String pageId){

        if (!isContainFragment){

            MobclickAgent.onPageEnd(pageId);
        }
        MobclickAgent.onPause(context);

    }

    /**
     *Fragment 中 开始统计页面 （fragment中套fragment的）统计哪个页面哪里调用
     * @param pageId
     */

    public static void  onFragmentPageResume(String pageId){
        MobclickAgent.onPageStart(pageId);
    }
    /**
     **Fragment 中 结束统计页面（fragment中套fragment的）统计哪个页面哪里调用
     * @param pageId
     */

    public static void  onFragmentPagePause(String pageId){
        MobclickAgent.onPageEnd(pageId);
    }

    /**
     * 简单的事件统计
     * @param context
     * @param eventId
     */
    public static void onEvent(Context context, String eventId){
        MobclickAgent.onEvent(context,eventId);
    }

    public static void onEvent(Context context, String eventId, Map<String, String> map){
        MobclickAgent.onEvent(context,eventId, map);
    }

    /**
     * 统计登录
     * @param from
     * @param userCode
     */
    public static void onUserLoginIn(String from, String userCode){
        MobclickAgent.onProfileSignIn(userCode);
    }
    public static void onUserLoginOut(){
        MobclickAgent.onProfileSignOff();
    }

    /**
     * 如果开发者调用Process.kill或者System.exit之类的方法杀死进程，请务必在此之前调用此方法，用来保存统计数据
     */
    public static void onKillProcess(Context context) {
        MobclickAgent.onKillProcess(context);
    }
}
