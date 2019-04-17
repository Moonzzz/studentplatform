package com.moon.studentplatform.dto.society;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/6 18:27
 * @description：这个类是社团组织类
 * @modified By：
 */
public class Club {
    private int id;
    private String name;
    private String description;//社团描述
    private String datepublished;
    private String firstman;
    private int count;//组织类型
    private String icon;
    private String phonum;
    private String pass;

    public Club() {
    }

    public Club(int id, String name, String description, String datepublished, String firstman, String phonum, String pass) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.datepublished = datepublished;
        this.firstman = firstman;
        this.phonum = phonum;
        this.pass = pass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDatepublished() {
        return datepublished;
    }

    public void setDatepublished(String datepublished) {
        this.datepublished = datepublished;
    }

    public String getFirstman() {
        return firstman;
    }

    public void setFirstman(String firstman) {
        this.firstman = firstman;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getPhonum() {
        return phonum;
    }

    public void setPhonum(String phonum) {
        this.phonum = phonum;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Club(String name, String datepublished, String firstman, String phonum, String description) {
        this.name = name;
        this.datepublished = datepublished;
        this.firstman = firstman;
        this.phonum = phonum;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Club{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", datepublished='" + datepublished + '\'' +
                ", firstman='" + firstman + '\'' +
                ", count=" + count +
                ", icon='" + icon + '\'' +
                ", phonum='" + phonum + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
