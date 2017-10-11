package com.ichsy.hrys.model.base;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ichsy.hrys.R;
import com.ichsy.hrys.common.interfaces.ActivityInterface;
import com.ichsy.hrys.common.utils.ProgressDialogUtils;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.UMAnalyticsUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.http.RequestListener;
import com.ichsy.hrys.common.view.CommonExceptionView;
import com.ichsy.hrys.common.view.CustomTittleView;
import com.ichsy.hrys.entity.response.BaseResponse;

import java.util.concurrent.TimeUnit;

import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import zz.mk.utilslibrary.LogUtil;
import zz.mk.utilslibrary.ToastUtils;
import zz.mk.utilslibrary.net.NetUtil;


/**
 * base fragment
 *
 * @author 赵然
 *
 */
public abstract class BaseFragment extends Fragment implements ActivityInterface,RequestListener {

    public static final int RESULT_OK = Activity.RESULT_OK;

    private CustomTittleView customTittleView;
    /**
     * 异常view
     */
    private CommonExceptionView exceptionView;

    private View mContentView;

    private ViewGroup mGroupContent = null;

    private Intent mIntent = new Intent();

    // UM统计需要的页面ID
    private String pageId;

    // 当前页面是否打开UM统计
    private boolean isOpenUMAnalyse = false;
    /**
     * 是否缓存页面
     */
    private boolean isCache = true ;
    protected boolean isFirstLoadFinished = false;
    /**
     * 加载框
     */
    ProgressDialog progressDialog;

    private Disposable disposable;

    /**
     * 异常view
     */
//    private CommonExceptionView exceptionView;
    private View contentView;

    private boolean isViaOnCreate;
    protected Activity context;

    private String uiMemoryAdress = this.toString();
    /**
     * 是否在界面销毁时 清空所有的请求
     */
    private boolean isCancleAllRequest = true;

    /**
     * 请求错误信息的处理
     */
//    private  Handler responseStatusExceptionHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            loginOut();
//        }
//    };
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContentView = inflateLayout(R.layout.activity_base, null, false);
        hiddenTitlebar();
        context = getActivity();
        isViaOnCreate = true;
        initTitleBarView();
        initView();
        LogUtil.zLog().e("TAG: "+uiMemoryAdress);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//		getActivity().setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // 设置禁止横屏
        if (isCache) {
            isViaOnCreate = false;
            getActivity().getSupportFragmentManager().saveFragmentInstanceState(
                    this);
            ViewGroup p = (ViewGroup) mContentView.getParent();
            if (p != null)
                p.removeAllViewsInLayout();
        }else{
            if (isViaOnCreate)
            {
                isViaOnCreate = false;
            }else {
                mContentView = inflateLayout(R.layout.activity_base, null, false);

                initTitleBarView();
                initView();
            }
        }
        return mContentView;

    }
    /**
     * 装载一个布局文件，获得其视图
     *
     * @param layoutId
     *            布局文件
     * @param root
     * @param attachToRoot
     * @return
     */
    protected View inflateLayout(int layoutId, ViewGroup root,
                                 boolean attachToRoot) {
        View view = LayoutInflater.from(getActivity()).inflate(layoutId, root,
                attachToRoot);
        return view;
    }

    protected void setContentView(int resid) {
        contentView = View.inflate(mContentView.getContext(), resid, mGroupContent);
        contentView.setClickable(true);
        ButterKnife.bind(this, contentView);
    }

    private void initView() {
        loadLayout();
        logic();
        loadListener();
        request();
    }
    /**
     * 在这里实现Fragment数据的缓加载.
     * @param isVisibleToUser
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(!isFirstLoadFinished && getUserVisibleHint()) {
            isFirstLoadFinished = true;
            onVisible();
        }
    }

    protected void onVisible(){
        lazyLoad();
    }

    protected abstract void lazyLoad();

    protected void initTitleBarView() {
        mGroupContent = (ViewGroup) mContentView.findViewById(R.id.activity_content);
        customTittleView = (CustomTittleView) findViewById(R.id.title_bar);
        exceptionView = (CommonExceptionView) findViewById(R.id.cev_base_exception);
    }
    public View getmContentView(){
        return contentView;
    }

    /**
     * 获取异常view
     * @return
     */
    public CommonExceptionView getExceptionView(){
        return exceptionView;
    }

    protected View findViewById(int id) {
        return mContentView.findViewById(id);
    }

    @Override
    public void setArguments(Bundle args) {
        super.setArguments(args);
        mIntent.replaceExtras(args);
    }


    protected void runOnUiThread(Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }

    protected Intent getIntent() {
        return mIntent;
    }

    public void repalceFramgnet(int id, Fragment fragment) {
        FragmentTransaction mTransaction = getFragmentManager().beginTransaction();

        mTransaction.replace(id, fragment);
        mTransaction.commit();
    }

    protected void replaceFragment(Fragment fragment, String tag, int id) {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment oldFragment = fragmentManager.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (oldFragment == null)
            oldFragment = fragment;
        fragmentTransaction.replace(id, oldFragment, tag);
        fragmentTransaction.commit();
    }

    /**
     * Fragment 中嵌套fragment时    父fragment中替换view使用这个
     * @param fragment  innerFragment
     * @param tag     标记
     * @param id   替换的布局
     */
    protected void replaceInnerFragment(Fragment fragment, String tag, int id) {
        FragmentManager fragmentManager = getChildFragmentManager();
        Fragment oldFragment = fragmentManager.findFragmentByTag(tag);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        // fragmentTransaction.setCustomAnimations(R.anim.anim_enter,R.anim.anim_exit);
        if (oldFragment == null)
            oldFragment = fragment;
        fragmentTransaction.replace(id, oldFragment, tag);
        // fragmentTransaction.addToBackStack(oldFragment.getClass().toString());
        fragmentTransaction.commit();
    }


    // 数据请求的

    /**
     * 开启页面统计
     */
    public void openUMAnlyse(String pageId) {
        this.pageId = pageId;
        isOpenUMAnalyse = true;
    }


    @Override
    public void onPause() {
        super.onPause();
        if (isOpenUMAnalyse) {
            UMAnalyticsUtils.onFragmentPagePause(pageId);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isOpenUMAnalyse) {
            UMAnalyticsUtils.onFragmentPageResume(pageId);
        }
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
     *
     * @param drawableID
     *            图片对应的ID
     */
    public void setLeftDrawable(int drawableID) {
        customTittleView.setVisibility(View.VISIBLE);
        customTittleView.setTextViewDrawableResource(0,drawableID,0); // 设置左图标

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
     * 自定义的 异常view
     * @param view
     */
//    public void showCustomExceptionView(View view){
//        exceptionView.setVisibility(View.VISIBLE);
//        exceptionView.setCustomException(view);
//    }
    /**
     * 异常布局中得无网界面 一样故 统一封装  点击直接调用request方法
     * @param isClickWithRequestMethed  点击无网布局是否调用 request防范  true: 调用  false 自己继续写点击事件
     */
    public CommonExceptionView showCommonNoNet(boolean isClickWithRequestMethed){
        exceptionView.setVisibility(View.VISIBLE);
        exceptionView.getExceptionIcon().setBackgroundResource(R.drawable.icon_nonet);
        if (isAdded() && getActivity() != null)
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
    public CommonExceptionView showCommonNoData (String tips){
        exceptionView.setVisibility(View.VISIBLE);
        exceptionView.getExceptionIcon().setVisibility(View.GONE);
        exceptionView.getExctptionButton().setVisibility(View.GONE);
        exceptionView.getExceptionTextView().setText(tips);
        return exceptionView;
    }
    /**
     * 无数据布局
     */
//    public CommonExceptionView showCommonNoData (String tips, int drawableRes){
//        exceptionView.setVisibility(View.VISIBLE);
//        exceptionView.getExceptionIcon().setBackgroundResource(drawableRes);
//        exceptionView.getExctptionButton().setVisibility(View.GONE);
//        exceptionView.getExceptionTextView().setText(tips);
//        return exceptionView;
//    }
    /**
     * 显示加载框
     * @param isCancleable
     */
    public void showLoadingDialog(boolean isCancleable) {
        if (isAdded()) {
        if (progressDialog == null){

            progressDialog = ProgressDialogUtils.getProgressDialog(getContext(), isCancleable);
        }else{
            hiddenLoadingDialog();
        }

            disposable = Observable.timer(1000, TimeUnit.MILLISECONDS)
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
    public void onHttpResponseCodeException(String url, HttpContext httpContext,int status) {
        if (801 == status){
//            responseStatusExceptionHandler.sendEmptyMessage(0);
        }
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
     * 设置fragment 是否缓存页面
     * @param isCache  默认true
     */
    public void setIsCache(boolean isCache){
        this.isCache = isCache;
    }
    public boolean getisCache(){
        return isCache;
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
     * 显示默认的普通tittle   左侧返回按钮 点击返回  中间展示文字
     * @param centreTittleText
     */
    protected  void showDefaultTittle(String centreTittleText){
        setCenterTitleText(centreTittleText);
        setLeftDrawable(R.mipmap.icon_back_black);

        setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isCancleAllRequest){
            clearAllRequest();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    /**
     * 检查网络  无网络则提示
     * @return
     */
    public  boolean checkNet(){
        if (NetUtil.checkNetWork(getContext())){
            return true;
        }else{
            ToastUtils.showShortToast(R.string.string_netconnect_nonet);
            return false;
        }
    }

    /**
     * 退出登录框
     */
//    private void  loginOut(){
//        SharedPreferencesUtils.clearSp(getContext());
//        final CommonDialog commonDialog = new CommonDialog(getContext(), CommonDialog.CommonDialogViewType.ONE);
//        commonDialog.setCanceledOnTouchOutside(false);
//        commonDialog.setTittleAndContent("账号异常","您的账号出现异常，为了您的资金安全请重新登录");
//        commonDialog.getCenterButton().setText("知道了");
//        commonDialog.getCloseBtn().setVisibility(View.GONE);
//        commonDialog.getCenterButton().setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                commonDialog.dismiss();
//                LoginParams params = new LoginParams(context, LoginEvent.LOGIN);
//                params.goToMainPage = true;
//                CenterEventBus.getInstance().postTask(params);
//            }
//        });
//    }

    /**
     * 获取请求的唯一标示
     * @return
     */
    public String getRequestUnicode(){
        return uiMemoryAdress;

    }
    private void clearAllRequest(){
        RequestUtils.cancleRelativeRequest(getRequestUnicode());
    }

    /**
     * 设置是否界面销毁时取消全部请求
     * @param isCancleAllRequest
     */
    public  void  setCancleAllRequest(boolean isCancleAllRequest){

        this.isCancleAllRequest = isCancleAllRequest;
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
}
