package com.ichsy.hrys.common.utils.http.retrofit.updownload;

import android.content.Context;

import com.google.gson.Gson;
import com.ichsy.hrys.common.utils.http.retrofit.updownload.entity.ImageResponseEntiy;
import com.ichsy.hrys.config.config.ClentConfig;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import zz.mk.utilslibrary.LogUtil;

/**
 *上传图片的工具类
 */
public class UpDownloadUtils {

    public static  void upload(final Context context, String filePath, final UploadListener uploadListener) {
        File file = new File(filePath);
        OkHttpClient client = new OkHttpClient.Builder()
                //设置超时，不设置可能会报异常
                .connectTimeout(1000, TimeUnit.MINUTES)
                .readTimeout(1000, TimeUnit.MINUTES)
                .writeTimeout(1000, TimeUnit.MINUTES)
                .build();

        //这个是ui线程回调，可直接操作UI
        final UIProgressListener uiProgressRequestListener = new UIProgressListener() {
            @Override
            public void onUIProgress(long bytesWrite, long contentLength, boolean done) {
                if (uploadListener != null){
                    uploadListener.onProgress(bytesWrite,contentLength,"");
                }
            }

            @Override
            public void onUIStart(long bytesWrite, long contentLength, boolean done) {
                super.onUIStart(bytesWrite, contentLength, done);
                if (uploadListener != null){
                    uploadListener.onStart();
                }
            }

            @Override
            public void onUIFinish(long bytesWrite, long contentLength, boolean done) {
                super.onUIFinish(bytesWrite, contentLength, done);
            }
        };

        //构造上传请求，类似web表单
        RequestBody requestBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"filename\""),
                        RequestBody.create(null, file.getName()))
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"file\"; filename=\"a.jpg\""),
                        RequestBody.create(null,file))
                .build();

        //进行包装，使其支持进度回调
        final Request request = new Request.Builder().url(ClentConfig.BASE_URL+"/webupload/zooupload").post(ProgressHelper.addProgressRequestListener(requestBody, uiProgressRequestListener)).build();
        //开始请求
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                LogUtil.zLog().e("failure");
                if (uploadListener != null){
                    uploadListener.onFailure(e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (uploadListener != null){

                    String json = response.body().string().toString();
//                LogUtil.zLog().e("onResponse" + response.body().string().toString());
                    Gson gson = new Gson();
                    ImageResponseEntiy entity = gson.fromJson(json,ImageResponseEntiy.class);
                    if (entity != null){

                        uploadListener.onSuccess(entity.getFileUrl());
                    }else{
                        uploadListener.onSuccess("");
                    }
                }


            }

        });

    }


}
