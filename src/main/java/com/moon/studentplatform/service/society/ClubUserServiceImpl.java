package com.moon.studentplatform.service.society;

import com.moon.studentplatform.dao.society.IFileDao;
import com.moon.studentplatform.dto.society.ClubActivity;
import com.moon.studentplatform.dto.society.ClubUser;
import com.moon.studentplatform.mapper.society.IClubUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
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

    @Autowired
    IFileDao fileDao;

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

    @Override
    public boolean editArticle(MultipartFile picFile, ClubActivity activity) {
        long pSize = picFile.getSize();
        System.out.println("pic size:  " + pSize);
        int randomNum = fileDao.getSecondTimestamp(new Date());
        String pSuffix = fileDao.getSuffix(picFile.getOriginalFilename());
        String pRealPath = "D:/AIDEAworkspace/studentplatform/src/main/resources/static/society_files/activity_imgs/" + randomNum + pSuffix;
        String image = randomNum + pSuffix;
        System.out.println("image:  " + image);
        activity.setImage(image);

        if (clubUserDao.editArticle(activity)) {
            boolean flag = fileDao.upLoadPhoto(picFile, pRealPath);
            if (flag) return true;
        }
        return false;
    }
}
