package com.ichsy.hrys.entity.response;


import com.ichsy.hrys.entity.Comment;
import com.ichsy.hrys.entity.PageResults;

import java.util.ArrayList;
import java.util.List;

/**
 * author: ihesen on 2016/5/12 15:24
 * email: hesen@ichsy.com
 */
public class CommentListResponseEntity extends BaseResponse {
    /**
     * 评论列表
     */
    public List<Comment> commentList = new ArrayList<>();
    /**
     * 分页信息
     */
    public PageResults pageResults = new PageResults();
}
