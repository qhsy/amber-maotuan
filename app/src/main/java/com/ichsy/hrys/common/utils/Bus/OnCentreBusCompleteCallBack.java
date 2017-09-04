package com.ichsy.hrys.common.utils.Bus;

/**
 * 功能：核心总线处理完成任务后的回调
 * ＊创建者：赵然 on 16/7/1 17:35
 * ＊
 */
public interface OnCentreBusCompleteCallBack {
    void OnCentreBusFailure(Exception e);
    void onCentreBusSuccess();
}
