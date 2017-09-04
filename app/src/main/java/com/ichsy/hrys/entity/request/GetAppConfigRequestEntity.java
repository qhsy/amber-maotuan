package com.ichsy.hrys.entity.request;


import com.ichsy.hrys.entity.AppConfigEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 功能：配置接口请求实体
 * ＊创建者：赵然 on 16/5/29 22:47
 * ＊
 */
public class GetAppConfigRequestEntity extends  BaseRequest {

    /**
     * {
     "artConfiGuration": [
     {
     "configContent": "string",
     "configType": "string",
     "configUrl": "string",
     "configVersion": "string",
     "stats": "string"
     }
     ],
     "zoo": {
     "key": "tesetkey",
     "token": " "
     }
     }
     */
    public List<AppConfigEntity> artConfiGuration = new ArrayList<>();


}
