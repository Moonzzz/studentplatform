package com.moon.studentplatform.domain;

import lombok.Data;

import java.sql.Timestamp;

/**
 * 评论回复 基类
 * @author Moon
 */
@Data
class Discuss {
    private Long userId;
    private String username;
    private String userPhoto;
    private String content;
    private Timestamp datePublished;
    private Integer likes;
    private Integer disLikes;
}
