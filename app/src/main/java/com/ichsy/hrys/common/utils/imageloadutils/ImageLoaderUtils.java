package com.ichsy.hrys.common.utils.imageloadutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.ichsy.hrys.R;
import com.ichsy.hrys.common.utils.SharedPreferencesUtils;
import com.ichsy.hrys.entity.AppConfigEntity;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.ColorFilterTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropSquareTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.BrightnessFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ContrastFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.InvertFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.KuwaharaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.PixelationFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SketchFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.SwirlFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.VignetteFilterTransformation;
import zz.mk.utilslibrary.LogUtil;
import zz.mk.utilslibrary.bitmap.BitmapUtil;

import static com.ichsy.hrys.AppApplication.mContext;
import static com.ichsy.hrys.R.drawable.icon_wode;


/**
 * 功能:图片加载的工具类  图片展示切换动画为渐变
 * ＊创建者：Bone on 16/4/25 14:18
 * ＊
 */
public class ImageLoaderUtils {
    //当加载源为空时 加载失败时 默认图 的ID
    private static int defaultDrawableID = R.drawable.head_placeholder;
    private static int animationTime = 400;

    //缓存策略--原图   不设置则glide默认为当前加载的控件的宽高 不一样宽高的图会再次缓存
    private static DiskCacheStrategy cacheStrategy = DiskCacheStrategy.RESULT;

    /**
     * 图片下载监听
     */
    private static ImageLoadListener loadListener;
    /**
     * Gif图的下载监听
     */
    private static  RequestListener<Object,GifDrawable> gitRequestListener = new RequestListener<Object, GifDrawable>() {
        @Override
        public boolean onException(Exception e, Object model, Target<GifDrawable> target, boolean isFirstResource) {
            if (loadListener != null){
                loadListener.onException(e);
            }
            return false;
        }

        @Override
        public boolean onResourceReady(GifDrawable resource, Object model, Target<GifDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            if (loadListener != null){
                loadListener.onSuccess(model,resource.getCurrent());
            }
            return false;
        }
    };
    /**
     * 普通图片加载的监听
     * @return
     */
    private static RequestListener<Object, GlideDrawable> requestListener = new RequestListener<Object, GlideDrawable>() {
        @Override
        public boolean onException(Exception e, Object model, Target<GlideDrawable> target, boolean isFirstResource) {
            if (loadListener != null){
                loadListener.onException(e);
            }
            return false;
        }

        @Override
        public boolean onResourceReady(GlideDrawable resource, Object model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
            if (loadListener != null){
                loadListener.onSuccess(model,resource.getCurrent());
            }
            return false;
        }
    };

    /**************** 一般的图片加载  ******************/
    /**
     * 简单的图片加载
     *
     * @param img
     * @param sourceId
     */
    public static void loadViewImage(@NonNull Context context, @NonNull ImageView img, @NonNull Object sourceId) {
        loadViewImage(context,img,sourceId,defaultDrawableID,defaultDrawableID,defaultDrawableID,null,ImageStyleType.NULL);
    }/**************** 一般的图片加载  ******************/
    /**
     * 简单的图片加载
     *
     * @param img
     * @param sourceId
     */
    public static void loadViewImageWithWH(@NonNull Context context, @NonNull ImageView img, @NonNull Object sourceId, int width, int height) {
        loadViewImageWithWH(context,img,sourceId,defaultDrawableID,defaultDrawableID,defaultDrawableID,null,ImageStyleType.NULL,width,height);
    }

    public static void loadViewImageWithWH(@NonNull Context context, @NonNull ImageView img, @NonNull Object sourceId,int defaultDrawableID, int width, int height, @NonNull ImageStyleType type) {
        loadViewImageWithWH(context,img,sourceId,defaultDrawableID,defaultDrawableID,defaultDrawableID,null,type,width,height);
    }

    /**
     * 简单的图片加载
     *
     * @param img
     * @param sourceId
     */
    public static void loadViewImage(@NonNull Context context, @NonNull ImageView img, @NonNull Object sourceId, ImageLoadListener listener) {
        loadViewImage(context,img,sourceId,defaultDrawableID,defaultDrawableID,defaultDrawableID,listener,ImageStyleType.NULL);
    }
    /**
     * 简单的图片加载-- 图片有特殊形状
     *
     * @param img
     * @param sourceId
     * @param type
     */
    public static void loadViewImage(@NonNull Context context, @NonNull ImageView img, @NonNull Object sourceId, @NonNull ImageStyleType type) {
        loadViewImage(context,img,sourceId,defaultDrawableID,defaultDrawableID,defaultDrawableID,null,type);
    }
    /**
     *
     * @param img
     * @param sourceId
     * @param defaultDrawableID 默认图ID
     */
    public static void loadViewImage(@NonNull Context context, @NonNull ImageView img, @NonNull Object sourceId, int defaultDrawableID) {
        loadViewImage(context,img,sourceId,defaultDrawableID,defaultDrawableID,defaultDrawableID,null,ImageStyleType.NULL);

    }
    /**
     *
     * @param img
     * @param sourceId
     * @param defaultDrawableID 默认图ID
     */
    public static void loadViewImage(@NonNull Context context, @NonNull ImageView img, @NonNull Object sourceId, int defaultDrawableID, int defaultErrorDrawableId, @NonNull ImageStyleType type) {
        loadViewImage(context,img,sourceId,defaultDrawableID,defaultErrorDrawableId,defaultDrawableID,null,type);

    }

    /**
     *
     * @param img
     * @param sourceId
     * @param defaultDrawableID 默认图ID
     */
    public static void loadViewRoundCornerImage(@NonNull Context context, @NonNull ImageView img, @NonNull Object sourceId, int defaultDrawableID, int defaultErrorDrawableId, int raduis) {
        Glide.with(context)
                .load(sourceId)
                .diskCacheStrategy(cacheStrategy)
                .centerCrop()
                .placeholder(defaultDrawableID)
                .error(defaultErrorDrawableId)
                .crossFade(animationTime)
                .bitmapTransform(new RoundedCornersTransformation(mContext, raduis, 0, RoundedCornersTransformation.CornerType.ALL))
                .into(img);
    }

    /**
     * @param img
     * @param sourceId
     * @param defaultDrawableID 默认图ID
     */
    public static void loadViewImage(@NonNull Context context, @NonNull ImageView img, @NonNull Object sourceId, int defaultDrawableID, int defaultErrorDrawableId) {
        loadViewImage(context,img,sourceId,defaultDrawableID,defaultErrorDrawableId,defaultDrawableID,null,ImageStyleType.NULL);
    }

    /**********  先加载缩略图 后加载 原图的效果  ***********/
    /**
     * 图片加载先加载 缩略图
     *
     * @param img
     * @param sourceId
     */
    public static void loadImageWithThumb(@NonNull Context context, @NonNull ImageView img, @NonNull Object sourceId) {
        Glide.with(context).load(sourceId).diskCacheStrategy(cacheStrategy).placeholder(defaultDrawableID).crossFade(animationTime).thumbnail(0.1f).into(img);

    }
    /**
     * 加载gif图片
     * @param context
     * @param img
     * @param sourceId 可以是ID  也可以是url
     */
    public static void loadGifImage(@NonNull Context context, @NonNull final ImageView img, @NonNull Object sourceId){
        loadGifImage(context,img,sourceId,defaultDrawableID,null);
    }

    public static void loadCircleWhite(@NonNull Context context, @NonNull final ImageView img, @NonNull Object sourceId){
        Glide.with(mContext)
                .load(sourceId)
                .placeholder(icon_wode)
                .transform(new GlideCircleTransform(context, 1, ContextCompat.getColor(context, R.color.color_white)))
                .into(img);
    }

    /**
     * 加载gif图片
     * @param context
     * @param img
     * @param sourceId 可以是ID  也可以是url
     */
    public static void loadGifImage(@NonNull Context context, @NonNull final ImageView img, @NonNull Object sourceId, int defaultDrawableID, ImageLoadListener listener){
        loadListener =  listener;
        Glide.with(context)
                .load(sourceId)
                .asGif()
                .centerCrop()
                .diskCacheStrategy(cacheStrategy)
                .placeholder(defaultDrawableID)
                .crossFade(animationTime)
//                .error(defaultDrawableID)
//                .fallback(defaultDrawableID)
                .listener(gitRequestListener).into(img);
    }

    public static void loadViewImage(@NonNull Context context, @NonNull ImageView img, @NonNull Object sourceId, @NonNull int defaultDrawableID, int errorDrawableId, int fallBackDrawableId, ImageLoadListener listener, @NonNull ImageStyleType type) {
        loadListener =  listener;
            switch (type){
                case NULL:
                    Glide.with(context)
                            .load(sourceId)
                            .diskCacheStrategy(cacheStrategy)
                            .centerCrop()
                            .placeholder(defaultDrawableID)
                            .crossFade(animationTime)
//                            .error(errorDrawableId)
//                            .fallback(fallBackDrawableId)
                            .listener(requestListener)
                            .into(img);
                    break;
                case Mask:
                    Glide.with(context)
                            .load(sourceId)
                            .diskCacheStrategy(cacheStrategy)
                            .centerCrop()
                            .placeholder(defaultDrawableID)
                            .crossFade(animationTime)
//                            .error(errorDrawableId)
//                            .fallback(fallBackDrawableId)
                            .bitmapTransform(new CenterCrop(context),
                                    new MaskTransformation(context, R.mipmap.ic_launcher))
                            .listener(requestListener)
                            .into(img);
                break;
                case NinePatchMask:
                    Glide.with(context)
                            .load(sourceId)
                            .diskCacheStrategy(cacheStrategy)
                            .centerCrop()
                            .placeholder(defaultDrawableID)
                            .crossFade(animationTime)
//                            .error(errorDrawableId)
//                            .fallback(fallBackDrawableId)
                            .bitmapTransform(new CenterCrop(context),
                                    new MaskTransformation(context, R.mipmap.ic_launcher))
                            .listener(requestListener)
                            .into(img);
                    break;

                case Vignette:
                    Glide.with(context)
                            .load(sourceId)
                            .diskCacheStrategy(cacheStrategy)
                            .centerCrop()
                            .placeholder(defaultDrawableID)
                            .crossFade(animationTime)
//                            .error(errorDrawableId)
//                            .fallback(fallBackDrawableId)
                            .bitmapTransform(new VignetteFilterTransformation(context, new PointF(0.5f, 0.5f),
                                    new float[] { 0.0f, 0.0f, 0.0f }, 0f, 0.75f))
                            .listener(requestListener)
                            .into(img);
                    break;
                default:
                    Glide.with(context)
                            .load(sourceId)
                            .diskCacheStrategy(cacheStrategy)
                            .centerCrop()
                            .placeholder(defaultDrawableID)
                            .error(errorDrawableId)
                            .crossFade(animationTime)
//                            .error(errorDrawableId)
//                            .fallback(fallBackDrawableId)
                            .bitmapTransform(getBitmapTransform(context,type))
                            .listener(requestListener)
                            .into(img);
                    break;

        }
    }
    public static void loadViewImageWithWH(@NonNull Context context, @NonNull ImageView img, @NonNull Object sourceId, @NonNull int defaultDrawableID, int errorDrawableId, int fallBackDrawableId, ImageLoadListener listener, @NonNull ImageStyleType type, int width, int height) {
        loadListener =  listener;
        switch (type){
            case NULL:
                Glide.with(context)
                        .load(sourceId)
                        .diskCacheStrategy(cacheStrategy)
                        .fitCenter()
                        .placeholder(defaultDrawableID)
                        .crossFade(animationTime)
                        .override(width,height)
//                            .error(errorDrawableId)
//                            .fallback(fallBackDrawableId)
                        .listener(requestListener)
                        .into(img);
                break;
            case Mask:
                Glide.with(context)
                        .load(sourceId)
                        .diskCacheStrategy(cacheStrategy)
                        .fitCenter()
                        .placeholder(defaultDrawableID)
                        .crossFade(animationTime)
                        .override(width,height)
//                            .error(errorDrawableId)
//                            .fallback(fallBackDrawableId)
                        .bitmapTransform(new CenterCrop(context),
                                new MaskTransformation(context, R.mipmap.ic_launcher_round))
                        .listener(requestListener)
                        .into(img);
                break;
            case NinePatchMask:
                Glide.with(context)
                        .load(sourceId)
                        .diskCacheStrategy(cacheStrategy)
                        .fitCenter()
                        .override(width,height)
                        .placeholder(defaultDrawableID)
                        .crossFade(animationTime)
//                            .error(errorDrawableId)
//                            .fallback(fallBackDrawableId)
                        .bitmapTransform(new CenterCrop(context),
                                new MaskTransformation(context, R.mipmap.ic_launcher_round))
                        .listener(requestListener)
                        .into(img);
                break;

            case Vignette:
                Glide.with(context)
                        .load(sourceId)
                        .diskCacheStrategy(cacheStrategy)
                        .fitCenter()
                        .placeholder(defaultDrawableID)
                        .crossFade(animationTime)
                        .override(width,height)
//                            .error(errorDrawableId)
//                            .fallback(fallBackDrawableId)
                        .bitmapTransform(new VignetteFilterTransformation(context, new PointF(0.5f, 0.5f),
                                new float[] { 0.0f, 0.0f, 0.0f }, 0f, 0.75f))
                        .listener(requestListener)
                        .into(img);
                break;
            default:
                Glide.with(context)
                        .load(sourceId)
                        .diskCacheStrategy(cacheStrategy)
                        .placeholder(defaultDrawableID)
                        .crossFade(animationTime)
                        .override(width,height)
//                            .error(errorDrawableId)
//                            .fallback(fallBackDrawableId)
                        .fitCenter()
                        .bitmapTransform(getBitmapTransform(context,type))
                        .listener(requestListener)
                        .into(img);
                break;

        }
    }
    private static Transformation<Bitmap> getBitmapTransform(Context mContext, @NonNull ImageStyleType type) {
        switch (type) {

            case CropTop:
                return  new CropTransformation(mContext, 300, 100, CropTransformation.CropType.TOP);
            case CropCenter:
                return new CropTransformation(mContext, 300, 100);
            case CropBottom:
                return new CropTransformation(mContext, 300, 100, CropTransformation.CropType.BOTTOM);
            case CropSquare:
                return new CropSquareTransformation(mContext);
            case CropCircle:
                return  new CropCircleTransformation(mContext);
            case ColorFilter:
                return new ColorFilterTransformation(mContext, Color.argb(80, 255, 0, 0));
            case Grayscale:
                return new GrayscaleTransformation(mContext);
            case RoundedCorners:
                return  new RoundedCornersTransformation(mContext, 12, 0, RoundedCornersTransformation.CornerType.ALL);
            case Blur:
                return new BlurTransformation(mContext, 25);
            case Toon:
                return  new ToonFilterTransformation(mContext);
            case Sepia:
                return  new SepiaFilterTransformation(mContext);
            case Contrast:
                return  new ContrastFilterTransformation(mContext, 2.0f);
            case Invert:
                return  new InvertFilterTransformation(mContext);
            case Pixel:
                return  new PixelationFilterTransformation(mContext, 20);
            case Sketch:
                return  new SketchFilterTransformation(mContext);
            case Swirl:
                return  new SwirlFilterTransformation(mContext, 0.5f, 1.0f, new PointF(0.5f, 0.5f));
            case Brightness:
                return new BrightnessFilterTransformation(mContext, 0.5f);
            case Kuawahara:
               return new KuwaharaFilterTransformation(mContext, 25);

        }
        return null;
    }

    /**
     * 下载图片
     * @param context
     * @param url
     * @param listener
     */

    public  static void downloadImage(@NonNull final Context context, @NonNull final String url, final ImageLoadListener listener){
        loadListener =  listener;
        Glide.with(context)
                .load(url)
                .listener(requestListener)
                .into(new SimpleTarget<GlideDrawable>() {
                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {

                        /** 首先默认个文件保存路径 */
                        final String filePath= Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED) ? Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator : "/mnt/sdcard/";//保存到SD卡

                        BitmapUtil.saveBitmapToSD(filePath,"AmberLoading.jpg", BitmapUtil.drawableToBitmap(resource));
                        AppConfigEntity loadingInfo = SharedPreferencesUtils.getLoadingInfo(context);
                        loadingInfo.setLocalUrl("file://"+filePath +  "AmberLoading.jpg");

                        SharedPreferencesUtils.setLoadingInfo(context,loadingInfo);
                        LogUtil.zLog().e("AmberLoading");

                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        LogUtil.zLog().e("failure");
                    }
                });
    }

    /**
     * 单线程列队执行
     */
    private static ExecutorService singleExecutor = null;

    /** Glide图片下载*/
    public static void downloadPic(@NonNull final Context context, @NonNull final String url, @NonNull final ImageDownloadCallback callback){
        if (singleExecutor == null) {
            singleExecutor = Executors.newSingleThreadExecutor();
        }
        singleExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    callback.onSuccess(Glide
                            .with(context)
                            .load(url)
                            .downloadOnly(Target.SIZE_ORIGINAL, Target.SIZE_ORIGINAL)
                            .get());
                } catch (Exception ex) {
                    callback.onException(ex);
                }
            }
        });
    }

}
