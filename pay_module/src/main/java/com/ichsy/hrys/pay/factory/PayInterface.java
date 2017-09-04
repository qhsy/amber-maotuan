package com.ichsy.hrys.pay.factory;


import android.app.Activity;
import android.os.Handler;

import com.ichsy.hrys.pay.bean.PayParams;

/**
 * 统一支付接口
 *
 * @author xingchun
 */
public interface PayInterface {
    void pay(PayParams parms, Activity activity, Handler handler);
}
