package com.ichsy.hrys.common.interfaces;

import com.ichsy.hrys.common.utils.otto.OttoEventEntity;
import com.squareup.otto.Subscribe;

/**
 * 功能： otto  接收发送的消息
 *
 */
public interface OnReceiveOttoEventInterface {
    /**
     * 接收 Otto消息 请在实现此类的方法上添加 @Subscribe注解 否则无效
     * @param eventEntity
     */
    @Subscribe
    void OnReceiveEvent(OttoEventEntity eventEntity);
}
