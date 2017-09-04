package com.ichsy.hrys.pay.factory;

import android.app.Activity;
import android.os.Handler;

import com.ichsy.hrys.pay.bean.PayParams;


/**
 * @author ihesen
 *         支付方式工厂
 */
public abstract class PayFactory {

    public void pay(PayParams params, Activity activity, Handler handler) {
        PayInterface payInterface = creatPayWay();
        payInterface.pay(params, activity, handler);
    }

    protected abstract PayInterface creatPayWay();
}
