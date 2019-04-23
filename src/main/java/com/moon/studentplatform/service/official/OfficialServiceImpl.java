package com.moon.studentplatform.service.official;

import com.moon.studentplatform.dto.official.DeptType;
import com.moon.studentplatform.dto.official.Lecture;
import com.moon.studentplatform.dto.official.LectureSeach;
import com.moon.studentplatform.dto.society.ClubActivity;
import com.moon.studentplatform.mapper.society.IOfficialDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/21 22:08
 * @description：这个类是
 * @modified By：
 */
@Service("officialService")
public class OfficialServiceImpl implements IOfficialService {

    @Autowired
    IOfficialDao officialDao;

    @Override
    public List<Lecture> getLimitLectures(int offset, int limit) {
        return officialDao.getLimitLectures(offset, limit);
    }

    @Override
    public List<Lecture> shearchLectures(LectureSeach seach) {
        return officialDao.shearchLectures(seach);
    }

    @Override
    public List<DeptType> getAllType() {
        return officialDao.getAllType();
    }

    @Override
    public int getSearchCount(LectureSeach seach) {
        return officialDao.getSearchCount(seach);
    }

    @Override
    public int getAllLectureCount() {
        return officialDao.getAllLectureCount();
    }

    @Override
    public List<ClubActivity> showAllContests(int type) {
        return officialDao.showAllContests(type);
    }

    @Override
    public List<ClubActivity> showAllInfoDynas() {
        return officialDao.showAllInfoDynas();
    }
}
