package com.moon.studentplatform.dto;

import java.sql.Date;

public class Found {
    private int id;
    private String title;
    private String type;
    private String adress;
    private String description;
    private String number;
    Date publishtime;

    public Found(int id, String title, String type, String adress, String description, String number, Date publishtime) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.adress = adress;
        this.description = description;
        this.number = number;
        this.publishtime = publishtime;
    }

    public Found(String title, String type, String adress, String description, String number, Date publishtime) {
        this.title = title;
        this.type = type;
        this.adress = adress;
        this.description = description;
        this.number = number;
        this.publishtime = publishtime;
    }

    public Found() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    @Override
    public String toString() {
        return "Found{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", adress='" + adress + '\'' +
                ", description='" + description + '\'' +
                ", number='" + number + '\'' +
                ", publishtime=" + publishtime +
                '}';
    }
}
