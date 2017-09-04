package com.ichsy.hrys.model.login;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.ichsy.centerbus.CenterEventBus;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.interfaces.OnReceiveOttoEventInterface;
import com.ichsy.hrys.common.utils.IntentUtils;
import com.ichsy.hrys.common.utils.LoginUtils;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.common.utils.UMAnalyticsUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.otto.OttoController;
import com.ichsy.hrys.common.utils.otto.OttoEventEntity;
import com.ichsy.hrys.common.utils.otto.OttoEventType;
import com.ichsy.hrys.common.view.dialog.LoginByVerifyDialogView;
import com.ichsy.hrys.config.config.ClentConfig;
import com.ichsy.hrys.config.config.ServiceConfig;
import com.ichsy.hrys.config.constants.IntConstant;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.AppConfigEntity;
import com.ichsy.hrys.entity.ArtUserInfo;
import com.ichsy.hrys.entity.request.LoginWithWXRequestEntity;
import com.ichsy.hrys.entity.response.LoginResponseEntity;
import com.ichsy.hrys.model.base.BaseActivity;
import com.ichsy.hrys.model.base.CommonWebViewActivity;
import com.squareup.otto.Subscribe;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.io.IOException;
import java.util.Map;

import butterknife.BindView;
import zz.mk.utilslibrary.LogUtil;
import zz.mk.utilslibrary.ScreenUtil;
import zz.mk.utilslibrary.ToastUtils;
import zz.mk.utilslibrary.net.NetUtil;

/**
 * 账号密码登陆界面
 */
public class LoginActivity extends BaseActivity implements LoginByVerifyDialogView.OnLoginByVerifyViewClickListener,MediaPlayer.OnCompletionListener,MediaPlayer.OnErrorListener,MediaPlayer.OnInfoListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnSeekCompleteListener,MediaPlayer.OnVideoSizeChangedListener,SurfaceHolder.Callback,OnReceiveOttoEventInterface {
    /**
     * 登录弹框
     */
    private LoginByVerifyDialogView loginDialog;
    private String openID ;
    private  String accessToken;
    private SurfaceHolder holder;
    private MediaPlayer player;
    private int vWidth,vHeight;

    @BindView(R.id.vv_login_video)
    SurfaceView videoView;

    private String videoViewUrl;

    /**
     * 展示  验证码登陆弹框
     */
    private void showLoginDialog() {
        if (loginDialog  == null){
            loginDialog = new LoginByVerifyDialogView(getContext());
            loginDialog.setOnLoginByVerifyViewClickListener(this);
        }
        loginDialog.show();
    }


    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_login);
        getWindow().setWindowAnimations(R.style.myact);
        OttoController.register(this);
    }

    @Override
    public void loadListener() {
        setClickListeners(R.id.tv_activitylogin_logintext
                ,R.id.ll_activitylogin_loginbywx
                ,R.id.btn_loginactivity_close,R.id.tv_login_agreement);
    }

    @Override
    public void request() {}


    @Override
    public void onViewClick(int viewId) {

        switch (viewId){
            case R.id.tv_activitylogin_logintext:
                showLoginDialog();
                break;
            case R.id.ll_activitylogin_loginbywx:
                if (NetUtil.checkNetWork(context)) {
                    doUMOauth(SHARE_MEDIA.WEIXIN );
                } else {
                    ToastUtils.showShortToast(getString(R.string.string_netconnect_nonet));
                }
                break;
            case R.id.btn_loginactivity_close:
                finish();
                break;
            case R.id.tv_login_agreement:
                Bundle bundle = new Bundle();
                bundle.putString(StringConstant.PARAMS_URL, ClentConfig.AGREEMENT_URL);
                bundle.putInt(StringConstant.PARAMS_URLTYPE, IntConstant.WEBVIEWURL_TYPE_SHOWTITLE);
                IntentUtils.startActivity(getContext(), CommonWebViewActivity.class, bundle);
                break;
            default:
                break;
        }
    }

    @Override
    public void logic() {
        hiddenTitlebar();
        AppConfigEntity loginMovie = SharedPreferencesUtils.getLoginMovie(getContext());
        if(!TextUtils.isEmpty(loginMovie.getLocalUrl())){
            videoViewUrl = loginMovie.getLocalUrl();
        }
        if (!TextUtils.isEmpty(videoViewUrl)){
            initSurfaceView();
        }else{
            findViewById(R.id.iv_login_defaultimg).setVisibility(View.VISIBLE);
        }
    }

    private void initSurfaceView(){
        //给SurfaceView添加CallBack监听
        holder = videoView.getHolder();
        holder.addCallback(this);
        //为了可以播放视频或者使用Camera预览，我们需要指定其Buffer类型
        holder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);

        //下面开始实例化MediaPlayer对象
        player = new MediaPlayer();
        player.setOnCompletionListener(this);
        player.setOnErrorListener(this);
        player.setOnInfoListener(this);
        player.setOnPreparedListener(this);
        player.setOnSeekCompleteListener(this);
        player.setOnVideoSizeChangedListener(this);
        player.setLooping(true);
        //然后指定需要播放文件的路径，初始化MediaPlayer
        try {
            player.setDataSource(videoViewUrl);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //然后，我们取得当前Display对象
    }
    private UMShareAPI umShareAPI;
    /**
     * 第三方授权微信登陆
     * @param plate
     */
    private void doUMOauth(final SHARE_MEDIA plate){

        /**
         * Umeng分享的控制器
         */
        umShareAPI = UMShareAPI.get(this);

        umShareAPI.doOauthVerify(context, plate, new UMAuthListener() {

            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Toast.makeText(context, "授权完成", Toast.LENGTH_SHORT).show();
                loginWithWX(map);
//                umShareAPI.getPlatformInfo(getContext(), plate, new UMAuthListener() {
//                    @Override
//                    public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
//                        Toast.makeText( getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();
//                        String result = data.get("result");
//                        ClipboardManager manager = (ClipboardManager) AppApplication.mContext.getSystemService(Context.CLIPBOARD_SERVICE);
//                        manager.setText(result);
//
//                    }
//
//                    @Override
//                    public void onError(SHARE_MEDIA platform, int action, Throwable t) {
//                        Toast.makeText( getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
//                    }
//
//                    @Override
//                    public void onCancel(SHARE_MEDIA platform, int action) {
//                        Toast.makeText( getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
//                    }
//
//
//                });
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                ToastUtils.showShortToast("授权错误"+throwable.getMessage());
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                ToastUtils.showShortToast("授权取消");
            }

        });
    }
    /**
     * 微信授权完成后  调用接口登录
     */
    private void loginWithWX(Map<String, String> bundle){
        LoginWithWXRequestEntity requestEntity = new LoginWithWXRequestEntity();
        requestEntity.openId = bundle.get("openid").toString();

        accessToken = bundle.get("access_token").toString();
        openID = requestEntity.openId;
        RequestUtils.loginWithWX(getRequestUnicode(),requestEntity,this);
    }

    /**
     * 跳转到绑定手机号的页面
     * @param openId
     */
    private void  toBandPhoneActivity(String openId){
        Bundle bundle = new Bundle();
        bundle.putString(StringConstant.WEIXIN_OPENID,openId);
        bundle.putString(StringConstant.WEIXIN_ACCESSTOKEN,accessToken);
        IntentUtils.startActivity(this,BandPhoneNumActivity.class,bundle);
    }

    @Override
    protected void onStop() {
        super.onStop();
        hiddenLoadingDialog();
    }

    @Override
    public void onCancleClick() {
        loginDialog.dismiss();
    }


    /**
     * 登录成功后保存用户信息 跳转首页
     * @param userInfo
     */
    private void toMainActivity(ArtUserInfo userInfo){
        SharedPreferencesUtils.saveUserMsg(getContext(),userInfo);
//        IntentUtils.startActivity(getContext(),MainActivity.class);
        CenterEventBus.getInstance().revert(LoginManager.class, LoginEvent.LOGIN, true);
    }

    @Override
    public void onHttpRequestSuccess(String url, HttpContext httpContext) {
        super.onHttpRequestSuccess(url, httpContext);
        if (ServiceConfig.LOGINWITHWX.equals(url) && httpContext.getResponseObject() != null){
            LoginResponseEntity responseObject = httpContext.getResponseObject();
            if (responseObject.status == 1){
                if (responseObject.flag == 0){

                    responseObject.userInfo.setUserToken(responseObject.getUserToken());
                    responseObject.userInfo.isLogin = true;
                    if (loginDialog != null){

                        loginDialog.dismiss();
                    }
                    toMainActivity(responseObject.userInfo);
                    UMAnalyticsUtils.onUserLoginIn("wx",responseObject.userInfo.getUserCode());

                }else{

                    //跳转绑定手机号页面
                    toBandPhoneActivity(openID);
                }

            }else{
                ToastUtils.showShortToast(responseObject.getError());
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
    }
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
        // 当Surface尺寸等参数改变时触发
        LogUtil.zLog().e("Surface Change:::surfaceChanged called");
    }
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // 当SurfaceView中的Surface被创建的时候被调用
        //在这里我们指定MediaPlayer在当前的Surface中进行播放
        player.setDisplay(holder);
        //在指定了MediaPlayer播放的容器后，我们就可以使用prepare或者prepareAsync来准备播放了
        player.prepareAsync();

    }
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

        LogUtil.zLog().e("Surface Destory:::surfaceDestroyed called");
    }
    @Override
    public void onVideoSizeChanged(MediaPlayer arg0, int arg1, int arg2) {
        // 当video大小改变时触发
        //这个方法在设置player的source后至少触发一次
        LogUtil.zLog().e("Video Size ChangeonVideoSizeChanged called");

    }
    @Override
    public void onSeekComplete(MediaPlayer arg0) {
        // seek操作完成时触发
        LogUtil.zLog().e("Seek Completion onSeekComplete called");

    }
    @Override
    public void onPrepared(MediaPlayer player) {
        // 当prepare完成后，该方法触发，在这里我们播放视频

        //首先取得video的宽和高
        vWidth = player.getVideoWidth();
        vHeight = player.getVideoHeight();

        DisplayMetrics screenDisplay = ScreenUtil.getScreenDisplay(getContext());
        if(vWidth > screenDisplay.widthPixels || vHeight > screenDisplay.heightPixels){
            //如果video的宽或者高超出了当前屏幕的大小，则要进行缩放

            //选择大的一个进行缩放

            vWidth = screenDisplay.widthPixels;
            vHeight = screenDisplay.heightPixels;

            //设置surfaceView的布局参数
            videoView.setLayoutParams(new RelativeLayout.LayoutParams(vWidth, vHeight));

            //然后开始播放视频
        }

        player.start();
    }
    @Override
    public boolean onInfo(MediaPlayer player, int whatInfo, int extra) {
        // 当一些特定信息出现或者警告时触发
        switch(whatInfo){
            case MediaPlayer.MEDIA_INFO_BAD_INTERLEAVING:
                break;
            case MediaPlayer.MEDIA_INFO_METADATA_UPDATE:
                break;
            case MediaPlayer.MEDIA_INFO_VIDEO_TRACK_LAGGING:
                break;
            case MediaPlayer.MEDIA_INFO_NOT_SEEKABLE:
                break;
        }
        return false;
    }
    @Override
    public boolean onError(MediaPlayer player, int whatError, int extra) {
        LogUtil.zLog().e("Play Error::: onError called");
        switch (whatError) {
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                LogUtil.zLog().e("Play Error::: MEDIA_ERROR_SERVER_DIED");
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                LogUtil.zLog().e("Play Error:::, MEDIA_ERROR_UNKNOWN");
                break;
            default:
                break;
        }
        return false;
    }
    @Override
    public void onCompletion(MediaPlayer player) {
        // 当MediaPlayer播放完成后触发
        LogUtil.zLog().e("Play Over::: onComletion called");
    }
    @Subscribe
    @Override
    public void OnReceiveEvent(OttoEventEntity eventEntity) {
        if (OttoEventType.LOGIN_SUCCESS == eventEntity.getType() ){
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
        if(!LoginUtils.isLogin(getContext())){
            CenterEventBus.getInstance().revert(LoginManager.class, LoginEvent.LOGIN, false);
        }
        OttoController.unregister(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        umShareAPI.onActivityResult(requestCode,resultCode,data);
    }
}
