package com.moon.studentplatform.dto.official;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/21 21:40
 * @description：这个类是讲座类
 * @modified By：
 */
public class Lecture {
    int id;

    String title;
    String dateStart;
    String dateEnd;
    String context;
    String type;

    public Lecture() {
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


    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    @Override
    public String toString() {
        return "Lecture{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dateStart='" + dateStart + '\'' +
                ", dateEnd='" + dateEnd + '\'' +
                ", context='" + context + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
