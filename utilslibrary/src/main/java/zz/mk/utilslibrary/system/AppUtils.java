package zz.mk.utilslibrary.system;

import android.content.Context;
import android.content.pm.PackageManager;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import zz.mk.utilslibrary.walle.WalleChannelReader;

/**
 * author: zhu on 2017/4/21 10:33
 * email: mackkill@gmail.com
 */

public class AppUtils {

    /**
     * 得到软件版本号
     *
     * @param context 上下文
     * @return 当前版本Code
     */
    public static int getVerCode(Context context) {
        int verCode = -1;
        try {
            String packageName = context.getPackageName();
            verCode = context.getPackageManager()
                    .getPackageInfo(packageName, 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verCode;
    }

    /**
     * 得到软件显示版本信息
     *
     * @param context 上下文
     * @return 当前版本信息
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            String packageName = context.getPackageName();
            verName = context.getPackageManager()
                    .getPackageInfo(packageName, 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
    public static String getAndroidId(Context context) {
        String result = "";
        try {
            result = android.provider.Settings.Secure.getString(
                    context.getContentResolver(),
                    android.provider.Settings.Secure.ANDROID_ID);
            if (result == null) {
                result = "NoAndroidId";
            }
        } catch (Exception e) {
            result = "NoAndroidId";
        }
        return result;
    }

    public static String getSeralNo(Context context) {
        String result = "";
        try {
            Class<?> c = Class.forName("android.os.SystemProperties");
            Method get = c.getMethod("get", String.class, String.class);
            result = (String) (get.invoke(c, "ro.serialno", "NoSerail"));
        } catch (Exception ignored) {
            result = "NoSerail";
        }

        return result;
    }

    public static String getDefaultChannel(Context mContext) {
        String result = "";
        try {
            if (mContext != null
                    && mContext.getPackageManager() != null
                    && mContext.getPackageManager().getApplicationInfo(
                    mContext.getPackageName(),
                    PackageManager.GET_META_DATA) != null
                    && mContext.getPackageManager().getApplicationInfo(
                    mContext.getPackageName(),
                    PackageManager.GET_META_DATA).metaData != null
                    && mContext.getPackageManager().getApplicationInfo(
                    mContext.getPackageName(),
                    PackageManager.GET_META_DATA).metaData
                    .getString("security_channel_id") != null) {
                result = mContext.getPackageManager()
                        .getApplicationInfo(mContext.getPackageName(),
                                PackageManager.GET_META_DATA).metaData
                        .getString("security_channel_id");
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }

    /**
     * 获取手机系统SDK版本
     *
     * @return 如API 17 则返回 17
     */
    public static int getSDKVersion() {
        return android.os.Build.VERSION.SDK_INT;
    }


    /**
     * 是否Dalvik模式
     *
     * @return 结果
     */
    public static boolean isDalvik() {
        return "Dalvik".equals(getCurrentRuntimeValue());
    }


    /**
     * 是否ART模式
     *
     * @return 结果
     */
    public static boolean isART() {
        String currentRuntime = getCurrentRuntimeValue();
        return "ART".equals(currentRuntime) ||
                "ART debug build".equals(currentRuntime);
    }

    /**
     * 获取手机当前的Runtime
     *
     * @return 正常情况下可能取值Dalvik, ART, ART debug build;
     */
    public static String getCurrentRuntimeValue() {
        try {
            Class<?> systemProperties = Class.forName(
                    "android.os.SystemProperties");
            try {
                Method get = systemProperties.getMethod("get", String.class,
                        String.class);
                if (get == null) {
                    return "WTF?!";
                }
                try {
                    final String value = (String) get.invoke(systemProperties,
                            "persist.sys.dalvik.vm.lib",
                        /* Assuming default is */"Dalvik");
                    if ("libdvm.so".equals(value)) {
                        return "Dalvik";
                    }
                    else if ("libart.so".equals(value)) {
                        return "ART";
                    }
                    else if ("libartd.so".equals(value)) {
                        return "ART debug build";
                    }

                    return value;
                } catch (IllegalAccessException e) {
                    return "IllegalAccessException";
                } catch (IllegalArgumentException e) {
                    return "IllegalArgumentException";
                } catch (InvocationTargetException e) {
                    return "InvocationTargetException";
                }
            } catch (NoSuchMethodException e) {
                return "SystemProperties.get(String key, String def) method is not found";
            }
        } catch (ClassNotFoundException e) {
            return "SystemProperties class is not found";
        }
    }

    public static String getChannel(Context context) {
        return WalleChannelReader.getChannel(context.getApplicationContext());
    }
}
