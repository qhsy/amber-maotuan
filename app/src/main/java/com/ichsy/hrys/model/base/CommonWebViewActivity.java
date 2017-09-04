package com.ichsy.hrys.model.base;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.gson.Gson;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.interfaces.JavaScriptinterface;
import com.ichsy.hrys.common.view.BaseWebView;
import com.ichsy.hrys.config.constants.IntConstant;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.webviewjs.Entity;

import butterknife.BindView;
import zz.mk.utilslibrary.LogUtil;


/**
 * 通用的基本WebView
 * 创建者:赵然
 */
public class CommonWebViewActivity extends BaseActivity {

    @BindView(R.id.bwv_commowebviewacvivity)
    BaseWebView mWebView;

    private String loadUrl;
    /**
     * 链接类型  0 常规 直接展示  1：提现界面  需展示头
     */
    private  int urlType;

    private boolean loadSuccess = true;

    private String TAG = "ihesen  ";

    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_common_web_view);
    }

    @Override
    public void logic() {
        if (getIntent() != null){
            loadUrl = getIntent().getStringExtra(StringConstant.PARAMS_URL);
            urlType = getIntent().getIntExtra(StringConstant.PARAMS_URLTYPE,0);
            initWebView();
        }
        if (IntConstant.WEBVIEWURL_TYPE_NORMAL == urlType){
            hiddenTitlebar();
        } else if(IntConstant.WEBVIEWURL_TYPE_SHOWTITLE == urlType ){
            String titleName = getIntent().getStringExtra(StringConstant.WEB_TITLE_NAME);
            if(!TextUtils.isEmpty(titleName)){
                showDefaultTittle(titleName);
            }
        }
    }

    @Override
    public void loadListener() {

    }

    @Override
    public void request() {
        if(checkNet()){
            if(TextUtils.isEmpty(mWebView.getOriginalUrl())){
                mWebView.loadUrl(loadUrl);
            }else{
                mWebView.reload();
            }
        }else{
            showExceptionLayout();
        }
    }

    private void showExceptionLayout(){
        showCommonNoNet(true);
        showDefaultTittle("");
        setLeftTittleText("返回");
        setLeftClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void hideExceptionLayout(){
        if(!(urlType == IntConstant.WEBVIEWURL_TYPE_SHOWTITLE)){
            hiddenTitlebar();
        }
        hiddenCommonException();
    }


    @Override
    public void onViewClick(int viewId) {

    }

    @Override
    public void onBackPressed() {
        if (mWebView.canGoBack()) {
            mWebView.goBack(); // 后退
            return; // 已处理
        } else {
            finish();
        }
    }
    /**
     * 初始化WebView
     */

    @SuppressLint("SetJavaScriptEnabled")
    private void initWebView() {
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setIsBackEnable(true);
        mWebView.setShowLoading(true);
        setH5LocalStorageEnable();
//        mWebView.loadUrl(loadUrl);

        mWebView.addJavascriptInterface(new JavaScriptinterface() {

            @Override
            @JavascriptInterface
            public void notifyOnAndroid(String str) {
                LogUtil.zLog().e("JS:"+str);
                Gson gson = new Gson();
                Entity entity = gson.fromJson(str, Entity.class);
                if (entity != null) {
                    String type = entity.getType();
                    if (StringConstant.JS_CLOSEWINDOW.equals(type)) {
                        finish();
                    }
                }
            }

            @Override
            @JavascriptInterface
            public String getSQNum() {
                return null;
            }

        }, StringConstant.WEBVIEW_JS_JSNAME);


        mWebView.setWebViewClient(new WebViewClient() {

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                LogUtil.zLog().e(TAG + "onPageStarted " + url);
                showLoadingDialog(true);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                LogUtil.zLog().e(TAG + "shouldOverrideUrlLoading " + url);
                mWebView.loadUrl(url);
                return false;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                LogUtil.zLog().e(TAG + "onReceivedError");
//                super.onReceivedError(view, errorCode, description, failingUrl);
                loadSuccess = false;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                LogUtil.zLog().e(TAG + "onPageFinished");
                if(loadSuccess){
                    hideExceptionLayout();
                }else{
                    showExceptionLayout();
                }
                hiddenLoadingDialog();
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                LogUtil.zLog().e(TAG + "onProgressChanged newProgress : " + newProgress);
                if(newProgress == 100){
                    loadSuccess = true;
                }
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
                if(IntConstant.WEBVIEWURL_TYPE_SHOWTITLE == urlType ){
                    showDefaultTittle(title);
                }
            }
        });
    }
    /**
     * 添加H5中使用本地缓存 的支持
     */
    private void setH5LocalStorageEnable(){
        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setAppCacheMaxSize(1024 * 1024 * 8);
        String appCachePath = getCacheDir().getAbsolutePath();
        mWebView.getSettings().setAppCachePath(appCachePath);
    }
}
