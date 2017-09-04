package com.ichsy.hrys.model.login;

import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;

import com.ichsy.centerbus.CenterEventBus;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.LoginUtils;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.common.utils.StringUtils;
import com.ichsy.hrys.common.utils.UMAnalyticsUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.config.config.ServiceConfig;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.request.BandPhoneRequestEntity;
import com.ichsy.hrys.entity.request.GetVerifyCodeRequestEntity;
import com.ichsy.hrys.entity.response.BaseResponse;
import com.ichsy.hrys.entity.response.GetVerifyCodeResponseEntity;
import com.ichsy.hrys.entity.response.LoginResponseEntity;
import com.ichsy.hrys.model.base.BaseActivity;

import butterknife.BindView;
import zz.mk.utilslibrary.ToastUtils;


/**
 * 绑定手机号页面
 * 创建人:赵然
 */
public class BandPhoneNumActivity extends BaseActivity {
    @BindView(R.id.btn_activitybandphone_getverify)
    Button getVerifyBtn;

    @BindView(R.id.et_activitybandphone_phone)
    EditText phoneET;

    @BindView(R.id.ev_activitybandphone_verifycode)
    EditText verifyET;


    private boolean isGetVerifyCode = false;
    /**
     * 微信关联ID
     */
    private String openId;
    /**
     * access token
     */
    private String accessToken;
    private int timeCount = 60;


    private GetVerifyCodeRequestEntity getVerifyCodeRequestEntity;
    private BandPhoneRequestEntity bandPhoneRequestEntity;

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            timeCount--;
            if (getVerifyBtn != null){

                if (timeCount>0) {
                    getVerifyBtn.setText("重新发送(" + timeCount+")");
                    handler.postDelayed(this, 1000);
                }else {
                    getVerifyBtn.setEnabled(true);
                    getVerifyBtn.setText(context.getString(R.string.login_getverify));
                }
            }
        }
    };

    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_band_phone_num);
    }

    @Override
    public void logic() {
        setCenterTitleText("绑定手机号");
        if (getIntent() != null){
            openId = getIntent().getStringExtra(StringConstant.WEIXIN_OPENID);
            accessToken = getIntent().getStringExtra(StringConstant.WEIXIN_ACCESSTOKEN);
        }
        getVerifyCodeRequestEntity = new GetVerifyCodeRequestEntity();
        getVerifyCodeRequestEntity.type = "weChatlogin";

        bandPhoneRequestEntity = new BandPhoneRequestEntity();
        bandPhoneRequestEntity.openId = openId;
        bandPhoneRequestEntity.accessToken = accessToken;
    }

    @Override
    public void loadListener() {

        setClickListeners(getVerifyBtn);
        setClickListeners(R.id.tv_activitybandphone_login);
    }

    @Override
    public void request() {

    }

    @Override
    public void onViewClick(int viewId) {

        switch (viewId){
            case R.id.tv_activitybandphone_login:
                // 登录
                if (isGetVerifyCode){
                    loginIn();

                }else{
                    ToastUtils.showShortToast("请先获取验证码");
                }
                break;
            case R.id.btn_activitybandphone_getverify:
                //  获取验证码
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
            if (checkNet()) {
                // 发送获取验证码的请求
                getVerifyCodeRequestEntity.phoneNum = phoneET.getText().toString();
                RequestUtils.getVerifyCode(getRequestUnicode(),getVerifyCodeRequestEntity,this);
                showLoadingDialog(false);
            }
        }
    }

    private void loginIn(){
        if (checkPhone() && cheVerify()){

            if (checkNet()){

                showLoadingDialog(false);
                // 发送绑定手机号的请求
                bandPhoneRequestEntity.phoneNum = phoneET.getText().toString();
                bandPhoneRequestEntity.verifyCode = verifyET.getText().toString();
                RequestUtils.bandPhone(getRequestUnicode(),bandPhoneRequestEntity,this);

            }
        }

    }

    /**
     * 开始倒计时
     */
    private void startTimerCount(){
        timeCount = 60;
        getVerifyBtn.setEnabled(false);
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
        }else{
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

    @Override
    protected void onDestroy() {
        handler.removeCallbacks(runnable);
        if(!LoginUtils.isLogin(getContext())){
            CenterEventBus.getInstance().revert(LoginManager.class, LoginEvent.LOGIN, false);
        }
        super.onDestroy();
    }

    @Override
    public void onHttpRequestSuccess(String url, HttpContext httpContext) {
        super.onHttpRequestSuccess(url, httpContext);
        if (ServiceConfig.GETVERIFYCODE.equals(url)){
            //获取验证码
            if (httpContext.getResponseObject() != null  && ((BaseResponse)httpContext.getResponseObject()).status == 1){
                startTimerCount();
                isGetVerifyCode = true;

                bandPhoneRequestEntity.verifyNumber =((GetVerifyCodeResponseEntity)httpContext.getResponseObject()).getVerifyNumber();
            }else{
                ToastUtils.showShortToast(((BaseResponse)httpContext.getResponseObject()).getError());
            }
        }else if(ServiceConfig.BANDPHONE.equals(url)){
            //绑定手机号
            if (httpContext.getResponseObject() != null  ){
                LoginResponseEntity responseObject = httpContext.getResponseObject();
                if (responseObject.status == 1){

                    responseObject.userInfo.setUserToken(responseObject.getUserToken());
                    UMAnalyticsUtils.onUserLoginIn("wx",responseObject.userInfo.getUserCode());
                    ////TODO affected
                    responseObject.userInfo.isLogin = true;
                    SharedPreferencesUtils.saveUserMsg(getContext(),responseObject.userInfo);
                    finish();
                    CenterEventBus.getInstance().revert(LoginManager.class, LoginEvent.LOGIN, true);
                }else{
                    ToastUtils.showShortToast(responseObject.getError());
                }
            }

        }
    }

    @Override
    public void onHttpRequestFailed(String url, HttpContext httpContext, Throwable e) {
        super.onHttpRequestFailed(url, httpContext, e);
    }

    @Override
    public void onHttpRequestComplete(String url, HttpContext httpContext) {
        super.onHttpRequestComplete(url, httpContext);
        if (ServiceConfig.GETVERIFYCODE.equals(url)){

            getVerifyBtn.setEnabled(true);
            //获取验证码
        }
        hiddenLoadingDialog();
    }
}
