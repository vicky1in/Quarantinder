package com.groun24.quarantinder.Modal;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class CommentVo {
    private String commentBody;
    private String name;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Australia/Sydney")
    private Date timePosted;

    public CommentVo(String commentBody, String name, Date timePosted) {
        this.commentBody = commentBody;
        this.name = name;
        this.timePosted = timePosted;
    }

    public String getCommentBody() {
        return commentBody;
    }

    public String getName() {
        return name;
    }

    public Date getTimePosted() {
        return timePosted;
    }

    public void setCommentBody(String commentBody) {
        this.commentBody = commentBody;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimePosted(Date timePosted) {
        this.timePosted = timePosted;
    }
}
