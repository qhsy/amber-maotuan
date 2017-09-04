package com.ichsy.hrys.common.utils.Bus;

import android.app.Activity;
import android.os.Bundle;

public class IntentBusEventEntity {

    private Activity fromActivity;
    private Class toActivity;
    private Bundle data;
    private int flag;

    private String toActivityName;
    private String selfEventCode;
    private String parentEventCode;
    private String childEventCode;

    private  boolean  isFinished;

    private boolean  isRoot ;

    public Activity getFromActivity() {
        return fromActivity;
    }

    public void setFromActivity(Activity fromActivity) {
        this.fromActivity = fromActivity;
    }

    public Class getToActivity() {
        return toActivity;
    }

    public void setToActivity(Class toActivity) {
        this.toActivity = toActivity;
    }

    public Bundle getData() {
        return data;
    }

    public void setData(Bundle data) {
        this.data = data;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getToActivityName() {
        return toActivityName;
    }

    public void setToActivityName(String toActivityName) {
        this.toActivityName = toActivityName;
    }

    public String getSelfEventCode() {
        return selfEventCode;
    }

    public void setSelfEventCode(String selfEventCode) {
        this.selfEventCode = selfEventCode;
    }

    public String getParentEventCode() {
        return parentEventCode;
    }

    public void setParentEventCode(String parentEventCode) {
        this.parentEventCode = parentEventCode;
    }

    public String getChildEventCode() {
        return childEventCode;
    }

    public void setChildEventCode(String childEventCode) {
        this.childEventCode = childEventCode;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setFinished(boolean finished) {
        isFinished = finished;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }
}
