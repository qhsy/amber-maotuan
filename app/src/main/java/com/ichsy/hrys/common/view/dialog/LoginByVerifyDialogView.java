package com.ichsy.hrys.common.view.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ichsy.centerbus.CenterEventBus;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.common.utils.StringUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.http.RequestListener;
import com.ichsy.hrys.config.config.ServiceConfig;
import com.ichsy.hrys.entity.request.GetVerifyCodeRequestEntity;
import com.ichsy.hrys.entity.request.LoginRequestEntity;
import com.ichsy.hrys.entity.response.BaseResponse;
import com.ichsy.hrys.entity.response.GetVerifyCodeResponseEntity;
import com.ichsy.hrys.entity.response.LoginResponseEntity;
import com.ichsy.hrys.model.login.LoginEvent;
import com.ichsy.hrys.model.login.LoginManager;

import zz.mk.utilslibrary.LogUtil;
import zz.mk.utilslibrary.ToastUtils;
import zz.mk.utilslibrary.net.NetUtil;

/**
 * 功能：通过验证码登录的弹框view
 * ＊创建者：赵然 on 16/5/17 11:10
 * ＊
 */
public class LoginByVerifyDialogView extends Dialog implements View.OnClickListener{
    OnLoginByVerifyViewClickListener listener;

    private Context context;
    private Button closeBtn;
    private EditText phoneET;
    private EditText verifyET;
    private Button getVerifyCodeBtn;
    private TextView loginInTv;
    private int timeCount = 60;
    private Handler handler = new Handler();

    private GetVerifyCodeRequestEntity verifyCodeRequestEntity;
    private LoginRequestEntity loginRequestEntity;
    private RequestListener requestListener;
    private String tag = this.toString();
    /**
     * 验证码接口返回的流水号
     */

    private String verifyNumber = "";
    /**
     * 是否获取成功过验证码  false 则点击登陆不处理 弹出提示
     */
    private boolean isGetVerifyCode;

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timeCount--;
            if (getVerifyCodeBtn != null){

                if (timeCount>0) {
                    getVerifyCodeBtn.setText("重新发送(" + timeCount+")");
                    handler.postDelayed(this, 1000);
                }else {
                    getVerifyCodeBtn.setEnabled(true);
                    getVerifyCodeBtn.setText(context.getString(R.string.login_getverify));
                    timeCount=60;
                }
            }
        }
    };


    public LoginByVerifyDialogView(final Context context) {
        super(context,R.style.loginDialog);
        this.context = context;
        setContentView(R.layout.dialog_login_phoneverify);
        initView();
        closeBtn.setOnClickListener(this);
        getVerifyCodeBtn.setOnClickListener(this);
        loginInTv.setOnClickListener(this);
        requestListener = new RequestListener() {
            @Override
            public void onHttpRequestBegin(String url) {

            }

            @Override
            public void onHttpRequestSuccess(String url, HttpContext httpContext) {
                if (httpContext.getResponseObject() != null && ((BaseResponse)httpContext.getResponseObject()).status == 1){
                    switch (url){
                        case ServiceConfig.GETVERIFYCODE:
                            LogUtil.zLog().e("GETVERIFYCODE:success");
                            startTimerCount();
                            verifyNumber = ((GetVerifyCodeResponseEntity)httpContext.getResponseObject()).getVerifyNumber();
                            isGetVerifyCode = true;
                            break;
                        case ServiceConfig.LOGININ:
                            //
                            LoginResponseEntity loginResponseEntity = httpContext.getResponseObject();
                            if (loginResponseEntity.userInfo != null){

                                loginResponseEntity.userInfo.setUserToken(loginResponseEntity.getUserToken());
                                loginResponseEntity.userInfo.isLogin = true;

                                SharedPreferencesUtils.saveUserMsg(context,loginResponseEntity.userInfo);
                                CenterEventBus.getInstance().revert(LoginManager.class, LoginEvent.LOGIN, true);
                                ((Activity) context).finish();
                            }else{
                                ToastUtils.showShortToast(((BaseResponse)httpContext.getResponseObject()).getError());

                            }

                            break;
                        default:
                            break;
                    }
                }else{
                    if (httpContext.getResponseObject() != null){

                        ToastUtils.showShortToast(((BaseResponse)httpContext.getResponseObject()).getError());
                    }else{
                        ToastUtils.showShortToast("返回数据为空");
                    }
                    getVerifyCodeBtn.setText("获取验证码");
                    getVerifyCodeBtn.setEnabled(true);
                    loginInTv.setEnabled(true);
                }

            }

            @Override
            public void onHttpRequestFailed(String url, HttpContext httpContext, Throwable e) {


                if (ServiceConfig.GETVERIFYCODE.equals(url)){
                    getVerifyCodeBtn.setText("获取验证码");
                    getVerifyCodeBtn.setEnabled(true);
                }
            }

            @Override
            public void onHttpRequestComplete(String url, HttpContext httpContext) {

                loginInTv.setEnabled(true);
            }

            @Override
            public void onHttpRequestCancel(String url, HttpContext httpContext) {

            }

            @Override
            public void onHttpResponseCodeException(String url, HttpContext httpContext, int status) {

            }
        };
    }


    private void  initView(){
        LogUtil.zLog().e("TAG: "+tag);
        closeBtn = (Button) findViewById(R.id.btn_dialoglogin_close);
        phoneET = (EditText) findViewById(R.id.et_dialoglogin_phone);
        verifyET = (EditText) findViewById(R.id.ev_dialoglogin_verifycode);
        getVerifyCodeBtn = (Button) findViewById(R.id.btn_dialog_getverify);
        loginInTv = (TextView) findViewById(R.id.tv_dialoglogin_login);
        initStyle();
        verifyCodeRequestEntity = new GetVerifyCodeRequestEntity();
        verifyCodeRequestEntity.type = "maotuan";

        loginRequestEntity = new LoginRequestEntity();

    }
    private void  initStyle(){
        Window window = getWindow();
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, android.view.WindowManager.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_dialoglogin_close:
                if (listener != null){
                    listener.onCancleClick();
                }
                break;
            case R.id.tv_dialoglogin_login:
                if (isGetVerifyCode){
                    loginIn();
                }else{
                    ToastUtils.showShortToast("请先获取验证码");
                }
                break;
            case R.id.btn_dialog_getverify:
                getVerifCode();
                break;
            default:
                break;
        }
    }

    /**
     * 获取验证码的请求
     */
    private void getVerifCode() {
        if (checkPhone()){
            if (NetUtil.checkNetWork(context)) {
                getVerifyCodeBtn.setEnabled(false);
                getVerifyCodeBtn.setText("短信发送中..");
                verifyCodeRequestEntity.phoneNum = phoneET.getText().toString();
                RequestUtils.getVerifyCode(tag,verifyCodeRequestEntity,requestListener);
            }else{
                ToastUtils.showShortToast(context.getString(R.string.string_netconnect_nonet));
            }
        }
    }

    private void loginIn(){
        if (checkPhone() && cheVerify()){

            if (NetUtil.checkNetWork(context)){
                loginInTv.setEnabled(false);
                loginRequestEntity.phoneNum = phoneET.getText().toString();
                loginRequestEntity.verifyCode = verifyET.getText().toString();
                loginRequestEntity.verifyNumber = verifyNumber;
                RequestUtils.loginInByVerifyCode(tag,loginRequestEntity,requestListener);

            }else{
                ToastUtils.showShortToast(context.getString(R.string.string_netconnect_nonet));
            }
        }

    }

    /**
     * 开始倒计时
     */
    private void startTimerCount(){
        timeCount = 60;
        handler.postDelayed(runnable,1000);
    }
    /**
     * 校验手机号
     * @return
     */
    private boolean  checkPhone(){

        boolean isPhoneAvailable = StringUtils.IsValidMobileNo(phoneET.getText().toString());
        if (!isPhoneAvailable){
            ToastUtils.showShortToast(R.string.string_tips_phonenumerror);
        }

        return isPhoneAvailable;
    }
    private boolean cheVerify(){

        if (verifyET.getText().toString().length() == 6 ){
            return true;
        }else{
            ToastUtils.showShortToast("请输入正确的6位数字验证码");
            return false;
        }

    }
    /**
     * View点击的回调
     */
    public interface  OnLoginByVerifyViewClickListener{

        /**
         * 点击关闭弹框的回调
         */
         void onCancleClick();

    }

    public void setOnLoginByVerifyViewClickListener(OnLoginByVerifyViewClickListener listener){

        this.listener = listener;
    }

    @Override
    public void show() {
        reset();
        super.show();
    }

    private void  reset(){

        getVerifyCodeBtn.setEnabled(true);
        getVerifyCodeBtn.setText(context.getString(R.string.login_getverify));
        phoneET.setText("");
        verifyET.setText("");
    }
    @Override
    public void dismiss() {
        handler.removeCallbacks(runnable);
        super.dismiss();
    }
}
