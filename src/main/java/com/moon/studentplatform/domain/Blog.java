package com.moon.studentplatform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

/**
 * @author Moon
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blog {
    private Long userId;
    private Long postId;
    private String loginName;
    private String title;
    private String digest;
    private String content;
    private Integer commentNum;
    private Integer views;
    private Integer likes;
    private String images;
    private Timestamp dateLastModified;
    private Byte isLocked;
    private Set<Integer> categories;
    private List<Comment> comments;
}
