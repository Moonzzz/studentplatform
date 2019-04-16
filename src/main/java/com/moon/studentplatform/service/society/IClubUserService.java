package com.moon.studentplatform.service.society;

import com.moon.studentplatform.dto.society.ClubUser;

import java.util.List;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/16 16:00
 * @description：这个类是社区会员service接口
 * @modified By：
 */
public interface IClubUserService {
    List<ClubUser> showAllClubUsers(int offset, int limit, int type);
    int getClubUserCount(int type);
}
