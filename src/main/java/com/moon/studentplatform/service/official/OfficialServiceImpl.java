package com.moon.studentplatform.service.official;

import com.moon.studentplatform.dto.official.DeptType;
import com.moon.studentplatform.dto.official.Lecture;
import com.moon.studentplatform.dto.official.LectureSeach;
import com.moon.studentplatform.dto.society.ClubActivity;
import com.moon.studentplatform.dto.society.ReplyManager;
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

    private List<ReplyManager> replyManagers = null;

    @Override
    public int getReplysCount(String table) {
        int returnCount = 0;
        if (table.equalsIgnoreCase("reply")) {
            returnCount = officialDao.getReplysCount();
        } else if (table.equalsIgnoreCase("comment")) {
            returnCount = officialDao.getCommentsCount();
        }
        return returnCount;
    }

    @Override
    public int deleteReplyByID(int id, String table) {
        int returnCount = 0;
        if (table.equalsIgnoreCase("reply")) {
            returnCount = officialDao.deleteReplyByID(id);
        } else if (table.equalsIgnoreCase("comment")) {
            returnCount = officialDao.deleteCommentByID(id);
        }
        return returnCount;
    }

    @Override
    public List<ReplyManager> getLimitReplys(int offset, int limit, String table) {

        if (table.equalsIgnoreCase("reply")) {
            replyManagers = officialDao.getLimitReplys(offset, limit);
        } else if (table.equalsIgnoreCase("comment")) {
            replyManagers = officialDao.getLimitComments(offset, limit);
        }
        return replyManagers;
    }


    @Override
    public boolean modifyReplyContentById(int id, String content, String table) {
        boolean flag = false;
        if (table.equalsIgnoreCase("reply")) {
            flag = officialDao.modifyReplyContentById(id, content);
        } else if (table.equalsIgnoreCase("comment")) {
            flag = officialDao.modifyCommentContentById(id, content);
        }
        return flag;
    }

    @Override
    public int addLecture(Lecture lecture) {
        return officialDao.addLecture(lecture);
    }

    @Override
    public int deleteLectureById(String id) {
        return officialDao.deleteLectureById(id);
    }

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
