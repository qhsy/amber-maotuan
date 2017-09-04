package com.ichsy.hrys.common.utils.http.retrofit;


import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.http.RequestListener;
import com.ichsy.hrys.entity.response.BaseResponse;

import rx.Subscriber;
import zz.mk.utilslibrary.LogUtil;

/**
 * 功能：请求的回调  此处是为了记录请求过程的信息
 * ＊创建者：赵然 on 16/4/21 15:54
 * ＊
 */
public class RequestSubscriber extends Subscriber<Object> {
    private HttpContext httpContext = new HttpContext();
    private RequestListener callbackInterface;
    private String requestUrl;
    private boolean  isDealed = false;
    private boolean  isCancle = false;
    private String reuqestUnicode;

    public  RequestSubscriber(String reuqestUnicode, String requestUrl, RequestListener callbackInterface){
        this.requestUrl = requestUrl;
        this.callbackInterface = callbackInterface;
        this.reuqestUnicode = reuqestUnicode+requestUrl;

    }
    public  RequestSubscriber(String reuqestUnicode, String requestUrl, Object requestTag, RequestListener callbackInterface){
        this.requestUrl = requestUrl;
        httpContext.setRequestTag(requestTag);
        this.callbackInterface = callbackInterface;
        this.reuqestUnicode = reuqestUnicode+requestUrl;

    }
    @Override
    public void onStart() {
        super.onStart();

        if (httpContext == null) httpContext = new HttpContext();
        if (callbackInterface != null){
            callbackInterface.onHttpRequestBegin(requestUrl);
        }
    }

    @Override
    public void onCompleted() {
        RequestController.getInstance().removeRequest(reuqestUnicode);
        if (callbackInterface != null){
            callbackInterface.onHttpRequestComplete(requestUrl,httpContext);
        }
    }

    @Override
    public void onError(Throwable e) {
        LogUtil.zLog().e("onError ****************"+ e.getMessage());
        if (callbackInterface != null){
            callbackInterface.onHttpRequestFailed(requestUrl,httpContext,e);
            callbackInterface.onHttpRequestComplete(requestUrl,httpContext);
        }
    }

    @Override
    public void onNext(Object t) {
        httpContext.setResponseObject(t);
        LogUtil.zLog().e("onNext ********************************");
        if (callbackInterface != null){
            if (checkResutCode(t)){
                isDealed = false;
                callbackInterface.onHttpRequestSuccess(requestUrl,httpContext);
            }else{
                isDealed = true;
                callbackInterface.onHttpResponseCodeException(requestUrl,httpContext,(((BaseResponse)t).status));
            }
        }
    }

    /**
     * 需要统一处理的异常信息  后期考虑 非1 全部这里返回处理
     * @param responseEntityObj
     */
    private boolean checkResutCode(Object responseEntityObj){
        BaseResponse  responseEntity = (BaseResponse) responseEntityObj;
        return 801 != responseEntity.status;
    }

    /**
     * 取消请求
     */
    public void cancleRequest(){
        isCancle = true;
    }

}
