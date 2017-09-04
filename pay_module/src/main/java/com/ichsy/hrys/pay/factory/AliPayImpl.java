package com.ichsy.hrys.pay.factory;

import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.ichsy.hrys.pay.AliPayResult;
import com.ichsy.hrys.pay.PayCallBackEvent;
import com.ichsy.hrys.pay.bean.PayParams;

import com.alipay.sdk.app.PayTask;

public class AliPayImpl implements PayInterface {

    private final int POLL_NUM = 5;

    @Override
    public void pay(final PayParams params, final Activity activity, final Handler handler) {
        new Thread() {
            private String result;

            public void run() {
                PayTask alipay = new PayTask(activity);
                try {
                    for (int i = 0; i < POLL_NUM; i++) {
                        String alipayStr = alipay.pay(params.alipayUrl, true);
                        result = new AliPayResult(alipayStr).getResultStatus();
                        if (result != null) break;
                    }
                    Message message = new Message();
                    if (TextUtils.isEmpty(result)) {
                        //支付调起失败
                        message.what = PayCallBackEvent.ALIPAY_NO_STIR_UP;
                    } else {
                        switch (Integer.parseInt(result)) {
                            case 9000:// 支付成功
                                message.what = PayCallBackEvent.PAY_SUCCEED;
                                break;
                            case 4000://支付失败
                                message.what = PayCallBackEvent.PAY_FAIL;
                                break;
                            case 6001://支付取消
                                message.what = PayCallBackEvent.PAY_CANCEL;
                                break;
                            case 6002://网络错误
                                message.what = PayCallBackEvent.PAY_NET_ERROR;
                                break;
                            default:
                                break;
                        }
                    }
                    handler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
