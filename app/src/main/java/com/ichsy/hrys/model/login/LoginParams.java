package com.ichsy.hrys.model.login;

import android.content.Context;

import com.ichsy.centerbus.CenterParams;

/**
 * author: ihesen on 2016/7/4 16:14
 * email: hesen@ichsy.com
 */
public class LoginParams extends CenterParams<LoginManager> {

    /**
     * 用于判断是否是第一次登陆成功，弹出达人推荐码对话框
     */
    public boolean isFirstLogin = true;

    /**
     * 标识登录成功后，需要进入，主要处理801账号异常重新登录后进入MainActivity(默认不是)
     */
    public boolean goToMainPage = false;

    public LoginParams(Context context, String eventName) {
        super(context, eventName);
    }

    @Override
    public Class<LoginManager> getManagerClass() {
        return LoginManager.class;
    }
}
