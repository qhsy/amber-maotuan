package com.ichsy.hrys.entity.share;

/**
 * 功能：分享平台的基类
 * ＊创建者：赵然 on 16/8/4 14:25
 * ＊
 */
public abstract class BaseSharePlatform {
    /**
    * @param showName 显示名称
    * @param plateName 平台标记名称
    * @param iconOnName 可选图标的名称---R.drawable.logo 则传logo
    * @param iconOffName 不可选图标的名称---R.drawable.logo 则传logo
     **/
    private String showName;
    private String plateName;
    private String iconOnName;
    private String iconOffName;

    public BaseSharePlatform(){

        init();
    }
    private void init(){
        setShowName(initShowName());
        setPlateName(initPlateName());
        setIconOffName(initIconOffName());
        setIconOnName(initIconOnName());
    }

    public String getShowName() {
        return showName;
    }

    public void setShowName(String showName) {
        this.showName = showName;
    }

    public String getPlateName() {
        return plateName;
    }

    public void setPlateName(String plateName) {
        this.plateName = plateName;
    }

    public String getIconOnName() {
        return iconOnName;
    }

    public void setIconOnName(String iconOnName) {
        this.iconOnName = iconOnName;
    }

    public String getIconOffName() {
        return iconOffName;
    }

    public void setIconOffName(String iconOffName) {
        this.iconOffName = iconOffName;
    }

    abstract String initShowName();
    abstract String initPlateName();
    abstract String initIconOnName();
    abstract String initIconOffName();
    public abstract void setShareContent(Object o);
    public abstract void share();


}
