package com.moon.studentplatform.dto.society;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/15 9:55
 * @description：这个类是社团成员类
 * @modified By：
 */
public class ClubUser {
    private int id;
    private int user_id;
    private int club_id;

    private String username;
    private String clubname;
    private String reason;
    private String experience;
    private String joindate;
    private String pass;

    public ClubUser() {
    }

    public ClubUser(String username, String clubname, String reason, String experience, String joindate, String pass) {
        this.username = username;
        this.clubname = clubname;
        this.reason = reason;
        this.experience = experience;
        this.joindate = joindate;
        this.pass = pass;
    }

    public ClubUser(int user_id, int club_id, String reason, String experience, String joindate, String pass) {
        this.user_id = user_id;
        this.club_id = club_id;
        this.reason = reason;
        this.experience = experience;
        this.joindate = joindate;
        this.pass = pass;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getClub_id() {
        return club_id;
    }

    public void setClub_id(int club_id) {
        this.club_id = club_id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getJoindate() {
        return joindate;
    }

    public void setJoindate(String joindate) {
        this.joindate = joindate;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getClubname() {
        return clubname;
    }

    public void setClubname(String clubname) {
        this.clubname = clubname;
    }

    @Override
    public String toString() {
        return "ClubUser{" +
                "user_id=" + user_id +
                ", club_id=" + club_id +
                ", reason='" + reason + '\'' +
                ", experience='" + experience + '\'' +
                ", joindate='" + joindate + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
