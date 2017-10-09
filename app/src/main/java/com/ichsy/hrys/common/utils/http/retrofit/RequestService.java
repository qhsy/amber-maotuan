package com.ichsy.hrys.common.utils.http.retrofit;


import android.text.TextUtils;

import com.ichsy.hrys.AppApplication;
import com.ichsy.hrys.ApplicationSettingController;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.config.config.ClentConfig;
import com.ichsy.hrys.entity.ArtUserInfo;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.Buffer;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import zz.mk.utilslibrary.LogUtil;


/**
 * 请求helper
 */
public class RequestService {
    RequestInterface requestInterface;
    private static int TIMEOUT = 10000;
    String requestTag = "httpRequest";

    private RequestService(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                LogUtil.zLog().e(requestTag+":"+message);
            }
        });
        logging.setLevel(ApplicationSettingController.HTTPLOGLEVEL);


        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS);
        httpClientBuilder.addInterceptor(logging);

        httpClientBuilder.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder requestBuilder = request.newBuilder();
                String postBodyString =resetRequestToken(bodyToString(request.body()));
                request = requestBuilder
                        .post(RequestBody.create(MediaType.parse("application/json; charset=UTF-8"), postBodyString))
                        .build();

                return chain.proceed(request);
            }
        });

        OkHttpClient client = httpClientBuilder.build();
        Retrofit adapter = new Retrofit.Builder()
                .baseUrl(ClentConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // 添加Rx适配器
                .client(client)
                .build();
        requestInterface = adapter.create(RequestInterface.class);
    }


    static class RequestServiceHoler{
        private static RequestService RequestService = new RequestService();
    }

   public static RequestInterface getInstance(){
        return RequestServiceHoler.RequestService.requestInterface;
    }
    public static String bodyToString(final RequestBody request){
        try {
             RequestBody copy = request;
             Buffer buffer = new Buffer();
            if(copy != null)
                copy.writeTo(buffer);
            else
                return "";
            return buffer.readUtf8();
        }
        catch (final IOException e) {
            return "did not work";
        }
    }

    private static String resetRequestToken(String requestStr){

        Pattern pat = Pattern.compile("\"token\":\".*\"");
        Matcher mat = pat.matcher(requestStr);
        ArtUserInfo userInfo = SharedPreferencesUtils.getUserInfo(AppApplication.mContext);
        String newToken = userInfo.getUserToken();
        if (!userInfo.isLogin && !TextUtils.isEmpty(newToken)){
            LogUtil.zLog().e("login:requestService");
            userInfo.setUserToken("");
            SharedPreferencesUtils.saveUserMsg(AppApplication.mContext,userInfo);
        }
        return mat.find() ? requestStr.replace(mat.group(), "\"token\":\"" + newToken + "\"") : requestStr;

    }
}
