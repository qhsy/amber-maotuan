package com.ichsy.hrys.model.base;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.ichsy.centerbus.CenterEventBus;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.interfaces.ActivityInterface;
import com.ichsy.hrys.common.utils.Bus.IntentBus;
import com.ichsy.hrys.common.utils.ProgressDialogUtils;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.common.utils.UMAnalyticsUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.http.RequestListener;
import com.ichsy.hrys.common.view.CommonExceptionView;
import com.ichsy.hrys.common.view.CustomTittleView;
import com.ichsy.hrys.common.view.dialog.CommonDialog;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.response.BaseResponse;
import com.ichsy.hrys.model.login.LoginEvent;
import com.ichsy.hrys.model.login.LoginParams;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import zz.mk.utilslibrary.ToastUtils;
import zz.mk.utilslibrary.net.NetUtil;


/**
 * author: zhu on 2017/4/12 17:40
 * email: mackkilled@gmail.com
 */

public abstract class BaseActivity extends AppCompatActivity implements ActivityInterface, RequestListener {
    private CustomTittleView customTittleView;
    /**
     * 异常view
     */
    private CommonExceptionView exceptionView;

    private String reQuestUnicode = this.toString();

    private ViewGroup mGroupContent = null;// add activity's content
    protected Activity context;

    // 友盟统计中页面对应的ID
    private String pageId;

    // 友盟统计中页面是否只含有activity，不包含fragment
    private boolean isContainFragment = true;
    // 当前页面是否打开UM统计
    private boolean isOpenUMAnalyse = false;

    /**
     * 加载框
     */
    ProgressDialog progressDialog;

    private Disposable disposable;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
//        gcAndFinalize();
        context = this;
        setCustomerView(R.layout.activity_base);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 设置禁止横屏
        init();
        IntentBus.getInstance().setCurrentViewID(getIntent().getStringExtra(StringConstant.EVENTCODE)
                ,this.toString()
                ,getIntent().getStringExtra(StringConstant.PARENTEVENTCODE));

    }

    public Activity getContext(){
        return context;
    }


    private void init() {
        loadLayout();
        logic();
        loadListener();
        request();
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        View view = LayoutInflater.from(this).inflate(layoutResID, mGroupContent, false);
        setContentView(view);
    }

    @Override
    public void setContentView(View view) {
        mGroupContent.addView(view);
        ButterKnife.bind(this);
    }

    protected void setCustomerView(int res) {
        super.setContentView(res);
        initTitleBarAndExceptionView();
    }
    private void initTitleBarAndExceptionView() {
        mGroupContent = (ViewGroup)findViewById(R.id.activity_content);
        customTittleView = (CustomTittleView) findViewById(R.id.title_bar);
        exceptionView = (CommonExceptionView)findViewById(R.id.cev_base_exception);

//        exceptionView = (CommonExceptionView)findViewById(R.id.cev_base_exception);
//        exceptionView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                request();
//            }
//        });
    }

    /**
     * 异常布局中得无网界面 一样故 统一封装  点击直接调用request方法
     * @param isClickWithRequestMethed  点击无网布局是否调用 request防范  true: 调用  false 自己继续写点击事件
     */
    public CommonExceptionView showCommonNoNet(boolean isClickWithRequestMethed){
        exceptionView.setVisibility(View.VISIBLE);
        exceptionView.getExceptionIcon().setBackgroundResource(R.drawable.icon_nonet);
        exceptionView.getExceptionTextView().setText(getString(R.string.string_netconnect_nonet));
        if (isClickWithRequestMethed) {
            exceptionView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    request();
                }
            });
        }
        return exceptionView;
    }

    /**
     * 无数据布局
     * @param tips  无数据布局的提示语
     */
    public CommonExceptionView showCommonNoData(String tips){
        exceptionView.setVisibility(View.VISIBLE);
        exceptionView.getExceptionIcon().setVisibility(View.GONE);
        exceptionView.getExctptionButton().setVisibility(View.GONE);
        exceptionView.getExceptionTextView().setText(tips);
        return exceptionView;
    }

    /**
     * 自定义图片及提示异常页面
     * @param icon
     *            异常图片
     * @param hint
     *            文字提示
     * @param  listener  点击布局的监听事件
     */
    public void showCommonExceptionLay(int icon, String hint,View.OnClickListener listener) {
        exceptionView.setVisibility(View.VISIBLE);
        exceptionView.getExceptionIcon().setBackgroundResource(icon);
        exceptionView.getExceptionTextView().setText(hint);
        exceptionView.setOnClickListener(listener);
    }

    /**
     * 隐藏异常布局
     */
    public void hiddenCommonException(){

        exceptionView.setVisibility(View.GONE);
    }

    /**
     * 设置 tittle 中间文字
     * @param title
     */
    public void setCenterTitleText(String title) {
        customTittleView.setCenterText(title);
    }

    /**
     * 设置左侧的文字
     * @param content
     */
    public void setLeftTittleText(String content) {
        customTittleView.setLeftText(content);
    }

    /**
     * 左侧的图片
     * @param drawableID 图片对应的ID
     */
    public void setLeftDrawable(int drawableID) {
        customTittleView.setTextViewDrawableResource(0,drawableID,0); // 设置左图标
    }

    /**
     * 设置右侧的文本
     * @param content   文本内容
     */
    public void setRightTitleText(String content) {
        customTittleView.setRightText(content);
    }

    /**
     * 设置右侧文本的图片 默认图片在左侧
     * @param drawableID 图片对应的ID
     */
    public void setRightDrawable(int drawableID) {
        customTittleView.setTextViewDrawableResource(2,drawableID,0); // 设置左图标
    }

    /**
     * 获取customTittleView中间部分的textview
     * @return
     */
    public TextView getCenterTittleView(){
        return customTittleView.getCenterView();
    }
    /**
     * 获取tittle中左侧的部分的textview
     * @return
     */
    public TextView getLeftTittleView(){
        return customTittleView.getLeftView();
    }
    /**
     * 获取右侧的view
     * @return
     */
    public TextView getRightTittleView(){
        return customTittleView.getRightView();
    }

    /**
     * 设置背景色
     * @param colorId
     */
    public void setBackgroundColor(int colorId){
        customTittleView.setViewBackgroundColor(colorId);
    }
    /**
     * 设置右标题字体颜色
     * @return
     */
    public void setCenterTitleColor(int color){
        customTittleView.getCenterView().setTextColor(color);
    }

    /**
     * 隐藏tittle
     */
    public void hiddenTitlebar(){
        if (customTittleView != null && customTittleView.getVisibility() == View.VISIBLE) {
            customTittleView.setVisibility(View.GONE);
        }
    }

    /**
     * 显示标题栏
     */
    public void showTitlebar(){
        if (customTittleView != null && customTittleView.getVisibility() == View.GONE) {
            customTittleView.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 显示默认的普通tittle   左侧返回按钮 点击返回  中间展示文字
     * @param centreTittleText
     */
    protected  void showDefaultTittle(String centreTittleText){
        setCenterTitleText(centreTittleText);
        setLeftDrawable(R.mipmap.icon_back_black);

        setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HiddenKeyBoard();
                finish();
            }
        });
    }

    /**
     * 设置左侧tittle文本的点击事件
     * @param listener
     */
    public void setLeftClickListener(View.OnClickListener listener){
        if (listener != null){
            customTittleView.getLeftView().setOnClickListener(listener);
        }
    }

    /**
     * 设置左侧tittle文本的点击事件
     * @param listener
     */
    public void setRightClickListener(View.OnClickListener listener){
        if (listener != null){
            customTittleView.getRightView().setOnClickListener(listener);
        }
    }

    protected void setClickListeners(int... ids) {

        for (final int id : ids) {

            RxView.clicks(findViewById(id))
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(@NonNull Object o) throws Exception {
                            onViewClick(id);
                        }
                    });
        }
    }
    protected void setClickListeners(View... views) {

        for ( final View view : views) {

            RxView.clicks(view)
                    .throttleFirst(1000, TimeUnit.MILLISECONDS)
                    .subscribe(new Consumer<Object>() {
                        @Override
                        public void accept(@NonNull Object o) throws Exception {
                            onViewClick(view.getId());
                        }
                    });
        }
    }

    // 替换fragment
    protected void startFragment(Fragment fragment, int ResId) {
        replaceFragment(fragment, fragment.getClass().getSimpleName(), ResId);
    }

    protected void replaceFragment(Fragment fragment, String tag,int replaceLayoutId) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        Fragment oldFragment = fragmentManager.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        // fragmentTransaction.setCustomAnimations(R.anim.anim_enter,R.anim.anim_exit);
        if (null == oldFragment)
            oldFragment = fragment;
        fragmentTransaction.replace(replaceLayoutId, oldFragment, tag);
        fragmentTransaction.addToBackStack(oldFragment.getClass().toString());
        fragmentTransaction.commitAllowingStateLoss();
    }

    /**
     * 显示加载框
     * @param isCancleable
     */
    public void showLoadingDialog(boolean isCancleable) {
        if (progressDialog == null){

            progressDialog = ProgressDialogUtils.getProgressDialog(getContext(), isCancleable);
        }else{
            hiddenLoadingDialog();
        }

        disposable = Observable.timer(200, TimeUnit.MILLISECONDS)
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        if(progressDialog != null){
                            progressDialog.show();
                        }
                    }
                });
    }

    /**
     * 隐藏加载框
     */
    public void hiddenLoadingDialog() {
        if (disposable != null && !disposable.isDisposed()){
            disposable.dispose();
        }
        if (progressDialog != null ) {
            progressDialog.dismiss();
        }
    }

    /**
     * 获取请求的唯一标示
     * @return
     */
    public String getRequestUnicode(){
        return reQuestUnicode;

    }

    private void clearAllRequest(){
        RequestUtils.cancleRelativeRequest(getRequestUnicode());
    }

    /**
     * 开启页面统计 activity 中不包含fragment
     * @param pageId 页面编号
     */
    public void openUMAnlyse(String pageId) {
        isOpenUMAnalyse = true;
        this.pageId = pageId;
        this.isContainFragment = false;
    }
    /**
     * 检查网络  无网络则提示
     * @return
     */
    public  boolean checkNet(){
        if (NetUtil.checkNetWork(getContext())){
            return true;
        }else{
            ToastUtils.showLongToast(R.string.string_netconnect_nonet);
            return false;
        }
    }

    /**
     * 请求错误信息的处理
     */
    private  Handler responseStatusExceptionHandler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            loginOut();
        }
    };

    /**
     * 退出登录框
     */
    private void  loginOut(){
        SharedPreferencesUtils.clearSp(getContext());
        final CommonDialog commonDialog = new CommonDialog(getContext(), CommonDialog.CommonDialogViewType.ONE);
        commonDialog.setCanceledOnTouchOutside(false);
        commonDialog.setTittleAndContent("账号异常","您的账号出现异常，为了您的资金安全请重新登录");
        commonDialog.getCenterButton().setText("知道了");
        commonDialog.getCloseBtn().setVisibility(View.GONE);
        commonDialog.getCenterButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                commonDialog.dismiss();
                LoginParams params = new LoginParams(context, LoginEvent.LOGIN);
                params.goToMainPage = true;
                CenterEventBus.getInstance().postTask(params);
            }
        });
    }

    /**
     * 隐藏键盘
     */
    public void HiddenKeyBoard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView()
                    .getWindowToken(), 0);
        }
    }

    @Override
    public void onHttpRequestBegin(String url) {

    }

    @Override
    public void onHttpRequestSuccess(String url, HttpContext httpContext) {

    }

    @Override
    public void onHttpRequestFailed(String url, HttpContext httpContext, Throwable e) {

    }

    @Override
    public void onHttpRequestComplete(String url, HttpContext httpContext) {

    }

    @Override
    public void onHttpRequestCancel(String url, HttpContext httpContext) {

    }

    @Override
    public void onHttpResponseCodeException(String url, HttpContext httpContext ,int status) {
        if (801 == status){
            responseStatusExceptionHandler.sendEmptyMessage(0);
        }
    }

    /**
     * 检查 请求返回值 并给出提示
     * @param httpContext
     * @return
     */
    public boolean checkResponse(HttpContext httpContext) {

        BaseResponse response = httpContext.getResponseObject();
        if (response != null && response.status == 1) {

            return true;
        } else {
            if (response != null) {
                ToastUtils.showShortToast(response.getError());
            } else {
                // never  catch
            }
            return false;
        }
    }

    /**
     * 显示软键盘
     * @param v
     */
    public void showKeyBoard(final View v) {
        Observable.timer(300, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                v.setFocusable(true);
                v.setFocusableInTouchMode(true);
                v.requestFocus();
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.showSoftInput(v, InputMethodManager.SHOW_FORCED);
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (isOpenUMAnalyse) {
            UMAnalyticsUtils.onActivityPageResume(getContext(),isContainFragment,pageId);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (isOpenUMAnalyse) {
            UMAnalyticsUtils.onActivityPagePause(BaseActivity.this, isContainFragment, pageId);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        clearAllRequest();
//        hiddenLoadingDialog();
        gcAndFinalize();
    }

    void gcAndFinalize() {
        Runtime runtime = Runtime.getRuntime();
        System.gc();
        runtime.runFinalization();
        System.gc();
    }
}
