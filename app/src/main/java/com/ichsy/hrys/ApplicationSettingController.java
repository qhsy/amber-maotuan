package com.ichsy.hrys;


import com.ichsy.hrys.config.config.ClentConfig;

import okhttp3.logging.HttpLoggingInterceptor;
import zz.mk.utilslibrary.LogUtil;

//import okhttp3.logging.HttpLoggingInterceptor;

//http://beta-amber.ntw.srnpr.com/api/artUserController/getMinsnsInfo
// http://beta-artcloud.ntw.srnpr.com/api/artUserController/getMinsnsInfo

/**
 * App配置
 */
public class ApplicationSettingController {

    /**
     * 打包环境
     */
    public static final PackMode package_mode = PackMode.PRODUCT_TEST;
    /**
     * bugly是否可用
     */
    public static boolean buglyEnable = false;
    /**百度推送调试开关*/
    public static boolean pushDebug = false;
    public static HttpLoggingInterceptor.Level HTTPLOGLEVEL = HttpLoggingInterceptor.Level.BODY;

    // 打包环境
    public enum PackMode {
        BETA, //
        PRODUCT_TEST, //
        PRODUCT //
    }

    /**
     * 设置打包环境
     */
    public static void setPackMode() {
        switch (package_mode) {
            case BETA:
//                ClentConfig.BASE_URL = "http://beta-artcloud.ntw.srnpr.com";
                ClentConfig.BASE_URL = "https://amber-beta-manage.srnpr.com";
                ClentConfig.AGREEMENT_URL = "http://amber-beta-manage.srnpr.com/webjars/artstatic/agreementApp.html";
                HTTPLOGLEVEL = HttpLoggingInterceptor.Level.BODY;
                LogUtil.logFlag = true;
                buglyEnable = true;
                pushDebug = true;
                break;
            case PRODUCT_TEST:
                ClentConfig.BASE_URL = "https://art-api-001.iqhsy.com";
                ClentConfig.AGREEMENT_URL = "http://art-html.iqhsy.com/webjars/artstatic/agreementApp.html";
                HTTPLOGLEVEL = HttpLoggingInterceptor.Level.BODY;
                LogUtil.logFlag = true;
                buglyEnable = true;
                pushDebug = true;
                break;
            case PRODUCT:
                ClentConfig.BASE_URL = "https://art-api-001.iqhsy.com";
                ClentConfig.AGREEMENT_URL = "http://art-html.iqhsy.com/webjars/artstatic/agreementApp.html";
                HTTPLOGLEVEL = HttpLoggingInterceptor.Level.NONE;
                LogUtil.logFlag = false;
                buglyEnable = false;
                pushDebug = false;
                break;
        }
    }

}