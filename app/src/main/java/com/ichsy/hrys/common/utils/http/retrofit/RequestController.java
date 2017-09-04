package com.ichsy.hrys.common.utils.http.retrofit;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import zz.mk.utilslibrary.LogUtil;

/**
 * 功能：请求列表的管理器
 * TAG 为：界面所在内存地址+请求链接   所以同一个加界面的同一个请求此处只存一个 添加前会先取消
 * ＊创建者：赵然 on 16/6/16 17:41
 * ＊
 */
public class RequestController {
    private  RequestController(){}

    static class RequestControllerHolder{
          static final RequestController INSTANCE = new RequestController();
    }

    public static RequestController getInstance(){
        return RequestControllerHolder.INSTANCE;
    }
    /**
     * 存储当前进行的所有请求
     * key -----  界面所在内存地址+请求链接
     */
    private Map<String, RequestSubscriber> requestSubscriberMap = new HashMap<>();

    /**
     * 添加请求
     * @param tag
     * @param subscriber
     */
    public void  addRequest(@NonNull String tag, @NonNull RequestSubscriber subscriber){

        //判断是否存在 存在则 取消
        if (requestSubscriberMap.size()>0 && requestSubscriberMap.containsKey(tag)){
            unRegistRequest(tag);
            requestSubscriberMap.remove(tag);
        }

        requestSubscriberMap.put(tag,subscriber);
    }
    /**
     * 移除请求
     * @param tag  单个请求的唯一标示：界面所在内存地址 / 界面所在内存地址+请求链接
     */
    public void removeRequest(@NonNull String tag){
        if (requestSubscriberMap.containsKey(tag)){
            unRegistRequest(tag);
            requestSubscriberMap.remove(tag);
        }

    }
    /**
     * 移除界面相关的所有请求
     * @param tag 界面内存的地址
     */
    public void removeAllRelativeRequest(@NonNull String tag){
        Iterator<Map.Entry<String, RequestSubscriber>> it = requestSubscriberMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String, RequestSubscriber> entry=it.next();
            String requestTag=entry.getKey();
            if (requestTag.contains(tag)){

                unRegistRequest(requestTag);
                it.remove();
            }
        }

        LogUtil.zLog().e("requestMap size:"+requestSubscriberMap.size());
    }

    private void unRegistRequest(String requestTag){
        RequestSubscriber requestSubscriber = requestSubscriberMap.get(requestTag);
        if (requestSubscriber != null && !requestSubscriber.isUnsubscribed())
        {
            requestSubscriber.unsubscribe();
        }
    }


}
