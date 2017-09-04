package com.ichsy.hrys.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author xingchun
 */
public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {
    private static final String TAG = WXPayEntryActivity.class.getSimpleName();
    private IWXAPI api;
    private static CallBack mCallBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate");
        api = WXAPIFactory.createWXAPI(this, "wxb3a89ef9837e9dad");
        api.handleIntent(getIntent(), this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.i(TAG, "onNewIntent");
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq arg0) {
        Log.i(TAG, "onReq");
    }

    @Override
    public void onResp(BaseResp resp) {
        Log.i(TAG, "onResp arg0=" + resp);
        Log.i(TAG, "onPayFinish, errCode = " + resp.errCode);
        Log.i(TAG, "onPayFinish, errStr = " + resp.errStr);
        Log.i(TAG, "mCallBack=" + (mCallBack == null));
        if (mCallBack != null) {
            mCallBack.handlerReq(resp);
            mCallBack = null;
        }
        finish();
    }

    public interface CallBack {
        void handlerReq(BaseResp resp);
    }

    public void setOnCallBack(CallBack callBack) {
        WXPayEntryActivity.mCallBack = callBack;
    }
}
