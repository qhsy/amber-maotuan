# Add project specific ProGuard rules here.
# By default, the flags in this file are appended to flags specified
# in D:\application\android\android_studio\sdk/tools/proguard/proguard-android.txt
# You can edit the include path and order by changing the proguardFiles
# directive in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# Add any project specific keep options here:

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

-keepclasseswithmembernames class * {
    native <methods>;
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

#不混淆js 互调的接口方法
-keepattributes *Annotation*
-keepattributes *JavascriptInterface*
-keepclassmembers class com.ichsy.hrys.common.interfaces.JavaScriptinterface {
  public *;
}

-dontwarn javax.annotation.**

# okhttp

-keepattributes Signature
-keepattributes *Annotation*
-keep class okhttp3.** { *; }
-keep interface okhttp3.** { *; }
-dontwarn okhttp3.**

# okio

-keep class sun.misc.Unsafe { *; }
-dontwarn java.nio.file.*
-dontwarn org.codehaus.mojo.animal_sniffer.IgnoreJRERequirement
-dontwarn okio.**

# BaseQuickAdapter
-keep class com.chad.library.adapter.** {
*;
}
-keep public class * extends com.chad.library.adapter.base.BaseQuickAdapter
-keep public class * extends com.chad.library.adapter.base.BaseViewHolder
-keepclassmembers public class * extends com.chad.library.adapter.base.BaseViewHolder {
     <init>(android.view.View);
}

## Glide
-keep public class * implements com.bumptech.glide.module.GlideModule
-keep public class * extends com.bumptech.glide.AppGlideModule
-keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
  **[] $VALUES;
  public *;
}

## GSYVideoPlayer
-keep class tv.danmaku.ijk.** { *; }
-dontwarn tv.danmaku.ijk.**
-keep class com.shuyu.gsyvideoplayer.** { *; }
-dontwarn com.shuyu.gsyvideoplayer.**

## butterknife
# Retain generated class which implement Unbinder.
-keep public class * implements butterknife.Unbinder { public <init>(**, android.view.View); }
# Prevent obfuscation of types which use ButterKnife annotations since the simple name
# is used to reflectively look up the generated ViewBinding.
-keep class butterknife.*
-keepclasseswithmembernames class * { @butterknife.* <methods>; }
-keepclasseswithmembernames class * { @butterknife.* <fields>; }

-optimizationpasses 5
-dontpreverify
#-allowaccessmodification
-dontusemixedcaseclassnames
-dontskipnonpubliclibraryclasses
-keepattributes *Annotation*
#-dontskipnonpubliclibraryclassmembers
-verbose
-keepattributes Exceptions,InnerClasses

-flattenpackagehierarchy
-printmapping map.txt    #混淆前后的映射 (在build\outputs\mapping\你的项目名\release\文件夹下，出现dump.txt, map.txt, seeds.txt, usage.txt)
-printseeds seeds.txt  #未混淆的类和成员
-printusage unused.txt （dump.txt） #列出从 apk 中删除的代码

-ignorewarnings

-keep class **$Properties
-dontwarn  org.eclipse.jdt.annotation.**

-keepclassmembers class **.R$* {
    public static <fields>;
}

# 混淆时所采用的算法
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/*

-keep public class * extends android.app.Activity
-keep public class * extends android.app.Application
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.support.v4.app.FragmentActivity
-keep public class * extends android.support.v4.app.Fragment

-keepclassmembers class * implements android.os.Parcelable {
    static android.os.Parcelable$Creator CREATOR;
}


# Explicitly preserve all serialization members. The Serializable interface
# is only a marker interface, so it wouldn't save them.
-keep public class * implements java.io.Serializable {
        public *;
}
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Preserve all native method names and the names of their classes.
 -keepclasseswithmembernames class * {
    native <methods>;
}

-dontwarn sun.misc.**

-keepclassmembers class rx.internal.util.unsafe.*ArrayQueue*Field* {
     long producerIndex;
     long consumerIndex;
  }

  -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueProducerNodeRef {
      rx.internal.util.atomic.LinkedQueueNode producerNode;
  }

  -keepclassmembers class rx.internal.util.unsafe.BaseLinkedQueueConsumerNodeRef {
      rx.internal.util.atomic.LinkedQueueNode consumerNode;
  }

  #保护所有bean文件不被混淆，防止json反射失败

-keep class com.ichsy.hrys.entity.** { *;}
-keep class com.ichsy.hrys.config.**{*;}

-keep class com.ichsy.hrys.common.utils.** {*;}
-keepnames class com.ichsy.hrys.common.utils.** {*;}

##---------------Begin: proguard configuration for Gson  ----------
# Gson uses generic type information stored in a class file when working with fields. Proguard
# removes such information by default, so configure it to keep all of it.
-keepattributes Signature

# Gson specific classes
-keep class sun.misc.Unsafe { *; }
-keep class com.google.gson.stream.** { *; }
-keep class com.google.gson.examples.android.model.** { *; }
-keep class com.google.gson.** { *;}

# Application classes that will be serialized/deserialized over Gson
-keep class com.antew.redditinpictures.library.imgur.** { *; }
-keep class com.antew.redditinpictures.library.reddit.** { *; }

#---------------End: proguard configuration for Gson  ----------

#支付宝
   -keep class com.alipay.euler.** { *; }
   -keep class com.alipay.android.app.IAlixPay{*;}
   -keep class com.alipay.android.app.IAlixPay$Stub{*;}
   -keep class com.alipay.android.app.IRemoteServiceCallback{*;}
   -keep class com.alipay.android.app.IRemoteServiceCallback$Stub{*;}
   -keep class com.alipay.sdk.app.PayTask{ public *;}
   -keep class com.alipay.sdk.auth.AlipaySDK{ public *;}
   -keep class com.alipay.sdk.auth.APAuthInfo{ public *;}
   -keep class com.alipay.mobilesecuritysdk.*
   -keep class com.ut.*


#删除所有log
-assumenosideeffects class android.util.Log {
    public static *** e(...);
    public static *** w(...);
    public static *** i(...);
    public static *** d(...);
    public static *** v(...);
}

# 避免混淆Bugly
-dontwarn com.tencent.bugly.**
-keep public class com.tencent.bugly.**{*;}

## 友盟统计
-keepclassmembers class * {
   public <init> (org.json.JSONObject);
}

-keep public class com.ichsy.hrys.R$*{
public static final int *;
}

-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

### 友盟 start
-dontshrink
-dontoptimize
-dontwarn com.google.android.maps.**
-dontwarn android.webkit.WebView
-dontwarn com.umeng.**
-dontwarn com.tencent.weibo.sdk.**
-dontwarn com.facebook.**
-keep public class javax.**
-keep public class android.webkit.**
-dontwarn android.support.v4.**
-keep enum com.facebook.**
-keepattributes Exceptions,InnerClasses,Signature
-keepattributes *Annotation*
-keepattributes SourceFile,LineNumberTable

-keep public interface com.facebook.**
-keep public interface com.tencent.**
-keep public interface com.umeng.socialize.**
-keep public interface com.umeng.socialize.sensor.**
-keep public interface com.umeng.scrshot.**

-keep public class com.umeng.socialize.* {*;}


-keep class com.facebook.**
-keep class com.facebook.** { *; }
-keep class com.umeng.scrshot.**
-keep public class com.tencent.** {*;}
-keep class com.umeng.socialize.sensor.**
-keep class com.umeng.socialize.handler.**
-keep class com.umeng.socialize.handler.*
-keep class com.umeng.weixin.handler.**
-keep class com.umeng.weixin.handler.*
-keep class com.umeng.qq.handler.**
-keep class com.umeng.qq.handler.*
-keep class UMMoreHandler{*;}
-keep class com.tencent.mm.sdk.modelmsg.WXMediaMessage {*;}
-keep class com.tencent.mm.sdk.modelmsg.** implements com.tencent.mm.sdk.modelmsg.WXMediaMessage$IMediaObject {*;}
-keep class im.yixin.sdk.api.YXMessage {*;}
-keep class im.yixin.sdk.api.** implements im.yixin.sdk.api.YXMessage$YXMessageData{*;}
-keep class com.tencent.mm.sdk.** {
   *;
}
-keep class com.tencent.mm.opensdk.** {
   *;
}
-keep class com.tencent.wxop.** {
   *;
}
-keep class com.tencent.mm.sdk.** {
   *;
}
-dontwarn twitter4j.**
-keep class twitter4j.** { *; }

-keep class com.tencent.** {*;}
-dontwarn com.tencent.**
-keep class com.kakao.** {*;}
-dontwarn com.kakao.**
-keep public class com.umeng.com.umeng.soexample.R$*{
    public static final int *;
}
-keep public class com.linkedin.android.mobilesdk.R$*{
    public static final int *;
}
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

-keep class com.tencent.open.TDialog$*
-keep class com.tencent.open.TDialog$* {*;}
-keep class com.tencent.open.PKDialog
-keep class com.tencent.open.PKDialog {*;}
-keep class com.tencent.open.PKDialog$*
-keep class com.tencent.open.PKDialog$* {*;}
-keep class com.umeng.socialize.impl.ImageImpl {*;}
-keep class com.sina.** {*;}
-dontwarn com.sina.**
-keep class  com.alipay.share.sdk.** {
   *;
}

-keepnames class * implements android.os.Parcelable {
    public static final ** CREATOR;
}

-keep class com.linkedin.** { *; }
-keep class com.android.dingtalk.share.ddsharemodule.** { *; }
-keepattributes Signature

### 友盟 end

### 主要是不混淆TabLayout 的反射修改下划线方式
-keep class android.support.design.widget.** {*;}

-keep class android.support.v4.** {*;}

#不提示这些
-dontwarn org.apache.**
-dontwarn net.sf.**
-dontwarn demo.**
-dontwarn com.squareup.picasso.**
-dontwarn com.jeremyfeinstein.**
-dontwarn com.baidu.**
-dontwarn com.alibaba.**
-dontwarn org.apache.**
-dontwarn com.umeng.**
-dontwarn com.nostra13.**