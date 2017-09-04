package com.ichsy.hrys.entity;

/**
 * 功能：配置接口实体
 * ＊创建者：赵然 on 16/5/29 22:47
 * ＊
 */
public class AppConfigEntity {
    /**
     {
     "artConfiGuration": [
     {
     "configContent": "string",
     "configType": "dzsd402910010013001(启动页)，dzsd402910010013002(登录页)，dzsd402910010013003(新浪微博),dzsd402910010013004 补丁",
     "configUrl": "string",
     "configVersion": "string",
     "stats": "dzsd4699100110010002(否)，dzsd4699100110010001(是)"
     }
     ],
     "zoo": {
     "key": "tesetkey",
     "token": " "
     }
     }
     */
    /**
     * "configType": "dzsd402910010013001(启动页)，dzsd402910010013002(登录页)，dzsd402910010013003(新浪微博)",
     */
    /**
     * 视频链接 补丁链接
     */
    private String configContent;
    private String configType;
    //图片相关的链接
    private String configUrl;
    private String configVersion;
    private String stats;

    /**
     * 额外添加的地址   如片 补丁 视频 图片下载完成后本地保存地址
     */

    private String localUrl;

    public String getConfigContent() {
        if (null == configContent) configContent = "";
        return configContent;
    }

    public void setConfigContent(String configContent) {
        this.configContent = configContent;
    }

    public String getConfigType() {
        if (null == configType) configType = "";
        return configType;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigUrl() {
        if (null == configUrl) configUrl = "";
        return configUrl;
    }

    public void setConfigUrl(String configUrl) {
        this.configUrl = configUrl;
    }

    public String getConfigVersion() {
        if (null == configVersion) configVersion = "";
        return configVersion;
    }

    public void setConfigVersion(String configVersion) {
        this.configVersion = configVersion;
    }

    public String getStats() {
        if (null == stats) stats = "";
        return stats;
    }

    public void setStats(String stats) {
        this.stats = stats;
    }

    public String getLocalUrl() {
        return localUrl;
    }

    public void setLocalUrl(String localUrl) {
        this.localUrl = localUrl;
    }
}
