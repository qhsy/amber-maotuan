package com.ichsy.hrys.entity;

/**
 * 发现列表title信息
 * author: ihesen on 2016/8/1 15:14
 * email: hesen@ichsy.com
 */
public class DiscoverTitle {

    //任务更多
    public static final int TASK_MORE = 1;
    //达人更多
    public static final int EXPERT_MORE = TASK_MORE + 1;
    //AMBer学概论更多
    public static final int POST_LIST_MORE = EXPERT_MORE + 1;

    public String titleName;
    public int eventID;
}
