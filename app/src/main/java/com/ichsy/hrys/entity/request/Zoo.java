package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.AppApplication;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.config.config.ClentConfig;

/**
 * 功能：请求的公用参数
 * ＊创建者：赵然 on 16/5/17 15:40
 */
public class Zoo {
   public  Zoo(){
       key = ClentConfig.API_KEY;
       token = SharedPreferencesUtils.getUserInfo(AppApplication.mContext).getUserToken();
    }
    public String key ;
    public String token;
}
