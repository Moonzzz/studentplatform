package com.moon.studentplatform.domain;

import com.moon.studentplatform.dto.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 用户回复
 * @author Moon
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Reply extends Discuss{
    private Long replyId;
    private User replyToUser;
    private Long fatherId;
}
