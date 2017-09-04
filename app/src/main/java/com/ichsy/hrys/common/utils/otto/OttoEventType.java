package com.ichsy.hrys.common.utils.otto;

/**
 * 功能：Otto 传递事件的时候 消息的类型
 */
public enum  OttoEventType {
  /**
   * 修改用户信息后 - 返回我的界面
   */
  USERIFNO_MODIFY,
  /**
   * 修改用户信息--地址
   */
  USERIFNO_MODIFY_ADDRESS,
  /**
   * 修改用户信息--添加地址
   */
  USERIFNO_MODIFY_ADDADDRESS,
  /**
   * 购买流程选择收货地址
   */
  SHOPPING_GET_GOODSADRESS,
  /**
   * 取消收藏  用于 收藏列表 帖子详情
   */
  CANCLE_COLLECTION,
  /**
   * 取消关注
   */
  CANCLE_MARKATTENTION,
  /**
   * 标记关注
   */
  MARK_ATTENTION,
  /**
   * 帖子详情购买弹框
   */
  BUGING_LAYER,
  /**
   * 登录成功
   */
  LOGIN_SUCCESS,
  /**
   * 退出登录
   */
  EXIT_LOGIN,
  /**
   * 支付完成
   */
  ORDER_PAY_FINISHED,
  /**
   * 确认订单
   */
  ORDER_CONFIRM,
  /**
   * 成为达人
   */
  BECOME_GEEK,
  /**
   * 成功上传内容任务
   */
  UPLOAD_CONTENT_SUCCESS,
  /**
   * 任务收藏状态变化
   */
  TASK_COLLECT_STATUS,
  /**
   *选择证件类型
   */
  SELECTE_IDENTIFYCARD_TYPE,
  /**
   * 选择证件
   */
  SELECTE_IDENTIFYCARD,
  /**
   * 选择银行卡
   */
  SELECTE_BANKCARD,
  /**
   * 收到Push
   */
  RECEIVE_PUSH,
  /**
   * 修改个人信息相关
   */
  OTTO_MODIFY_USERINFO,
  /**
   * 修改形象信息
   */
  OTTO_MODIFY_USERIMAGEINFO,
  /**
   * 我的报价 添加报价 or 修改报价保存成功通知
   */
  ADD_OR_EDIT_PRICE_SUCCESS,

  /**
   * 1.5.0 添加
   * 申请退款退货
   */
  REFUND_DETAIL
}
