package com.ichsy.hrys.config.constants;

/**
 * author: zhu on 2017/4/12 14:09
 * email: mackkilled@gmail.com
 */

public interface StringConstant {

    /****************** 用户信息sp start  **************/
    /**
     * 用户信息整体审核进度
     */
    String SP_USERINFO_ALLCHECKSTATUS = "SP_USERINFO_ALLCHECKSTATUS";
    /**
     * 用户信息的文件名字
     */
    String SP_USERINFO_FILENAME = "fileNameSpUserInfo";
    /**
     * 用户ID
     */
    String SP_USERINFO_ID = "IDSpUserInfo";
    /**
     * 用户token
     */
    String SP_USERINFO_TOKEN = "tokenSpUserInfo";
    /**
     * 用户姓名
     */
    String SP_USERINFO_NAME = "nameSpUserInfo";
    /**
     * 头像链接
     */
    String SP_USERINFO_ICONURL = "iconSpUserInfo";
    String SP_USERINFO_ICONTHUMBURL = "thumbUserIcon";
    /**
     * 关注数量
     */
    String SP_USERINFO_ATTENTIONNUM = "attentionNumSpUserInfo";
    /**
     * 发帖数量
     */
    String SP_USERINFO_POSTNUM = "postNumSpUserInfo";
    /**
     * 粉丝数量
     */
    String SP_USERINFO_FANSNUM = "fansNumSpUserInfo";
    /**
     * 二维码地址
     */
    String SP_USERINFO_QRCODEURL = "qRCodeUrlSpUserInfo";
    /**
     * 个人背景图
     */
    String SP_USERINFO_USERINFOBG = "userbgUrl";
    /**
     * 用户介绍信息
     */
    String SP_USERINFO_INSTRODUCTION = "userIntroductionSpUserInfo";
    /**
     * 个人介绍
     */
    String SP_USERINFO_SELF_INTRODUCTION = "userSelfIntroductionSpUserInfo";
    /**
     * sex	"男：dzsd4029100100030001，女：dzsd4029100100030002，未知：dzsd4029100100030003",
     */
    String SP_USERINFO_SEX = "sexSpUserInfo";
    /**
     * 注册时间
     */
    String SP_USERINFO_REGISTTIME = "registTime";
    /**
     *
     */
    String SP_USERINFO_STARTS = "starts";
    /**
     * 二维码流
     */
    String SP_USERINFO_QRCODEFLOW = "qrCodeFlowUrl";
    /**
     * 用户类型
     */
    String SP_USERINFO_USERTYPE = "userType";
    /**
     * 当前是否是登录状态
     */
    String SP_USERINFO_LOGINSTATUS = "isLogin";
    /**
     * 个人基本信息是否已填写完成
     */
    String SP_USERINFO_ISFINISHED = "isbaseuserinfocomplete";
    /**
     * 用户接收推送状态
     */
    String SP_USERINFO_PUSHSTATUS = "userpushstatus";
    /**
     * 用户选择标签json
     */
    String SP_USERINFO_USERLABLEJSON = "userinfojson";
    String SP_USERINFO_USERLABLEJSON_DATA = "userinfojsondata";
    /**
     * 用户形象资料SP
     */
    String SP_USERIMAGEINFO = "userimageinfo";
    String SP_USERIMAGEINFO_HEIGHT = "userimageinfo_height";
    String SP_USERIMAGEINFO_WEIGHT = "userimageinfo_weight";
    String SP_USERIMAGEINFO_BUST = "userimageinfo_bust";
    String SP_USERIMAGEINFO_WAIST = "userimageinfo_waist";
    String SP_USERIMAGEINFO_HIPS = "userimageinfo_hips";
    String SP_USERIMAGEINFO_IMAGELIST = "userimageinfo_imagelist";
    String SP_USERIMAGEINFO_FAILURE = "SP_USERIMAGEINFO_FAILURE";
    /**
     * 用户认证平台信息
     */
    String SP_USERAUTHINFO = "userauthinfolist";
    String SP_USERAUTHINFO_JSON = "userauthinfolist_json";
    /****************** 用户信息SP end **************/
    /**
     * APP相关的 SP
     */
    String SP_APP_ISFRISTLOAD_NAME = "isAppFirstLoadName";
    String SP_APP_ISFRISTLOAD_KEY = "isAppFirstLoad";
    /**
     * 配置接口
     */
    String SP_CONFIG_SINA_NAME = "SINA";
    String SP_CONFIG_LOGINMOVIE_NAME = "LOGINMOVIE";
    String SP_CONFIG_LOADINGPAGE_NAME = "LODINPAGE";
    String SP_CONFIG_PATCH = "PATCH";
    String SP_CONFIG_LOCALURL = "config_localurl";
    /**
     * private  String configContent;
     * private String configType;
     * private String configUrl;
     * private String configVersion;
     * private String stats;
     */
    String SP_CONFIG_TYPE = "configtype";
    String SP_CONFIG_CONTENT = "configContent";
    String SP_CONFIG_URL = "configUrl";
    String SP_CONFIG_VERSION = "configVersion";
    String SP_CONFIG_STATS = "configStats";


    /**
     * 版本升级
     */
    String APP_UPDATE_NAME = "appUpdate";
    String APP_UPDATE_TYPE = "appUpdateType";
    String APP_UPDATE_CONTENT = "appUpdateContent";
    String APP_UPDATE_URL = "appUpdateUrl";

    String APP_UPDATE_AMBER = "dzsd4107100210030001";
    String APP_UPDATE_MAOTUAN = "dzsd4107100210030002";


    /*******************
     * Webview start
     **************/


    String WEBVIEW_JS_JSNAME = "notify";
    //关闭页面
    String JS_CLOSEWINDOW = "close_window";
    String JS_PAY = "pay";
    String JS_CONFIRM_ORDER = "confirmOrder";
    String JS_PRODUCT_COTENT_LIST = "prodcut_content_list";
    String JS_PRODUCT_TASK_LIST = "prodcut_task_list";
    String JS_POST_DETAIL = "post_detail";
    String JS_TASK_DETAIL = "task_detail";
    String JS_MATERIAL_LIBRARY = "materialLibrary";
    String JS_RETURN_MONEY = "returnMoney";
    String JS_RETURN_PROCESS = "returnProcess";
    /****************** Webview end **************/


    /******************* 界面参数传递名称 start ********************/

    /**
     * commonWebView的title名称
     */
    String WEB_TITLE_NAME = "webTitleName";
    /**
     * 界面跳转传递url
     */
    String PARAMS_URL = "url";
    /**
     * 跳转到webview界面的 链接类型 参数
     */
    String PARAMS_URLTYPE = "paramsurltype";
    /**
     * 界面传递 用户头像
     */
    String USERINFO = "userinfo";
    /**
     * 跳转修改地址界面的 操作类型  0:修改 1 添加
     */
    String MODIFYADDRESS_TYPE = "modifyAddress";
    /**
     * 跳转修改收货地址界面  操作类型为0时 传入的地址信息
     */
    String MODIFYADDRESS_ADRESS = "modifyaddressData";
    /**
     * 订单编号
     */
    String ORDER_CODE = "orderCode";
    /**
     * 界面传递的  微信openID
     */
    String WEIXIN_OPENID = "wxOpenId";
    /**
     * 界面传递的  access_token
     */
    String WEIXIN_ACCESSTOKEN = "access_token";

    /**
     * 发现推荐帖子列表标签
     */
    String DISCOVER_TOP_TAG = "discover_top_tag";
    /**
     * 帖子id
     */
    String POST_ID = "post_id";
    /**
     * 帖子评论数
     */
    String POST_COMMENT_COUNT = "post_comment_count";
    /**
     * 跳转裁剪页面的 图片地址
     */
    String IMAGE_PATH = "imagepath";
    String IMAGE_CLIPTYPE = "";
    /**
     * 跳转收货地址页面  标记是否是选择收货地址
     */
    String SELECTGOODSADDRESS = "selectGoodsAddress";
    /**
     * 界面跳转 传递userID 用户编号
     */
    String USERID = "userID";

    /*******************
     * 界面参数传递名称 start
     ********************/
    String SEX_UNKNOW = "dzsd4029100100030003";
    String SEX_MAN = "dzsd4029100100030001";
    String SEX_WOMAN = "dzsd4029100100030002";

    /**
     * 首页列表item类型
     **/
    //首页图文类型
    String HOME_LIST_PIC_TYPE = "dzsd4029100100060001";
    //首页视频类型
    String HOME_LIST_VIDEO_TYPE = "dzsd4029100100060002";

    /**
     * 帖子详情类型
     **/
    //文字类型
    String POST_DETAIL_TEXT = "dzsd4029100100080001";
    //图片类型
    String POST_DETAIL_PIC = "dzsd4029100100080002";
    //视频类型
    String POST_DETAIL_VIDEO = "dzsd4029100100080003";

    /**
     * 帖子评论接口里
     **/
    //帖子的评论
    String COMMENT_POST = "dzsd4029100100110001";
    //评论的评论
    String COMMENT_USER = "dzsd4029100100110002";

    //视屏的评论
    String COMMENT_COMMENT = "0";
    //评论人
    String COMMENT_REPLY = "1";

    /**
     * 跳转查看头像页面的数据传递
     */
    String HEADICONURL = "headiconurl";

    /**
     * 配置接口 配置类型   "dzsd402910010013001(启动页)，dzsd402910010013002(登录页)，dzsd402910010013003(新浪微博)",
     * 任务客服电话参数:dzsd402910010013005
     * 客服电话-红人:  dzsd402910010013006
     * 是否启用ReactNative类型:dzsd402910010013007
     */
    String LOADING_PAGE = "dzsd402910010013001";
    String LOGIN_MOVICE = "dzsd402910010013002";
    String PATCH = "dzsd402910010013004";
    String TASK_SERVICE_PHONE = "dzsd402910010013005";
    String RED_PERSON_SERVICE_PHONE = "dzsd402910010013006";
    String REACTNATIVE_STATUS = "dzsd402910010013007";

    /**
     * 收货地址 是否是默认 1：是 0 代表不是
     */
    String DEFAULT_ADRESS = "1";
    /**
     * 是否强制为默认地址
     */
    String IS_FORCE_DEFAULTADRESS = "isForceDefaultAdress";

    /**
     * 检查更新相关
     * <p/>
     * dzsd4107100210020001:代表调用失败，dzsd4107100210020002:代表强制升级，dzsd4107100210020003:代表不强制升级,dzsd4107100210020004:代表静默升级,dzsd4107100210020005:代表不用升级
     */
    String UPDATE_FAILURE = "dzsd4107100210020001";
    String UPDATE_NONEED = "dzsd4107100210020005";
    String UPDATE_SILENCE = "dzsd4107100210020004";
    String UPDATE_WARN = "dzsd4107100210020003";
    String UPDATE_MUST = "dzsd4107100210020002";


    /**
     * 事件Code
     */
    String EVENTCODE = "eventCode";
    String ISEVENT = "isevent";
    String PARENTEVENTCODE = "parenteventcode";

    /**
     * 用户是达人类型
     */
    String EXPERT_USER_TYPE = "dzsd4029100100020002";

    /**
     * 任务相关
     */
    /**
     * 征集类型
     * dzsd4029100100220001:图文（含图片、视频、文字）
     * dzsd4029100100220002:视频
     * dzsd4029100100220003:图片
     */
    String TASK_COMMITTYPE_IMG = "dzsd4029100100220003";
    String TASK_COMMITTYPE_VIDEO = "dzsd4029100100220002";
    String TASK_COMMITTYPE_MUTI = "dzsd4029100100220001";

    /**
     * 任务状态
     * dzsd4029100100200001:未审核
     * dzsd4029100100200002:审核失败
     * dzsd4029100100200003:未支付
     * dzsd4029100100200004:已取消
     * dzsd4029100100200005:已支付
     * dzsd4029100100200006:进行中
     * dzsd4029100100200007:已结束
     * dzsd4029100100200008:已关闭
     * dzsd4029100100200009:已终止
     */
    String TASK_STATUS_PROGRESSING = "dzsd4029100100200006";
    String TASK_STATUS_FINISHED = "dzsd4029100100200007";
    String TASK_STATUS_CLOSE = "dzsd4029100100200008";
    String TASK_STATUS_TERMINATION = "dzsd4029100100200009";
    /**
     * 任务类型
     * dzsd4029100100230001:内容任务
     * dzsd4029100100230002:销售
     */
    String TASK_TYPE_CONTENT = "dzsd4029100100230001";
    String TASK_TYPE_SALE = "dzsd4029100100230002";

    /**
     * 达人状态
     * dzsd4029100100250001:未报名
     * dzsd4029100100250002:报名待审核
     * dzsd4029100100250003:资质审核未通过
     * dzsd4029100100250004:报名已审核
     * dzsd4029100100250005:投稿待审核
     * dzsd4029100100250007:投稿已采用
     * dzsd4029100100250006:投稿已弃用
     */
    String GEEK_DOTASKSTATUS_NOTAPPLY = "dzsd4029100100250001";
    String GEEK_DOTASKSTATUS_WAITTINGFORCHECKING = "dzsd4029100100250002";
    String GEEK_DOTASKSTATUS_WAITTINGFORFACEBACK = "dzsd4029100100250003";
    String GEEK_DOTASKSTATUS_APPLYFAILURE = "dzsd4029100100250004";
    String GEEK_DOTASKSTATUS_FACEBACKCHECKING = "dzsd4029100100250005";
    String GEEK_DOTASKSTATUS_FACEBACKFAILURE = "dzsd4029100100250006";
    String GEEK_DOTASKSTATUS_FACEBACKSUCCESS = "dzsd4029100100250007";

    /**
     * 报名已截止
     */
    String TASK_APPLY_CLOSE = "dzsd4029100100260002";


    /**
     * 认证平台ID
     */

    String PLATE_SINA = "dzsd4029100100190001";
    String PLATE_WXPUBLIC = "dzsd4029100100190002";
    String PLATE_UKU = "dzsd4029100100190003";
    String PLATE_YINGKE = "dzsd4029100100190004";
    String PLATE_IN = "dzsd4029100100190009";
    String PLATE_YIZHIBO = "dzsd4029100100190008";
    String PLATE_MIAOPAI = "dzsd4029100100190006";
    String PLATE_MEIPAI = "dzsd4029100100190007";
    String PLATE_HUAJIAO = "dzsd4029100100190005";

    /**
     * 任务(用于传递数据实体的key)
     */
    String TASK_OBJ = "task_obj";

    /**
     * 商品编号（用户传递数据标识）
     */
    String PRODUCT_CODE = "product_code";
    /**
     * 达人平台认证状态
     */
    String AUTH_GOING = "dzsd4029100100240001";
    String AUTH_COMPLETE = "dzsd4029100100240002";
    String AUTH_FAILURE = "dzsd4029100100240003";

    /***
     * 银行卡----证件相关
     */
    //进入证件信息页面--参数传递
    String IDENTIFYCARDINFO = "identifycardinfo";
    /**
     * 跳转银行卡列表界面 判断是否已绑定过证件
     */
    String IS_BANDED_IDENTIFYCARD = "IS_BANDED_IDENTIFYCARD";
    /**
     * 跳转绑定证件界面 标示 是否是最终跳转到绑定银行卡界面
     */
    String IS_TOBANDBANKCARD = "isToBandBankCard";
    /**
     * 跳转到绑定银行卡界面传递参数--- 卡主姓名
     */
    String TO_BANDBANKCARD_CARDUSERNAME = "TO_BANDBANKCARD_CARDUSERNAME";
    /**
     * 跳转提现页面 传入的可提现金额
     */
    String TO_WITHDRAW_AVAILABLEMONEY = "TO_WITHDRAW_AVAILABLEMONEY";

    /**
     * 浮层引导记录保存sp名称
     */
    String SP_NAME_GUIDE = "sp_name_guide";

    String SP_GUIDE_TASK_CONTENT = "guide_card_task_content";            //内容任务引导卡片
    String SP_GUIDE_TASK_SALE = "guide_card_task_sale";               //销售任务引导卡片
    String SP_GUIDE_GEEK = "guide_card_geek";                    //达人认证，提交/关闭标签卡片
    String SP_GUIDE_TASK_DETAIL_ZHUANSHOU = "guide_arrow_zhuanshou";              //任务详情页,立即转售按钮
    String SP_GUIDE_PRODUCT_CONTENT_POST = "guide_arrow_product_content_post";   //商品内容列表,图文
    String SP_GUIDE_GEEK_SINA = "guide_arrow_geek_sina";              //达人认证,新浪微博认证
    String SP_GUIDE_POST_SHARE = "guide_post_share";                   //帖子分享，箭头

    /**
     * 立即转售,进入贴子详情,标识（浮层引导用）
     */
    String ENTITY_POST_SHOW_GUIDE = "entity_post_show_guide";

    /**
     * 是否置顶
     */
    String HOME_TASK_ITEM_TOPFLAG = "dzsd4029100100040001";

    /**
     * 标记跳转置首页第几个tab标签
     */
    String MAIN_TAB_POSITION = "main_tab_position";
    /**
     * 跳转到任务列表页面 传递的展示TAB的参数名
     */
    String TASKLIST_SHOWTABINDEX = "show_tabindex";

    /**
     * 任务详情-任务订单列表-数据实体
     */
    String TASK_ORDER_LIST_BUNDLE = "task_order_list_bundle";

    /**
     * 任务订单列表tab标识
     */
    String TASK_ORDER_LIST_TYPE_KEY = "task_order_list_type_key";

    String TASK_CODE = "task_code";
    /**
     * 任务筛选类型
     * "已收货(ocsd439310011004),已付款(ocsd439310011002),未付款(ocsd439310011001),已发货(ocsd439310011003),全部传空",
     * <p>
     * ocsd439310011001	待付款
     * ocsd439310011002	待发货
     * ocsd439310011003	待收货
     * ocsd439310011004	交易成功
     * ocsd439310011005	交易取消
     * ocsd439310011006	交易失败
     */
    String ORDERLIST_SELECTTYPE_ALL = "";
    String ORDERLIST_SELECTTYPE_WAITFORPAY = "ocsd439310011001";
    String ORDERLIST_SELECTTYPE_HAVEDPAYED = "ocsd439310011002";
    String ORDERLIST_SELECTTYPE_DELIVERY = "ocsd439310011003";
    String ORDERLIST_SELECTTYPE_HAVEFINISHED = "ocsd439310011004";
    String ORDERLIST_SELECTTYPE_CANCEL = "ocsd439310011005";
    String ORDERLIST_SELECTTYPE_FAILED = "ocsd439310011006";
    /**
     * 账户明细页面的筛选类型
     * 出账 (dzsd4029100100310001)，入账(dzsd4029100100310002)
     */
    String ACCOUNTDETAIL_SELECTTYPE_ALL = "";
    String ACCOUNTDETAIL_SELECTTYPE_OUT = "dzsd4029100100310001";
    String ACCOUNTDETAIL_SELECTTYPE_IN = "dzsd4029100100310002";
    /**
     * 明细页面账户明细条目类型
     * <p>
     * 任务（dzsd402910010029），返利（dzsd4029100100290002），提现对应状态(4497465200040002:提现成功,4497465200040003:提现处理中,4497465200040005:提现审核失败,4497465200040006:提现支付失败
     * 任务：销售任务（dzsd4029100100290001）/内容任务返利（dzsd4029100100290003）分享返利："dzsd4029100100290002"：提现对应状态(4497465200040002:提现成功,4497465200040003:提现处理中,4497465200040005:提现审核失败,4497465200040006:提现支付失败)",
     */
    String ACCOUNTDETAIL_ITEMTYPE_TASK = "dzsd402910010029";

    String ACCOUNTDETAIL_ITEMTYPE_SALETASK = "dzsd4029100100290001";
    String ACCOUNTDETAIL_ITEMTYPE_CONTENTTASK = "dzsd4029100100290003";

    String ACCOUNTDETAIL_ITEMTYPE_REBATE = "dzsd4029100100290002";
    String ACCOUNTDETAIL_ITEMTYPE_WITHDRAW_SUCCESS = "4497465200040002";
    String ACCOUNTDETAIL_ITEMTYPE_WITHDRAW_DEALING = "4497465200040003";
    String ACCOUNTDETAIL_ITEMTYPE_WITHDRAW_CHECKFAILURE = "4497465200040005";
    String ACCOUNTDETAIL_ITEMTYPE_WITHDRAW_PAYFAILURE = "4497465200040006";
    String ACCOUNTDETAIL_ITEMTYPE_TEAM = "dzsd4029100100290004";

    /**
     * 跳转到账户详情界面参数传递
     */
    String PARAMS_ACCOUNTDETAIL = "accountdetaildata";
    /**
     * 收到推送 本地保存数据
     */
    String SP_PUSHPARAMS = "pushparams";
    String SP_PUSHPARAMS_TYPE = "pushparams_type";
    String SP_PUSHPARAMS_PARAMS = "pushparams_params";
    String SP_PUSHPARAMS_USERID = "pushparams_userid";
    String SP_PUSHPARAMS_CHANNELID = "pushparams_channelid";
    /**
     * 推送 跳转到达人界面
     */
    String PARAMS_PUSH_APPLYGEEKSUCCESS = "pushparams_becomegeek";
    /**
     * 跳转到账户明细列表页面 角标值
     */
    String PARAMS_ACCOUNTDETAILLIST_INDEX = "params_accountdetaillist_index";
    /**
     * 进行中订单tab角标
     */
    String LINVING_TAB_INDEX = "linving_tab_index";
    /**
     * 内容任务已托管 （dzsd4029100100400001:已托管：dzsd4029100100400002:面议 ）
     */
    String MANAGED = "dzsd4029100100400001";
    /**
     * 首页视频分类参数
     */
    String ITEM_TYPE = "home_item_type";
    /**
     * 标识：标识首页内容列表，第一item，第一次进入无需再次请求输出，因为在NewContentTaskFragment已经请求过一次了
     */
    String NEED_REQUEST = "no_need_request";
    String RN_PAGENAME = "pageName";
    /**
     * 用户信息审核状态 dzsd4029100100320001,待审核，dzsd4029100100320002 审核成功，dzsd4029100100320003 审核失败
     * USERINFO_CHECKSTATUS_WAITINGAPPLY  等待提交--已完善未提交审核
     */
    String USERINFO_CHECKSTATUS_CHECING = "dzsd4029100100320001";
    String USERINFO_CHECKSTATUS_FAILURE = "dzsd4029100100320003";
    String USERINFO_CHECKSTATUS_SUCCESS = "dzsd4029100100320002";

    String USERINFO_CHECKSTATUS_WAITINGAPPLY = "USERINFO_CHECKSTATUS_WAITINGAPPLY";

    String SP_USERIMAGEINFO_CHECKINGSTATUS = "SP_USERIMAGEINFO_CHECKINGSTATUS";

    /**
     * 用户报价标识
     */
    String USER_OFFER = "user_offer";

    /**
     *
     * v1.5.0 添加
     */

    /**
     * 退款流程实体
     */
    String RETURN_FUND_GOODS_BEAN = "return_fund_goods_bean";

    /**
     * 是否发货  1：未发货 2：已发货
     */
    String RETURN_IS_NOT_SHIPPED = "RETURN_IS_NOT_SHIPPED";

    /**
     * 退货、退款理由编号
     */
    String RETURN_GOODS_REASON = "dzsd4029100100530001";
    String RETURN_FUNDS_REASON = "dzsd4029100100530002";

    /**
     * ---退货状态
     * dzsd4029100100540001待卖家处理
     * dzsd4029100100540002卖家已同意
     * dzsd4029100100540003卖家已拒绝
     * dzsd4029100100540004待卖家收货
     * dzsd4029100100540005卖家已收货
     * dzsd4029100100540006退货成功
     * <p>
     * ---退款状态
     * dzsd4029100100550001待卖家处理
     * dzsd4029100100550002退货成功
     * dzsd4029100100550004退款成功
     * dzsd4029100100550005退货失败/退款失败
     */

    String RETURN_GOODS_WAIT_SELLER_DEAL = "dzsd4029100100540001";
    String RETURN_GOODS_SELLER_AGREE = "dzsd4029100100540002";
    String RETURN_GOODS_SELLER_REFUSE = "dzsd4029100100540003";
    String RETURN_GOODS_WAIT_SELLER_RECEIVE = "dzsd4029100100540004";
    String RETURN_GOODS_SELLER_RECEIVED = "dzsd4029100100540005";
    String RETURN_GOODS_SUCCESS = "dzsd4029100100540006";

    String RETURN_FUNDS_WAIT_SELLER_DEAL = "dzsd4029100100550001";
    String RETURN_FUNDS_GOODS_SUCCESS = "dzsd4029100100550002";
    String RETURN_FUNDS_SUCCESS = "dzsd4029100100550004";
    String RETURN_FUNDS_GOODS_FAILED = "dzsd4029100100550005";

/****************************毛团儿*******************************/

    /**
     * 首页视频列表banner 跳转类型
     */
    String WEBVIEW = "dzsd4029100100600001";
    String VIDEO_DETAIL ="dzsd4029100100600002";
}
