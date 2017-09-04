package com.ichsy.hrys.model.user;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.RequestUtils;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.common.utils.http.HttpContext;
import com.ichsy.hrys.common.utils.http.retrofit.updownload.UpDownloadUtils;
import com.ichsy.hrys.common.utils.http.retrofit.updownload.UploadListener;
import com.ichsy.hrys.common.utils.otto.OttoController;
import com.ichsy.hrys.common.utils.otto.OttoEventType;
import com.ichsy.hrys.common.view.clip.ClipSquareImageView;
import com.ichsy.hrys.config.config.ServiceConfig;
import com.ichsy.hrys.config.constants.IntConstant;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.ArtUserInfo;
import com.ichsy.hrys.entity.request.ModifyUserInfoRequestEntity;
import com.ichsy.hrys.entity.response.BaseResponse;
import com.ichsy.hrys.model.base.BaseActivity;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;
import zz.mk.utilslibrary.FileUtil;
import zz.mk.utilslibrary.ToastUtils;
import zz.mk.utilslibrary.bitmap.BitmapUtil;


public class ClipActivity extends BaseActivity {
    @BindView(R.id.clipSquareIV)
    ClipSquareImageView imageView;
    private ArtUserInfo mUserInfo;
    /**
     *  裁剪类型
     */
    private int type;

    private Bitmap bmp;
    private String imagPath;

    @Override
    public void loadLayout() {
        setContentView(R.layout.activity_clip);
    }

    @Override
    public void logic() {

        setRightTitleText("保存");

        getRightTittleView().setTextColor(getResources().getColor(R.color.color_text_gold));
        if (getIntent() != null) {
            mUserInfo = (ArtUserInfo) getIntent().getSerializableExtra(StringConstant.USERINFO);
            imagPath = getIntent().getStringExtra(StringConstant.IMAGE_PATH);
            type = getIntent().getIntExtra(StringConstant.IMAGE_CLIPTYPE, IntConstant.CLIPIMAGETYPE_ICON);
        }
        switch (type){
            case IntConstant.CLIPIMAGETYPE_ICON:
                showDefaultTittle("个人头像");
                break;
            case IntConstant.CLIPIMAGETYPE_USERMSGBG:
                showDefaultTittle("个人背景");
                break;
        }
        if (!TextUtils.isEmpty(imagPath)) {
            showLoadingDialog(true);
            dealImage();
        }

    }


    @Override
    public void loadListener() {
        setRightClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                showLoadingDialog(false);
                // 此处获取剪裁后的bitmap
                dealClipImag();

            }
        });
    }


    @Override
    public void request() {

    }

    @Override
    public void onViewClick(int viewId) {

    }

    private Bitmap getBitmap(String pathString) {
        try{
            BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File(pathString)));
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeStream(in, null, options);
            in.close();
            int i = 0;
            Bitmap bitmap = null;
            while (true) {
                if ((options.outWidth >> i <= 1000) && (options.outHeight >> i <= 1000)) {
                    in = new BufferedInputStream(new FileInputStream(new File(pathString)));
                    options.inSampleSize = (int) Math.pow(2.0D, i);
                    options.inJustDecodeBounds = false;
                    bitmap = BitmapUtil.rotate(BitmapFactory.decodeStream(in, null, options), BitmapUtil.getImageDegree(pathString));
                    break;
                }
                i += 1;
            }
            return bitmap;
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * 大小处理
     *
     */
    private void dealImage() {

        Subscriber<Bitmap> subscriber = new Subscriber<Bitmap>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(final Bitmap bmp) {
                imageView.setImageBitmap(bmp);
                imageView.setBorderWeight(1, 1);
                hiddenLoadingDialog();
            }
        };
        Observable<Bitmap> observable = Observable.create(new Observable.OnSubscribe<Bitmap>() {
            @Override
            public void call(Subscriber<? super Bitmap> subscriber) {
                bmp = getBitmap(imagPath);
                subscriber.onNext(bmp);
                subscriber.onCompleted();
            }
        });



        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    /**
     * 上传保存图像
     */
    private void dealClipImag() {

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(final String s) {
                UpDownloadUtils.upload(getContext(), s, new UploadListener() {

                    @Override
                    public void onStart() {

                    }

                    @Override
                    public void onProgress(long currentSize, long totalSize, String speed) {

                    }

                    @Override
                    public void onSuccess(String imgUrl) {
                        Log.i("clipActivity", "onSuccess: " + imgUrl);
                        if (TextUtils.isEmpty(imgUrl)) {
                            ToastUtils.showShortToastSafe("图片上传失败");
                            hiddenLoadingDialog();
                        }else{
                            switch (type){
                                case IntConstant.CLIPIMAGETYPE_ICON:
                                    dealWithIcon(imgUrl);

                                    break;
                                case IntConstant.CLIPIMAGETYPE_USERMSGBG:
                                    dealWithUserBg(imgUrl);

                                    break;
                            }

                        }

                        FileUtil.deleteDirectory(s);

                    }

                    @Override
                    public void onFailure(Exception e) {

                    }
                });
            }
        };
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(final Subscriber<? super String> subscriber) {
                Bitmap bitmap = imageView.clip();
                if (bitmap != null) {
                    //上传头像
                    String fileTempName = "headericon.jpg";
                    final String fileName = System.currentTimeMillis() + fileTempName;

                    /** 首先默认个文件保存路径 */
                    final String filePath = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator : "/mnt/sdcard/";//保存到SD卡
                    Log.i("clipActivity", "call: " + filePath);
                    BitmapUtil.saveBitmapToSD(filePath, fileTempName, bitmap);

                    Luban.with(getContext())
                            .load(new File(filePath+fileTempName))           // 传人要压缩的图片列表
                            .ignoreBy(100)                                  // 忽略不压缩图片的大小
                            .setTargetDir(filePath)                        // 设置压缩后文件存储位置
                            .setCompressListener(new OnCompressListener() { //设置回调
                                @Override
                                public void onStart() {
                                    // TODO 压缩开始前调用，可以在方法内启动 loading UI
                                }

                                @Override
                                public void onSuccess(File file) {
                                    // TODO 压缩成功后调用，返回压缩后的图片文件
                                    subscriber.onStart();
                                    subscriber.onNext(file.getPath());
                                    subscriber.onCompleted();
                                }

                                @Override
                                public void onError(Throwable e) {
                                    // TODO 当压缩过程出现问题时调用
                                    Log.i("clipActivity", "onError: " + e.getMessage());
                                }
                            }).launch();    //启动压缩
                }
            }
        });

        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    private void  dealWithIcon(String imgUrl){
        mUserInfo.setUserIconurl(imgUrl);
        mUserInfo.setUserIconThumburl(imgUrl);
        OttoController.post(mUserInfo, OttoEventType.OTTO_MODIFY_USERINFO,"");
        hiddenLoadingDialog();
        finish();

    }
    private void dealWithUserBg(String imgUrl){

        mUserInfo.setBjIconurl(imgUrl);
        ModifyUserInfoRequestEntity requestEntity = new ModifyUserInfoRequestEntity();
        requestEntity.userInfo = mUserInfo;
        RequestUtils.modifyUserInfo(getRequestUnicode(),requestEntity,this);
    }

    @Override
    public void onHttpRequestSuccess(String url, HttpContext httpContext) {
        super.onHttpRequestSuccess(url, httpContext);
        if (ServiceConfig.MODIFYUSERINFO.equals(url)){
            BaseResponse responseObject = httpContext.getResponseObject();
            if (responseObject != null && responseObject.status == 1){
                //TODO no affect
                mUserInfo.setUserIconThumburl("");
                SharedPreferencesUtils.saveUserMsg(getContext(),mUserInfo);
                finish();
            }else if(responseObject != null){
                ToastUtils.showShortToast(responseObject.getError());

            }
        }
    }

    @Override
    public void onHttpRequestFailed(String url, HttpContext httpContext, Throwable e) {
        super.onHttpRequestFailed(url, httpContext, e);
        if (ServiceConfig.MODIFYUSERINFO.equals(url)){

            ToastUtils.showShortToast(getString(R.string.string_netconnect_timeout));
        }
    }

    @Override
    public void onHttpRequestComplete(String url, HttpContext httpContext) {
        super.onHttpRequestComplete(url, httpContext);
        hiddenLoadingDialog();
    }
}
