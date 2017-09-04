package com.ichsy.hrys.pay.factory;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.ichsy.hrys.pay.PayCallBackEvent;
import com.ichsy.hrys.pay.bean.PayParams;
import com.ichsy.hrys.pay.bean.WeChatpaymentResult;
import com.ichsy.hrys.wxapi.WXPayEntryActivity;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

/**
 * @author xingchun
 */
public class WeiChatImpl extends WXPayEntryActivity implements PayInterface {
    private static final String TAG = WeiChatImpl.class.getSimpleName();

    @Override
    public void pay(final PayParams parms, final Activity activity, final Handler handler) {
        Log.i(TAG, "parms=" + parms.toString());
        super.setOnCallBack(new CallBack() {
            @Override
            public void handlerReq(BaseResp resp) {
                Message message = new Message();
                int errCode = resp.errCode;
                Log.d(TAG, "errCode:" + errCode);
                switch (errCode) {
                    case -1:// 失败
                        message.what = PayCallBackEvent.WXIPAY_NO_STIR_UP;
                        break;
                    case -2:// 取消
                        message.what = PayCallBackEvent.PAY_CANCEL;
                        break;
                    case 0:// 成功
                        message.what = PayCallBackEvent.PAY_SUCCEED;
                        break;
                    default:
                        break;
                }
                Log.i(TAG, "message.what=" + message.what);
                handler.sendMessage(message);
            }
        });
        WeChatpaymentResult weChatpaymentResult = parms.weiChatPayReq;
        String appid = weChatpaymentResult.appId;
        Log.i(TAG, "appId=" + appid);
        IWXAPI msgApi = WXAPIFactory.createWXAPI(activity, appid);
        boolean isPaySupported = msgApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        if (isPaySupported) {
            msgApi.registerApp(appid);
        } else {
            handler.sendEmptyMessage(PayCallBackEvent.WEICAHT_NO_INSTALL);
        }
        PayReq req = new PayReq();
        req.appId = appid;
        req.partnerId = weChatpaymentResult.partnerId;
        req.prepayId = weChatpaymentResult.prepayId;
        req.packageValue = TextUtils.isEmpty(weChatpaymentResult.packageString)?"Sign=WXPay":weChatpaymentResult.packageString;
        req.nonceStr = weChatpaymentResult.nonceStr;
        req.timeStamp = weChatpaymentResult.timeStamp;
        req.extData = parms.orderCode;
        req.sign = weChatpaymentResult.sign;
        msgApi.sendReq(req);
    }
}
