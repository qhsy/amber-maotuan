package com.ichsy.hrys;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.ichsy.centerbus.CenterEventBus;
import com.ichsy.hrys.common.utils.Bus.IntentBus;
import com.ichsy.hrys.common.utils.UMAnalyticsUtils;
import com.ichsy.hrys.config.constants.UMShareConstant;
import com.ichsy.hrys.model.login.LoginActivity;
import com.ichsy.hrys.model.login.LoginManager;
import com.tencent.bugly.crashreport.CrashReport;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import zz.mk.utilslibrary.ToastUtils;
import zz.mk.utilslibrary.Utils;


/**

                   _ooOoo_
                  o8888888o
                  88" . "88
                  (| ^_^ |)
                  O\  ∀  /O
               ____/`---'\____
             .'  \\|     |//  `.
            /  \\|||  :  |||//  \
           /  _||||| -:- |||||-  \
           |   | \\\  -  /// |   |
           | \_|  ''\---/''  |   |
           \  .-\__  `-`  ___/-. /
         ___`. .'  /--.--\  `. . __
      ."" '<  `.___\_<|>_/___.'  >'"".
     | | :  `- \`.;`\ _ /`;.`/ - ` : | |
     \  \ `-.   \_ __\ /__ _/   .-` /  /
======`-.____`-.___\_____/___.-`____.-'======
                   `=---='
^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
            佛祖保佑       永无BUG
*/
public class AppApplication extends Application {

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        init();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    private void init() {

        Utils.init(this);
        ToastUtils.init(false);
//        LeakCanary.install(this);
        UMAnalyticsUtils.initUM(mContext);
//        Config.REDIRECT_URL="http://sns.whalecloud.com/sina2/callback";
        ApplicationSettingController.setPackMode();

//        PushSettings.enableDebugMode(this, pushDebug);
//        PushManager.startWork(getApplicationContext(), PushConstants.LOGIN_TYPE_API_KEY, BaiDuConstant.API_KEY);
        //集成bugly
        CrashReport.initCrashReport(getApplicationContext(), "291e20560c", ApplicationSettingController.buglyEnable);
//        HotFixUtils.getInstance().init(mContext);
        initBus();
        initCenterBus();

        UMShareAPI.get(this);
        PlatformConfig.setWeixin(UMShareConstant.WXAPPID, UMShareConstant.WXAPPSECRET);
        // 微信 appid appsecret
        PlatformConfig.setQQZone(UMShareConstant.QQAPPID, UMShareConstant.QQAPPKEY);
        // 新浪微博 appkey appsecret
        PlatformConfig.setSinaWeibo(UMShareConstant.SINAAPPKEY,UMShareConstant.SINAAPPSECTET, null);
        // QQ和Qzone appid appkey

    }
    private void  initBus(){

        addNeedLoginActivity(
//                OrderListActivity.class
//                ,MyCollectionsActivity.class
//        , MyNotifactionActivity.class
//        , MyMinsnsActivity.class
//        , AddressActivity.class
//        , MyUserInfoActivity.class
//        , TaskManagerActivity.class
//                , GeekDetailActivity.class
//                , MyTeamActivitiy.class
//                ,MyPriceActivity.class
//                ,ContactTaActivity.class
        );
    }

    /**
     * 添加需要跳转登录的界面的
     * @param clazz
     */
    private void addNeedLoginActivity(Class... clazz){
        for (Class cls: clazz){
            IntentBus.getInstance().addNeedToBeforeActivity(cls, LoginActivity.class);
        }
    }

    private void initCenterBus(){
        CenterEventBus.getInstance().registers(new Class[]{LoginManager.class});
    }

    public static Context getAppContext() {
        return mContext;
    }
}
