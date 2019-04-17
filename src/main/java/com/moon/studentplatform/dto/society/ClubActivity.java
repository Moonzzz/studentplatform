package com.moon.studentplatform.dto.society;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/8 16:05
 * @description：这个类是社团活动模板
 * @modified By：
 */
public class ClubActivity {
    private int id;
    private int userId;
    private int clubId;
    private String title;
    private String image;
    private String author;
    private String date;
    private String text;
    private String description;
    private String pass;//是否通过

    public ClubActivity() {

    }

    public ClubActivity(int userId, int clubId, String title, String author, String date, String text, String description, String pass) {
        this.userId = userId;
        this.clubId = clubId;
        this.title = title;
        this.author = author;
        this.date = date;
        this.text = text;
        this.description = description;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClubId() {
        return clubId;
    }

    public void setClubId(int clubId) {
        this.clubId = clubId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ClubActivity{" +
                "id=" + id +
                ", userId=" + userId +
                ", clubId=" + clubId +
                ", title='" + title + '\'' +
                ", image='" + image + '\'' +
                ", author='" + author + '\'' +
                ", date='" + date + '\'' +
                ", text='" + text + '\'' +
                ", description='" + description + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
