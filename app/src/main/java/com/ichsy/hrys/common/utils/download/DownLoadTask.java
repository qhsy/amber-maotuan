package com.ichsy.hrys.common.utils.download;

import java.util.HashMap;

public class DownLoadTask {

    public static final String ID = "_id";
    public static final String URL = "_url";
    public static final String MIMETYPE = "_mimetype";
    public static final String SAVEPATH = "_savepath";
    public static final String FINISHEDSIZE = "_finishedsize";
    public static final String TOTALSIZE = "_totalsize";
    public static final String NAME = "_name";
    public static final String STATUS = "_status";

    public static final int STATUS_PENDDING = 1 << 0;//1

    public static final int STATUS_RUNNING = 1 << 1;//2

    public static final int STATUS_PAUSED = 1 << 2;//4

    public static final int STATUS_CANCELED = 1 << 3;//8

    public static final int STATUS_FINISHED = 1 << 4;//16

    public static final int STATUS_ERROR = 1 << 5;//32

    private String id;

    private String name;

    private String url;

    private String mimeType;

    private String dlSavePath;

    private long dlFinishSize;

    private long dlTotalSize;

    private long dlSpeed;

    private int status;

    private String DownloadFolderPath;
    private HashMap<String, String> map;


    public HashMap<String, String> getMap() {
        return map;
    }

    public void setMap(HashMap<String, String> map) {
        this.map = map;
    }

    public DownLoadTask() {
        dlFinishSize = 0;
        dlTotalSize = 0;
        status = STATUS_PENDDING;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getDlSavePath() {
        return dlSavePath;
    }

    public void setDlSavePath(String dlSavePath) {
        this.dlSavePath = dlSavePath;
    }

    public long getDlFinishSize() {
        return dlFinishSize;
    }

    public void setDlFinishSize(long dlFinishSize) {
        this.dlFinishSize = dlFinishSize;
    }

    public long getDlTotalSize() {
        return dlTotalSize;
    }

    public void setDlTotalSize(long dlTotalSize) {
        this.dlTotalSize = dlTotalSize;
    }

    public long getDlSpeed() {
        return dlSpeed;
    }

    public void setDlSpeed(long dlSpeed) {
        this.dlSpeed = dlSpeed;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (!(o instanceof DownLoadTask)) {
            return false;
        }

        DownLoadTask task = (DownLoadTask) o;
        if (this.name == null || this.dlSavePath == null) {
            return this.url.equals(task.url);
        }

        return this.name.equals(task.name) && this.url.equals(task.url) && this.dlSavePath.equals(task.dlSavePath);
    }

    @Override
    public int hashCode() {
        int code = name == null ? 0 : name.hashCode();
        code += url.hashCode();
        return code;
    }

    public String getDownloadFolderPath() {
        return DownloadFolderPath;
    }

    public void setDownloadFolderPath(String downloadFolderPath) {
        DownloadFolderPath = downloadFolderPath;
    }


}
