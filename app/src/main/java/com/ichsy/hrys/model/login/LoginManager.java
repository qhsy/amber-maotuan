package com.ichsy.hrys.model.login;

import android.content.Intent;

import com.ichsy.centerbus.CenterEventBus;
import com.ichsy.centerbus.CenterManager;
import com.ichsy.hrys.MainActivity;
import com.ichsy.hrys.common.utils.LoginUtils;
import com.ichsy.hrys.common.utils.otto.OttoController;
import com.ichsy.hrys.common.utils.otto.OttoEventType;

/**
 * 登录模块相关事件
 * author: ihesen on 2016/7/4 16:13
 * email: hesen@ichsy.com
 */
public class LoginManager extends CenterManager<LoginParams> {

    private LoginParams mParams;

    @Override
    public void onEvent(LoginParams params) {
        this.mParams = params;
        if (LoginEvent.LOGIN.equals(params.getEventName())) {
            login();
        }
    }

    @Override
    public void onRevert(String eventName, Object params) {
        if (LoginEvent.LOGIN.equals(eventName)) {
            //登录操作完成
            loginComplated((Boolean) params);
        }
    }

    private void login() {
        if (!LoginUtils.isLogin(mParams.getContext())) {
            //未登录
            Intent intent = new Intent(mParams.getContext(), LoginActivity.class);
            mParams.getContext().startActivity(intent);
        } else {
            mParams.isFirstLogin = false;
            //登录
            loginComplated(true);
        }
    }

    /**
     * 登录操作完成
     *
     * @param loginSuccess 登录成功/失败
     */
    private void loginComplated(boolean loginSuccess) {
        if (mParams != null  && mParams.goToMainPage) {
            Intent intent = new Intent(mParams.getContext(), MainActivity.class);
            mParams.getContext().startActivity(intent);
            CenterEventBus.getInstance().remverCallBack(mParams);
            return;
        }else if(mParams == null ){
            return ;

        }
        if (loginSuccess) {
            OttoController.post("", OttoEventType.LOGIN_SUCCESS, "");
            //v1.1.6 登录成功 不需要弹框了
//            if (mParams.isFirstLogin && !LoginUtils.isExpertUser(mParams.getContext())) {
//                verifyExpert();
//            } else {
                CenterEventBus.getInstance().complated(mParams);
//            }
        } else {
            CenterEventBus.getInstance().remverCallBack(mParams);
        }
    }
}
