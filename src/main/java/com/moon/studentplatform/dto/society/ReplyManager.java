package com.moon.studentplatform.dto.society;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/27 12:43
 * @description：这个类是
 * @modified By：
 */
public class ReplyManager {
    private int id;

    private String userName;
    private String content;
    private String datePublished;

    public ReplyManager() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }
}
