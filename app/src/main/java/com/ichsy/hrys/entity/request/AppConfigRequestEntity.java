package com.ichsy.hrys.entity.request;

/**
 * 功能：
 * ＊创建者：赵然 on 2016/11/14 09:59
 * ＊
 */

public class AppConfigRequestEntity  extends  BaseRequest{
    /**
     * {
     "app_vision": "1.0.0",
     "channelId": "wifi",
     "from": "9100701",
     "idfa": "advertisingIdentifier",
     "mac": "mac",
     "model": "mi3",
     "net_type": "wifi",
     "op": "46001",
     "os": "ios",
     "os_info": "os_info",
     "product": "56mv_phone",
     "pushMobileToken": "wifi",
     "pushUserToken": "wifi",
     "screen": "800x480",
     "uniqid": "advertisingIdentifier",
     "zoo": {
     "key": "tesetkey",
     "token": " "
     }
     }
     */
    /**
     * 版本号
     */
    public String app_vision;
    /**
     * 推送ID
     */
    public String channelId;
    /**
     * 渠道号
     */
    public String from;

    /**
     *设备的唯一编号
     */
   public String idfa = "";

    /**
     *mac地址
     */
   public String mac;

    /**
     *手机型号
     */
    public String model;
    /**
     * 1是登录，2是退出
     */
    public String loginStatus;

    /**
     * 网络状态
     */
   public String net_type;
    /**
     * 运营商SIM卡国家码和网络码 ,
     */
    public String op ;
    /**
     * 平台
     */
   public String os = "android";

    /**
     * 手机操作系统及版本 ,
     */
    public String os_info;

    /**
     * 产品名称
     */
   public String product = "Amber";
    /**
     *  (string, optional): 百度云推送设备token
     */
    public String pushMobileToken;

    /**
     *百度云推送用户token
     */
    public String pushUserToken;

    /**
     *屏幕分辨率
     */
    public String screen;

    /**
     *设备的唯一标识
     */
    public String uniqid;

}
