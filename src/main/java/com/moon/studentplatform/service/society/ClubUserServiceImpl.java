package com.moon.studentplatform.service.society;

import com.moon.studentplatform.dto.society.ClubUser;
import com.moon.studentplatform.mapper.society.IClubUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/16 16:02
 * @description：这个类是
 * @modified By：
 */
@Service("ClubUserService")
public class ClubUserServiceImpl implements IClubUserService {
    @Autowired
    IClubUserDao clubUserDao;

    @Override
    public List<ClubUser> showAllClubUsers(int offset, int limit, int type) {
        return clubUserDao.showAllClubUsers(offset, limit, type);
    }

    @Override
    public int getClubUserCount(int type) {
        return clubUserDao.getClubUserCount(type);
    }

    @Override
    public boolean setIsPass(String id, String pass) {
        return clubUserDao.setIsPass(id, pass);
    }

    @Override
    public int deleteClubUserById(String id) {
        return clubUserDao.deleteClubUserById(id);
    }
}
