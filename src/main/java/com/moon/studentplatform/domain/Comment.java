package com.moon.studentplatform.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * comment
 * @author Moon
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Comment extends Discuss{
    private Long commentId;
    private Long postId;
    private List<Reply> replies;
}
