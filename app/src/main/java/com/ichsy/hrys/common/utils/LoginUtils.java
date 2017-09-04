package com.ichsy.hrys.common.utils;

import android.content.Context;

import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.ArtUserInfo;


/**
 * 登录工具类
 * author: ihesen on 2016/7/4 15:43
 * email: hesen@ichsy.com
 */
public class LoginUtils {

    public static boolean isLogin(Context context) {
        return SharedPreferencesUtils.getUserInfo(context).isLogin;
    }

    /**
     * 该用户是否是达人
     */
    public static boolean isExpertUser(Context context){
        ArtUserInfo userInfo = SharedPreferencesUtils.getUserInfo(context);
        if(userInfo.isLogin){
            return StringConstant.EXPERT_USER_TYPE.equals(userInfo.getUserType());
        }
        return false;
    }
}
