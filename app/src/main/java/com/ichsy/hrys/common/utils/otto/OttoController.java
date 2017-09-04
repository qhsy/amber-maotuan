package com.ichsy.hrys.common.utils.otto;

import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.squareup.otto.Bus;

/**
 * otto 工具类
 */
public class OttoController {

    /**
     * 示例对象
     */
    private  static Bus bus;
    private static Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            getInstance().post(msg.obj);
        }
    };

    private  static  Bus getInstance(){
        if (bus == null) bus = new Bus();
        return bus;
    }

    /**
     * 注册
     * @param obj 一般是上下文
     *            注意:同一个对象中 只能注册一次 否则报错
     */
    public static  void register(Object obj){
        getInstance().register(obj);
    }

    /**
     * 反注册
     * @param obj  一般是上下文
     */
    public static void unregister(Object obj){
        getInstance().unregister(obj);
    }
    /**
     * 发送 数据
     * @param data 数据
     * @param type  类型
     * @param tag  标记  需唯一 相同则只发送最近一个 为空则用当前时间戳为标记
     */
    public static  void post(Object data, OttoEventType type, String tag){
        OttoEventEntity entity = new OttoEventEntity();
        //防止同一个回调
        if (entity.getTag() == null || entity.getTag().equals(tag))
            handler.removeMessages(0);
        entity.setDatas(data);
        entity.setType(type);
        if (TextUtils.isEmpty(tag)){
            tag = System.currentTimeMillis()+"";
        }
        Log.i("TAG", "type:"+ type.toString()+"  tag:" + tag);
        entity.setTag(tag);
        //发送前不能移除
        Message msg = Message.obtain();
        msg.obj = entity;
        handler.sendMessage(msg);

    }
}

