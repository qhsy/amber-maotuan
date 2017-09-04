package com.ichsy.hrys.common.utils.Bus;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.ichsy.hrys.common.utils.LoginUtils;
import com.ichsy.hrys.config.constants.StringConstant;
import com.ichsy.hrys.model.login.LoginActivity;

import java.util.HashMap;
import java.util.Map;


/**
 * 功能：
 * 创建者：赵然 on 16/7/1 17:42
 *
 */
public enum IntentBus  {

    INSTANCE;

    private IntentBus() {}

    public static IntentBus getInstance(){
        return INSTANCE;
    }
    /**
     * 记录需要跳转新界面的界面集合
     */
    private Map<Class,Class> needLoginActivityMap = new HashMap<>();
    /**
     * 记录事件
     */
    private Map<String,IntentBusEventEntity> eventMap = new HashMap<>();
    /**
     * 记录跳转到的ACTIVITY 于当前事件编号 用于关联上下层事件
     */
    private Map<String ,String> toActivityMap = new HashMap<>();

    public void onParamsEventComplete(String eventTag, boolean isSuccess, Object data) {

    }

    /**
     * 发起事件的 回调集合
     */
    private Map<String,OnCentreBusCompleteCallBack> finshedCallbackMap;

    /**
     * 设置总事件 完成后的回调监听
     * @param eventTag
     * @param finshedCallback
     */

    public void setFinshedCallback(String eventTag, OnCentreBusCompleteCallBack finshedCallback) {

        finshedCallbackMap.put(eventTag,finshedCallback);
    }
    /**
     * Activity 页面跳转
     *
     * @param fromActivity
     *            从哪个页面跳得
     * @param toActivity
     *            到哪个页面去得
     */
    public void startActivity(Activity fromActivity, Class toActivity) {

        Intent intent = new Intent(fromActivity, toActivity);
        startActivity(fromActivity, toActivity, new Bundle(),intent.getFlags());
    }

    /**
     * Activity 页面跳转
     *
     * @param fromActivity
     *            从哪个页面跳得
     * @param toActivity
     *            到哪个页面去得
     * @param bundle
     *            携带的数据
     */
    public  void startActivity(Activity fromActivity, Class toActivity, Bundle bundle) {
        Intent intent = new Intent(fromActivity, toActivity);
        startActivity(fromActivity, toActivity, bundle,intent.getFlags());
    }

    /**
     * Activity 页面跳转
     *
     * @param fromActivity
     *            从哪个页面跳得
     * @param toActivity
     *            到哪个页面去得
     * @param flag
     *            Activity 跳转标示
     */
    public  void startActivity(Activity fromActivity, Class toActivity, int flag) {
        startActivity(fromActivity, toActivity, new Bundle(),flag);
    }

    /**
     * 最终调用的页面他跳转
     * @param fromActivity
     * @param toActivity
     * @param bundle
     *
     */
    public void startActivity(Activity fromActivity, Class toActivity, Bundle bundle, int flag) {


        bundle.putString(StringConstant.EVENTCODE,createEventCode(fromActivity,toActivity));

        if (needLoginActivityMap.get(toActivity) != null ){
            if ( LoginActivity.class == needLoginActivityMap.get(toActivity)
                    && !isLogin(fromActivity)){

                if (!eventMap.containsKey(createEventCode(fromActivity,toActivity))){
                    //说明是第一次调用
                    bundle.putString(StringConstant.PARENTEVENTCODE,createEventCode(fromActivity,toActivity));
                    addEventToMap(fromActivity,toActivity,bundle,flag,true);
                    startActivity(fromActivity, LoginActivity.class,bundle);
                }else{
                    //调用过一次  这次执行的是最终事件  不满足条件则不重复调用
                    eventMap.remove(createEventCode(fromActivity,toActivity));
                }

            }else{
                addEventToMap(fromActivity,toActivity,bundle,flag,false);
                toTargetActivity(fromActivity,toActivity,bundle,flag);
            }

        }else{
            addEventToMap(fromActivity,toActivity,bundle,flag,false);
            toTargetActivity(fromActivity,toActivity,bundle,flag);
        }

    }

    private void toTargetActivity(Activity fromActivity, Class toActivity, Bundle bundle, int flag){
        //不需要登录或已登录
        Intent intent = new Intent();
        intent.setClass(fromActivity,toActivity);
        intent.setFlags(flag);
        intent.putExtras(bundle);
        bundle.putString(StringConstant.PARENTEVENTCODE,"");

        fromActivity.startActivity(intent);
    }
    /**
     * 事件添加到 集合中
     */
    private void addEventToMap(Activity fromActivity, Class toActivity, Bundle bundle, int flag, boolean  isRoot){

        IntentBusEventEntity eventEntity = new IntentBusEventEntity();
        eventEntity.setToActivity(toActivity);
        eventEntity.setData(bundle);
        eventEntity.setFromActivity(fromActivity);
        eventEntity.setFlag(flag);

        eventEntity.setSelfEventCode(createEventCode(fromActivity,toActivity));
        eventEntity.setFinished(false);
        eventEntity.setRoot(isRoot);

        eventMap.put(eventEntity.getSelfEventCode(),eventEntity);

    }

    private  boolean  isLogin(Context context){
        return LoginUtils.isLogin(context);
    }


    public void addNeedToBeforeActivity(Class clazz, Class toClazz) {
        needLoginActivityMap.put(clazz,toClazz);
    }

    /**
     * 设置当前view的ID  更新Map
     * @param eventTag  事件ID
     * @param currentActivityName  当前view的类名
     */
    public   void setCurrentViewID(String eventTag, String currentActivityName, String parentEventCodeId){

        //更新事件Map数据
        //添加
        IntentBusEventEntity intentBusEventEntity = eventMap.get(eventTag);
        if (intentBusEventEntity != null){

            intentBusEventEntity.setToActivityName(currentActivityName);

            if (TextUtils.isEmpty(parentEventCodeId)){
                //查找toActivityMap 是否有记录
                if (! TextUtils.isEmpty(toActivityMap.get(intentBusEventEntity.getFromActivity().toString()))){

                    String parentEventCode = toActivityMap.get(intentBusEventEntity.getFromActivity().toString());
//设置子事件的父Code
                    intentBusEventEntity.setParentEventCode(parentEventCode);
                    eventMap.put(eventTag,intentBusEventEntity);
//设置父事件的子code
                    IntentBusEventEntity intentBusEventEntity1 = eventMap.get(parentEventCode);
                    intentBusEventEntity1.setChildEventCode(eventTag);
                    eventMap.put(parentEventCode,intentBusEventEntity1);
                }
            }else{
//设置子事件的父Code
                intentBusEventEntity.setParentEventCode(parentEventCodeId);
                eventMap.put(eventTag,intentBusEventEntity);
//设置父事件的子code
                IntentBusEventEntity intentBusEventEntity1 = eventMap.get(parentEventCodeId);
                intentBusEventEntity1.setChildEventCode(eventTag);
                eventMap.put(parentEventCodeId,intentBusEventEntity1);
            }

            //添加跳转Activity的事件关联
            toActivityMap.put(currentActivityName,eventTag);
        }

    }

    /**
     * 设置事件完成
     * @param eventCode  事件临时编号
     */
    public  void setEventComplete(String eventCode){

        IntentBusEventEntity intentBusEventEntity = eventMap.get(eventCode);

        if (intentBusEventEntity != null){

            intentBusEventEntity.setFinished(true);
            eventMap.put(eventCode,intentBusEventEntity);

            if (TextUtils.isEmpty(intentBusEventEntity.getChildEventCode())){
                //清除当前事件 相关Activity
                if (TextUtils.isEmpty(intentBusEventEntity.getParentEventCode())){
                    //父类为空 则 完成最终操作

                    if (intentBusEventEntity.isRoot()){

                        toTargetActivity(intentBusEventEntity.getFromActivity()
                                ,intentBusEventEntity.getToActivity()
                                ,intentBusEventEntity.getData()
                                ,intentBusEventEntity.getFlag());
                    }else{
                        eventMap.remove(eventCode);
                        toActivityMap.remove(intentBusEventEntity.getToActivityName());
                    }

                }else{
                    //父类不为空 则向上查找
                    eventMap.remove(eventCode);
                    toActivityMap.remove(intentBusEventEntity.getToActivityName());
                    clearParentEvent(intentBusEventEntity.getParentEventCode());
                }
            }
        }else{

            //第一个展示页面 会走  如splash  MainActivity  不用处理
        }

    }

    /**
     * 最终事件处理完成后向上查找父类事件 依次清空 直到 事件未完成 或者没有父类事件（处理最终事件）
     */
    private void clearParentEvent(String parentEventCode){
        if (eventMap.containsKey(parentEventCode)){
            IntentBusEventEntity intentBusEventEntity = eventMap.get(parentEventCode);

            intentBusEventEntity.setChildEventCode("");

            eventMap.put(parentEventCode,intentBusEventEntity);

            if ( TextUtils.isEmpty(intentBusEventEntity.getParentEventCode()) && intentBusEventEntity.isRoot()){
                startActivity(intentBusEventEntity.getFromActivity()
                        ,intentBusEventEntity.getToActivity()
                        ,intentBusEventEntity.getData()
                        ,intentBusEventEntity.getFlag());
                //  不清除 event事件--便于这次调用时对比  如果eventMap中有这个事件 说明执行过   如果判断条件不符合则不进行后续跳转操作
//                eventMap.remove(parentEventCode);
                toActivityMap.remove(intentBusEventEntity.getToActivityName());
            }else{

                if (intentBusEventEntity.isFinished()){

                    clearParentEvent(intentBusEventEntity.getParentEventCode());
                }
            }
        }


    }
    /**
     * 创建事件的编号
     * @param fromActivity
     * @param toActivity
     * @return
     */
    private String createEventCode(Activity fromActivity, Class toActivity){


        return fromActivity.toString()+toActivity.toString();
    }
}
