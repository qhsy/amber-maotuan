package com.ichsy.hrys.common.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import com.google.gson.Gson;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.entity.AppConfigEntity;
import com.ichsy.hrys.entity.ArtUserInfo;
import com.ichsy.hrys.entity.MarsterFieldEntity;
import com.ichsy.hrys.entity.PushEntity;
import com.ichsy.hrys.entity.UserImageInfoEntity;
import com.ichsy.hrys.entity.response.CheckUpdateResponseEntity;
import com.ichsy.hrys.entity.sp.LableSpAaveEntity;

import java.util.ArrayList;
import java.util.List;


public class SharedPreferencesUtils {
    /*************************** 用户信息 Start **********************/
    /**
     * 存储登陆信息  注意：  此处不仅仅是保存登录信息  还有的操作：
     *
     * @param context
     * @param userInfo 用户信息
     */

    public static void saveUserMsg(Context context, ArtUserInfo userInfo) {
        //达人状态检查
//        checkGeekStatusChange(context, userInfo);
        //
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_USERINFO_FILENAME, Context.MODE_PRIVATE);
        Editor editor = sp.edit();
        editor.putString(StringConstant.SP_USERINFO_ID, userInfo.getUserCode());
        editor.putString(StringConstant.SP_USERINFO_TOKEN, userInfo.getUserToken());
        editor.putString(StringConstant.SP_USERINFO_NAME, userInfo.getUserName());
        editor.putInt(StringConstant.SP_USERINFO_ATTENTIONNUM, userInfo.getAttentionNum());
        editor.putInt(StringConstant.SP_USERINFO_FANSNUM, userInfo.getFansNum());
        editor.putInt(StringConstant.SP_USERINFO_POSTNUM, userInfo.getPostNum());
        editor.putString(StringConstant.SP_USERINFO_QRCODEURL, userInfo.getQrCodeUrl());
        editor.putString(StringConstant.SP_USERINFO_ICONURL, userInfo.getUserIconurl());
        editor.putString(StringConstant.SP_USERINFO_ICONTHUMBURL, userInfo.getUserIconThumburl());
        editor.putString(StringConstant.SP_USERINFO_INSTRODUCTION, userInfo.getUserIntroduction());
        editor.putString(StringConstant.SP_USERINFO_SELF_INTRODUCTION, userInfo.getPersonalIntroduction());
        editor.putString(StringConstant.SP_USERINFO_SEX, userInfo.getSex());
        editor.putString(StringConstant.SP_USERINFO_REGISTTIME, userInfo.getRegistTime());
        editor.putString(StringConstant.SP_USERINFO_STARTS, userInfo.getStarts());
        editor.putString(StringConstant.SP_USERINFO_QRCODEFLOW, userInfo.getQrCodeFlowUrl());
        editor.putString(StringConstant.SP_USERINFO_USERINFOBG, userInfo.getBjIconurl());
        editor.putString(StringConstant.SP_USERINFO_USERTYPE, userInfo.getUserType());

        editor.putBoolean(StringConstant.SP_USERINFO_LOGINSTATUS, userInfo.isLogin);
        editor.putBoolean(StringConstant.SP_USERINFO_ISFINISHED, userInfo.state);
        editor.commit();
        saveUserLableInfo(context, userInfo.artMasterField);

    }

    public static ArtUserInfo getUserInfo(Context context) {
        ArtUserInfo userInfo = new ArtUserInfo();
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_USERINFO_FILENAME, Context.MODE_PRIVATE);

        userInfo.setUserCode(sp.getString(StringConstant.SP_USERINFO_ID, ""));
        userInfo.setUserToken(sp.getString(StringConstant.SP_USERINFO_TOKEN, ""));
        userInfo.setUserName(sp.getString(StringConstant.SP_USERINFO_NAME, ""));
        userInfo.setAttentionNum(sp.getInt(StringConstant.SP_USERINFO_ATTENTIONNUM, 0));
        userInfo.setFansNum(sp.getInt(StringConstant.SP_USERINFO_FANSNUM, 0));
        userInfo.setPostNum(sp.getInt(StringConstant.SP_USERINFO_POSTNUM, 0));
        userInfo.setQrCodeUrl(sp.getString(StringConstant.SP_USERINFO_QRCODEURL, ""));
        userInfo.setUserIconurl(sp.getString(StringConstant.SP_USERINFO_ICONURL, ""));
        userInfo.setUserIconThumburl(sp.getString(StringConstant.SP_USERINFO_ICONTHUMBURL, ""));
        userInfo.setUserIntroduction(sp.getString(StringConstant.SP_USERINFO_INSTRODUCTION, ""));
        userInfo.setPersonalIntroduction(sp.getString(StringConstant.SP_USERINFO_SELF_INTRODUCTION, ""));
        userInfo.setSex(sp.getString(StringConstant.SP_USERINFO_SEX, "dzsd4029100100030003"));
        userInfo.setRegistTime(sp.getString(StringConstant.SP_USERINFO_REGISTTIME, ""));
        userInfo.setStarts(sp.getString(StringConstant.SP_USERINFO_STARTS, ""));
        userInfo.setQrCodeFlowUrl(sp.getString(StringConstant.SP_USERINFO_QRCODEFLOW, ""));
        userInfo.setBjIconurl(sp.getString(StringConstant.SP_USERINFO_USERINFOBG, ""));
        userInfo.setUserType(sp.getString(StringConstant.SP_USERINFO_USERTYPE, ""));

        userInfo.state = sp.getBoolean(StringConstant.SP_USERINFO_ISFINISHED, false);
        /**
         * 额外添加字段 标示当前的登录状态
         */
        userInfo.isLogin = sp.getBoolean(StringConstant.SP_USERINFO_LOGINSTATUS, false);
        userInfo.artMasterField = getUserLableInfo(context);
        return userInfo;
    }

    /**
     * 存储用户选择的领域标签
     *
     * @param context
     * @param data
     */
    private static void saveUserLableInfo(Context context, List<MarsterFieldEntity> data) {
        if (data == null) {
            return;
        }
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_USERINFO_USERLABLEJSON, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        List<MarsterFieldEntity> selectedLableList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {

            if (data.get(i) != null) {

                selectedLableList.add(data.get(i));
            }
        }
        LableSpAaveEntity saveEntity = new LableSpAaveEntity();
        saveEntity.data = selectedLableList;
        edit.putString(StringConstant.SP_USERINFO_USERLABLEJSON_DATA, new Gson().toJson(saveEntity));
        edit.commit();

    }

    /**
     * 获取用户已选择的领域标签
     *
     * @param context
     * @return
     */
    private static List<MarsterFieldEntity> getUserLableInfo(Context context) {
        Gson gson = new Gson();
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_USERINFO_USERLABLEJSON, Context.MODE_PRIVATE);
        String str = sp.getString(StringConstant.SP_USERINFO_USERLABLEJSON_DATA, "");
        if (TextUtils.isEmpty(str)) {
            return new ArrayList<>();
        } else {
            LableSpAaveEntity saveEntity = gson.fromJson(str, LableSpAaveEntity.class);
            if (saveEntity == null || saveEntity.data == null) {
                return new ArrayList<>();
            } else {

                return saveEntity.data;
            }
        }
    }

    /**
     * 存储用户形象资料
     */
    public static void saveImageUserInfo(Context context, UserImageInfoEntity data) {
        if (data == null) data = new UserImageInfoEntity();
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_USERIMAGEINFO, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putString(StringConstant.SP_USERIMAGEINFO_HEIGHT, data.getUserHeight());
        edit.putString(StringConstant.SP_USERIMAGEINFO_WEIGHT, data.getUserWeight());
        edit.putString(StringConstant.SP_USERIMAGEINFO_WAIST, data.getUserTheWaist());
        edit.putString(StringConstant.SP_USERIMAGEINFO_BUST, data.getUserBust());
        edit.putString(StringConstant.SP_USERIMAGEINFO_HIPS, data.getUserHipline());
        edit.putString(StringConstant.SP_USERIMAGEINFO_IMAGELIST, data.getImageAccording());
        edit.putString(StringConstant.SP_USERIMAGEINFO_CHECKINGSTATUS, data.getState());
        edit.putString(StringConstant.SP_USERIMAGEINFO_FAILURE, data.getAuditFailure());
        edit.commit();

    }

    /**
     * 获取用户形象资料
     */
    public static UserImageInfoEntity getImageUserInfo(Context context) {
        UserImageInfoEntity entity = new UserImageInfoEntity();
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_USERIMAGEINFO, Context.MODE_PRIVATE);
        entity.setUserHeight(sp.getString(StringConstant.SP_USERIMAGEINFO_HEIGHT, ""));
        entity.setUserWeight(sp.getString(StringConstant.SP_USERIMAGEINFO_WEIGHT, ""));
        entity.setUserBust(sp.getString(StringConstant.SP_USERIMAGEINFO_BUST, ""));
        entity.setUserTheWaist(sp.getString(StringConstant.SP_USERIMAGEINFO_WAIST, ""));
        entity.setUserHipline(sp.getString(StringConstant.SP_USERIMAGEINFO_HIPS, ""));
        entity.setImageAccording(sp.getString(StringConstant.SP_USERIMAGEINFO_IMAGELIST, ""));
        entity.setAuditFailure(sp.getString(StringConstant.SP_USERIMAGEINFO_FAILURE, ""));
        entity.setState(sp.getString(StringConstant.SP_USERIMAGEINFO_CHECKINGSTATUS, ""));

        if ((!TextUtils.isEmpty(entity.getUserHeight()) || !TextUtils.isEmpty(entity.getUserWeight()) || !TextUtils.isEmpty(entity.getUserBust()) || !TextUtils.isEmpty(entity.getUserTheWaist()) || !TextUtils.isEmpty(entity.getUserHipline()) || !TextUtils.isEmpty(entity.getImageAccording())) && TextUtils.isEmpty(entity.getState())) {
//未提交审核（资料已完善或未完善） 同时 资料已完善 状态置为 待提交审核
            entity.setState(StringConstant.USERINFO_CHECKSTATUS_WAITINGAPPLY);
        }

        return entity;
    }

/*************************** 用户信息 End **********************/
    /**
     * 标记是否是第一次进入APP   是否进入引导页
     *
     * @param context
     * @return
     */
    public static boolean isFirstLoadApp(Context context) {
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_APP_ISFRISTLOAD_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(StringConstant.SP_APP_ISFRISTLOAD_KEY, true);
    }

    /**
     * 标记APP已经加载过
     *
     * @param context
     */
    public static void setFirstLoadApp(Context context) {
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_APP_ISFRISTLOAD_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(StringConstant.SP_APP_ISFRISTLOAD_KEY, false).commit();
    }


    /**
     * 存储登录视频信息
     */
    public static void setLoginMovieInfo(Context context, AppConfigEntity entity) {
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_CONFIG_LOGINMOVIE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(StringConstant.SP_CONFIG_URL, entity.getConfigUrl()).commit();
        sp.edit().putString(StringConstant.SP_CONFIG_VERSION, entity.getConfigVersion()).commit();
        sp.edit().putString(StringConstant.SP_CONFIG_LOCALURL, entity.getLocalUrl()).commit();
    }

    /**
     * 获取登录视频信息
     *
     * @param context
     * @return
     */
    public static AppConfigEntity getLoginMovie(Context context) {
        AppConfigEntity entity = new AppConfigEntity();
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_CONFIG_LOGINMOVIE_NAME, Context.MODE_PRIVATE);
        entity.setConfigType(StringConstant.LOGIN_MOVICE);
        entity.setConfigUrl(sp.getString(StringConstant.SP_CONFIG_URL, ""));
        entity.setConfigVersion(sp.getString(StringConstant.SP_CONFIG_VERSION, ""));
        entity.setLocalUrl(sp.getString(StringConstant.SP_CONFIG_LOCALURL, ""));
        return entity;
    }

    /**
     * 获取补丁信息
     *
     * @param context
     * @return
     */
    public static AppConfigEntity getPatchInfo(Context context) {
        AppConfigEntity entity = new AppConfigEntity();
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_CONFIG_PATCH, Context.MODE_PRIVATE);
        entity.setConfigType(StringConstant.PATCH);
        entity.setConfigUrl(sp.getString(StringConstant.SP_CONFIG_URL, ""));
        entity.setConfigVersion(sp.getString(StringConstant.SP_CONFIG_VERSION, "V0.0.0"));
        entity.setLocalUrl(sp.getString(StringConstant.SP_CONFIG_LOCALURL, ""));
        return entity;
    }

    /**
     * 存储补丁信息
     */
    public static void setPatchInfo(Context context, AppConfigEntity entity) {
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_CONFIG_PATCH, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putString(StringConstant.SP_CONFIG_URL, entity.getConfigUrl()).commit();
        edit.putString(StringConstant.SP_CONFIG_VERSION, entity.getConfigVersion()).commit();
        edit.putString(StringConstant.SP_CONFIG_LOCALURL, entity.getLocalUrl()).commit();
    }

    /**
     * 存储splash页面图片信息
     */
    public static void setLoadingInfo(Context context, AppConfigEntity entity) {
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_CONFIG_LOADINGPAGE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(StringConstant.SP_CONFIG_URL, entity.getConfigUrl()).commit();
        sp.edit().putString(StringConstant.SP_CONFIG_VERSION, entity.getConfigVersion()).commit();
        sp.edit().putString(StringConstant.SP_CONFIG_LOCALURL, entity.getLocalUrl()).commit();
    }

    /**
     * 获取splash页面图片信息
     *
     * @param context
     * @return
     */
    public static AppConfigEntity getLoadingInfo(Context context) {
        AppConfigEntity entity = new AppConfigEntity();
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_CONFIG_LOADINGPAGE_NAME, Context.MODE_PRIVATE);
        entity.setConfigType(StringConstant.LOADING_PAGE);
        entity.setConfigUrl(sp.getString(StringConstant.SP_CONFIG_URL, ""));
        entity.setConfigVersion(sp.getString(StringConstant.SP_CONFIG_VERSION, ""));
        entity.setLocalUrl(sp.getString(StringConstant.SP_CONFIG_LOCALURL, ""));
        return entity;
    }

    /**
     * 存储APP更新信息
     */
    public static void saveAppUpdate(Context context, CheckUpdateResponseEntity entity) {
        SharedPreferences sp = context.getSharedPreferences(StringConstant.APP_UPDATE_NAME, Context.MODE_PRIVATE);
        sp.edit().putString(StringConstant.APP_UPDATE_CONTENT, entity.getUpgradeContent()).commit();
        sp.edit().putString(StringConstant.APP_UPDATE_TYPE, entity.getUpgradeType()).commit();
        sp.edit().putString(StringConstant.APP_UPDATE_URL, entity.getAppUrl()).commit();
    }

    /**
     * 获取APP更新信息
     *
     * @param context
     * @return
     */
    public static CheckUpdateResponseEntity getAppUpdate(Context context) {
        CheckUpdateResponseEntity entity = new CheckUpdateResponseEntity();
        SharedPreferences sp = context.getSharedPreferences(StringConstant.APP_UPDATE_NAME, Context.MODE_PRIVATE);
        entity.setAppUrl(sp.getString(StringConstant.APP_UPDATE_URL, ""));
        entity.setUpgradeType(sp.getString(StringConstant.APP_UPDATE_TYPE, ""));
        entity.setUpgradeContent(sp.getString(StringConstant.APP_UPDATE_CONTENT, ""));
        return entity;
    }

    /**
     * 存储APP更新信息
     */
    public static void savePush(Context context, PushEntity entity) {
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_PUSHPARAMS, Context.MODE_PRIVATE);
        Editor edit = sp.edit();

        edit.putString(StringConstant.SP_PUSHPARAMS_USERID, entity.getUserId());
        edit.putString(StringConstant.SP_PUSHPARAMS_CHANNELID, entity.getChannleId());
        edit.putString(StringConstant.SP_PUSHPARAMS_TYPE, entity.getType());
        edit.putString(StringConstant.SP_PUSHPARAMS_PARAMS, entity.getParams());

        edit.commit();
    }

    public static PushEntity getPush(Context context) {
        PushEntity entity = new PushEntity();

        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_PUSHPARAMS, Context.MODE_PRIVATE);

        entity.setType(sp.getString(StringConstant.SP_PUSHPARAMS_TYPE, ""));
        entity.setParams(sp.getString(StringConstant.SP_PUSHPARAMS_PARAMS, ""));
        entity.setChannleId(sp.getString(StringConstant.SP_PUSHPARAMS_CHANNELID, ""));
        entity.setUserId(sp.getString(StringConstant.SP_PUSHPARAMS_USERID, ""));

        Editor edit = sp.edit();
        edit.putString(StringConstant.SP_PUSHPARAMS_TYPE, "");
        edit.putString(StringConstant.SP_PUSHPARAMS_PARAMS, "");
        edit.commit();


        return entity;
    }


    /**
     * 清除本地信息
     *
     * @param context
     */
    public static void clearSp(Context context) {
        UMAnalyticsUtils.onUserLoginOut();
        // 登陆信息
//		SharedPreferences userSp = context.getSharedPreferences(StringConstant.SP_USERINFO_FILENAME,
//				Context.MODE_PRIVATE);
//
//		if (userSp != null) {
//			userSp.edit().clear().commit();
//		}
        ArtUserInfo userInfo = getUserInfo(context);
        ArtUserInfo loginOutUserInfo = new ArtUserInfo();
        loginOutUserInfo.isLogin = false;
        loginOutUserInfo.setUserToken(userInfo.getUserToken());
        saveUserMsg(context, loginOutUserInfo);

        //清空 授权列表 个人形象信息
        SharedPreferences imageUserInfo = context.getSharedPreferences(StringConstant.SP_USERIMAGEINFO, Context.MODE_PRIVATE);
        if (imageUserInfo != null) {
            imageUserInfo.edit().clear().commit();
        }
        SharedPreferences lableInfo = context.getSharedPreferences(StringConstant.SP_USERINFO_USERLABLEJSON, Context.MODE_PRIVATE);
        if (lableInfo != null) {
            lableInfo.edit().clear().commit();
        }
        SharedPreferences authListSP = context.getSharedPreferences(StringConstant.SP_USERAUTHINFO, Context.MODE_PRIVATE);

        if (authListSP != null) {
            authListSP.edit().clear().commit();
        }

//清空本地的推送信息
        getPush(context);
//APP更新信息
//		OttoController.post(CONTENT_TASK_PAGE, OttoEventType.EXIT_LOGIN, null);
    }


    /**
     * 存储用户推送状态信息
     *
     * @param context
     * @param whetherPush
     */
    public static void saveUserPushStatus(Context context, boolean whetherPush) {
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_USERINFO_FILENAME, Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        edit.putBoolean(StringConstant.SP_USERINFO_PUSHSTATUS, whetherPush);
        edit.commit();
    }

    /**
     * 获取用户推送状态信息
     *
     * @param context
     */
    public static boolean getUserPushStatus(Context context) {
        SharedPreferences sp = context.getSharedPreferences(StringConstant.SP_USERINFO_FILENAME, Context.MODE_PRIVATE);
        return sp.getBoolean(StringConstant.SP_USERINFO_PUSHSTATUS, true);
    }


    /*********************************  ************************************/
    private static SharedPreferences sp;

    private static void init(Context context) {
        if (sp == null) {
            sp = context.getApplicationContext().getSharedPreferences(StringConstant.SP_USERINFO_FILENAME, Context.MODE_PRIVATE);
        }
    }

    public static void putValueToSp(Context context, String key, Object value) {
        if (sp == null) {
            init(context);
        }
        Editor edit = sp.edit();
        if (value instanceof Boolean) {
            edit.putBoolean(key, (Boolean) value);
        }
        if (value instanceof String) {
            edit.putString(key, (String) value);
        }
        if (value instanceof Integer) {
            edit.putInt(key, (Integer) value);
        }
        if (value instanceof Long) {
            edit.putLong(key, (Long) value);
        }
        if (value instanceof Float) {
            edit.putFloat(key, (Float) value);
        }
        edit.commit();
    }

    /**
     * 从sp中取String取不到返回空字符
     **/
    public static String getStringToSp(Context context, String key) {
        if (sp == null) {
            init(context);
        }
        return sp.getString(key, "");
    }

    /**
     * 从sp中取String取不到返回 defaultValue
     **/
    public static String getStringToSp(Context context, String defaultValue, String key) {
        if (sp == null) {
            init(context);
        }
        return sp.getString(key, defaultValue);
    }

    /**
     * 从sp中取int取不到返
     **/
    public static int getIntToSp(Context context, String key) {
        if (sp == null) {
            init(context);
        }
        return sp.getInt(key, 0);
    }

    /**
     * 从sp中取float取不到返
     **/
    public static float getFloatToSp(Context context, String key) {
        if (sp == null) {
            init(context);
        }
        return sp.getFloat(key, 0.0f);
    }

    /**
     * 从sp中取long取不到返
     **/
    public static long getLongToSp(Context context, String key) {
        if (sp == null) {
            init(context);
        }
        return sp.getLong(key, 0);
    }

    /**
     * 从sp中取boolean取不到返回true
     **/
    public static boolean getBooleanToSp(Context context, String key) {
        if (sp == null) {
            init(context);
        }
        return sp.getBoolean(key, true);
    }

    public static void remove(Context context, String key) {
        if (sp == null) {
            init(context);
        }
        sp.edit().remove(key).commit();
    }

    public static void removeAll(Context context) {
        if (sp == null) {
            init(context);
        }
        sp.edit().clear().commit();
    }

}
