package com.depth.cms.content.entity;

import java.io.Serializable;

/**
 * 文章评论实体
 * <p>@author weifeng 作者</p>
 */
public class CommentEntity implements Serializable {

    public static final int NORMAL_STATE = 1; //评论的正常状态

    public static final int CANCEL_STATE = 2; //评论的删除状态

    private Long id;

    private Long articleId; //文章id

    private String content; //内容

    private Integer state; //状态：1正常，2删除

    private Integer report; //举报数

    private Integer likes; //点赞数

    private Long aa01; //评论人

    private Long aa02; //评论时间

    private String commentTime; //评论时间

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getArticleId() {
        return articleId;
    }

    public void setArticleId(Long articleId) {
        this.articleId = articleId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Integer getReport() {
        return report;
    }

    public void setReport(Integer report) {
        this.report = report;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Long getAa01() {
        return aa01;
    }

    public void setAa01(Long aa01) {
        this.aa01 = aa01;
    }

    public Long getAa02() {
        return aa02;
    }

    public void setAa02(Long aa02) {
        this.aa02 = aa02;
    }

    public String getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(String commentTime) {
        this.commentTime = commentTime;
    }
}
