package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.PageResults;
import com.ichsy.hrys.entity.TeamUserInfo;

import java.util.List;

/**
 * 功能：获取团队列表的请求返回实体
 * ＊创建者：赵然 on 2017/1/6 10:18
 * ＊
 */

public class GetTeamListResponseEntity extends BaseResponse {


    private PageResults pageResults;
    private List<TeamUserInfo> teamUserList;


    public PageResults getPageResults() {
        return pageResults;
    }

    public void setPageResults(PageResults pageResults) {
        this.pageResults = pageResults;
    }

    public List<TeamUserInfo> getTeamUserList() {
        return teamUserList;
    }

    public void setTeamUserList(List<TeamUserInfo> teamUserList) {
        this.teamUserList = teamUserList;
    }
}
