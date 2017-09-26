package com.ichsy.hrys.model.splash;

import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.ichsy.hrys.MainActivity;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.imageloadutils.ImageLoaderUtils;
import com.ichsy.hrys.common.view.UpdateProgressDialogView;
import com.ichsy.hrys.config.config.ServiceConfig;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.AppConfigEntity;
import com.ichsy.hrys.entity.ArtUserInfo;
import com.ichsy.hrys.entity.PushEntity;
import com.ichsy.hrys.entity.request.GetAppConfigRequestEntity;
import com.ichsy.hrys.entity.response.BaseResponse;
import com.ichsy.hrys.entity.response.CheckUpdateResponseEntity;
import com.ichsy.hrys.entity.response.GetAppConfigResponseEntity;
import com.ichsy.hrys.model.base.BaseActivity;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import zz.mk.utilslibrary.LogUtil;
import zz.mk.utilslibrary.net.NetUtil;
import zz.mk.utilslibrary.system.AppUtils;

/**
 * splash页面
 */
public class SplashActivity extends BaseActivity {


    private static final int LOADMAINUI = 0;
    private static final int SECONDEPAGE = LOADMAINUI + 1;
    private static boolean isInterrupt;
    MyHandler mHandler;

    @BindView(R.id.sdv_splash_secondimage)
    ImageView secondPage;

    static class MyHandler extends Handler {
        WeakReference<SplashActivity> mActivity;

        public MyHandler(SplashActivity activity) {
            mActivity = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SplashActivity activity = mActivity.get();
            if (activity != null) {
                switch (msg.what) {
                    case LOADMAINUI:
                        if (!isInterrupt) {
                            activity.loadMainUi();
                        }
                        break;
                    case SECONDEPAGE:
                        //展示第二张图
                        if (activity.secondPage != null && !AppUtils.isDestroy(activity)) {
                            activity.secondPage.setVisibility(View.VISIBLE);
                            AppConfigEntity loadingInfo = SharedPreferencesUtils.getLoadingInfo(activity);

                            if (TextUtils.isEmpty(loadingInfo.getLocalUrl())) {

                                ImageLoaderUtils.loadViewImage(activity, activity.secondPage, loadingInfo.getConfigUrl(), R.drawable.splash);
                                ImageLoaderUtils.downloadImage(activity, loadingInfo.getConfigUrl(), null);
                            } else {
                                ImageLoaderUtils.loadViewImage(activity, activity.secondPage, loadingInfo.getLocalUrl(), R.drawable.splash);
                            }

                        }
                        Message message = new Message();
                        message.what = LOADMAINUI;
                        activity.mHandler.sendMessageDelayed(message, 2000);
                        break;
                    default:
                        break;

                }
            }
        }
    }

    @Override
    public void loadLayout() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
//        String path = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator +"youxi/101.apatch";
//        if (RichEditorController.isExist(path));
//             HotFixUtils.getInstance().loadPatch(path);
//        ToastUtils.showMessage(getContext(), AppUtils.getScreen(getContext()));
//        ToastUtils.showMessage(getContext(), AppUtils.getScreen(getContext()));
/**
 * 浏览器打开APP
 * 后续添加  跳转规则
 */
        dealWebIntent();
    }

    public void loadMainUi() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * 处理浏览器打开APP 跳转
     */
    private void dealWebIntent() {
        Intent webIntent = getIntent();
        String action = webIntent.getAction();

        if (Intent.ACTION_VIEW.equals(action)) {
            Uri uri = webIntent.getData();
            if (uri != null) {
                String type = uri.getQueryParameter("operationType");
                String params = uri.getQueryParameter("operationParams");
                LogUtil.zLog().e("push1   operationType:" + type + "operationParams:" + params);
                if ("taskContentDetail".equals(type)) {
                    PushEntity push = SharedPreferencesUtils.getPush(context);
                    push.setParams(params);
                    /** 跳转内容任务详情*/
                    push.setType("5");
                    SharedPreferencesUtils.savePush(context, push);
                }
            }
        }
    }

    @Override
    public void logic() {
        hiddenTitlebar();
        mHandler = new MyHandler(this);
        Message msg = new Message();
        msg.what = SECONDEPAGE;
        mHandler.sendMessageDelayed(msg, 3000);

        //TODO
        ArtUserInfo userInfo = SharedPreferencesUtils.getUserInfo(getContext());
        if (!TextUtils.isEmpty(userInfo.getUserToken()) && !userInfo.isLogin) {
            LogUtil.zLog().e("login splash");
            userInfo.isLogin = true;
            SharedPreferencesUtils.saveUserMsg(getContext(), userInfo);
        }
    }

    @Override
    public void loadListener() {

    }

    @Override
    public void request() {

        if (NetUtil.checkNetWork(getContext())) {
            getUpdate();
            getAppConfig();
        } else {
            CheckUpdateResponseEntity responseObject = new CheckUpdateResponseEntity();
            responseObject.setUpgradeType(StringConstant.UPDATE_FAILURE);
            SharedPreferencesUtils.saveAppUpdate(getContext(), responseObject);
        }

    }

    @Override
    public void onViewClick(int viewId) {

    }

    /**
     * 检查更新
     */
    private void getUpdate() {
        SplashCntroller.checkUpdate(getContext(), getRequestUnicode(), this);
    }

    /**
     * 获取配置信息
     */
    private void getAppConfig() {
        GetAppConfigRequestEntity artConfiGuration = new GetAppConfigRequestEntity();
        artConfiGuration.artConfiGuration.add(SharedPreferencesUtils.getLoadingInfo(getContext()));
        artConfiGuration.artConfiGuration.add(SharedPreferencesUtils.getLoginMovie(getContext()));
        artConfiGuration.artConfiGuration.add(SharedPreferencesUtils.getPatchInfo(getContext()));
        AppConfigEntity phoneEntiy = new AppConfigEntity();
        phoneEntiy.setConfigType(StringConstant.TASK_SERVICE_PHONE);
        artConfiGuration.artConfiGuration.add(phoneEntiy);
        AppConfigEntity redPersonServicePhone = new AppConfigEntity();
        redPersonServicePhone.setConfigType(StringConstant.RED_PERSON_SERVICE_PHONE);
        artConfiGuration.artConfiGuration.add(redPersonServicePhone);
        RequestUtils.getAppConfig(getRequestUnicode(), artConfiGuration, this);
    }

    @Override
    public void onHttpRequestSuccess(String url, HttpContext httpContext) {
        super.onHttpRequestSuccess(url, httpContext);
        if (ServiceConfig.GETAPPCONFIG.equals(url)) {
//            dealConfig(getConfigTestData());
            LogUtil.zLog().e("config  no---success");
            if (httpContext.getResponseObject() != null && ((BaseResponse) httpContext.getResponseObject()).status == 1) {
                GetAppConfigResponseEntity configResponseEntity = httpContext.getResponseObject();
                if (configResponseEntity.getArtConfiGuration() != null && configResponseEntity.getArtConfiGuration().size() > 0) {

                    dealConfig(configResponseEntity.getArtConfiGuration());
                }
            }

        } else if (ServiceConfig.CHECKUPDATE.equals(url)) {
            if (httpContext.getResponseObject() != null && ((BaseResponse) httpContext.getResponseObject()).status == 1) {
//                String downloadUrl = "http://pkg.fir.im/e7220aec132b78860bf603e41161048840ac2243.apk?attname=amber_debug.apk_1.0.0.apk&e=1465150484&token=LOvmia8oXF4xnLh0IdH05XMYpH6ENHNpARlmPc-T:XQnqH6v8kTATBkap3OA8NW9RtKk=";

//                CheckUpdateResponseEntity responseObject =  httpContext.getResponseObject();
//                responseObject.setAppUrl(downloadUrl);
                isInterrupt = SplashCntroller.dealUpdate(getContext(), (CheckUpdateResponseEntity) httpContext.getResponseObject(), (UpdateProgressDialogView) findViewById(R.id.updv_splash_pb), true);
            }

        }
    }

    @Override
    public void onHttpRequestFailed(String url, HttpContext httpContext, Throwable e) {
        super.onHttpRequestFailed(url, httpContext, e);
        if (ServiceConfig.CHECKUPDATE.equals(url)) {

            CheckUpdateResponseEntity responseObject = new CheckUpdateResponseEntity();
            responseObject.setUpgradeType(StringConstant.UPDATE_FAILURE);
            SharedPreferencesUtils.saveAppUpdate(getContext(), responseObject);
        }
    }

    @Override
    public void onHttpRequestComplete(String url, HttpContext httpContext) {
        super.onHttpRequestComplete(url, httpContext);
    }

    /**
     * 处理 配置接口返回数据
     *
     * @param configEntities
     */
    private void dealConfig(List<AppConfigEntity> configEntities) {


        for (int i = 0; i < configEntities.size(); i++) {
            AppConfigEntity appConfigEntity = configEntities.get(i);
            switch (appConfigEntity.getConfigType()) {
                case StringConstant.LOADING_PAGE:
                    if (!TextUtils.isEmpty(appConfigEntity.getConfigUrl())) {
                        //加载图
//                        ImageLoaderUtils.loadImage(appConfigEntity.getConfigUrl(),getContext());
                        SharedPreferencesUtils.setLoadingInfo(getContext(), appConfigEntity);
                    }

                    break;
                case StringConstant.LOGIN_MOVICE:
                    if (!TextUtils.isEmpty(appConfigEntity.getConfigContent())) {
                        //下载视频
                        SplashCntroller.loadMovie(getContext(), appConfigEntity);
                    }
                    break;
                case StringConstant.PATCH:
//                    if (!TextUtils.isEmpty(appConfigEntity.getConfigContent())) {
//                        SplashCntroller.loadPatch(getContext(), appConfigEntity);
//                    }
                    break;
                case StringConstant.TASK_SERVICE_PHONE:
                    SharedPreferencesUtils.putValueToSp(getContext(), StringConstant.TASK_SERVICE_PHONE, appConfigEntity.getConfigContent());
                    break;
                case StringConstant.REACTNATIVE_STATUS:
                    SharedPreferencesUtils.putValueToSp(getContext(), StringConstant.REACTNATIVE_STATUS, "dzsd4699100110010001".equals(appConfigEntity.getStats()));
                    break;
                case StringConstant.RED_PERSON_SERVICE_PHONE:
                    SharedPreferencesUtils.putValueToSp(getContext(), StringConstant.RED_PERSON_SERVICE_PHONE, appConfigEntity.getConfigContent());
                    break;
                default:
                    break;
            }

        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
