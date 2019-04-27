package com.moon.studentplatform.service.official;

import com.moon.studentplatform.dto.official.DeptType;
import com.moon.studentplatform.dto.official.Lecture;
import com.moon.studentplatform.dto.official.LectureSeach;
import com.moon.studentplatform.dto.society.ClubActivity;
import com.moon.studentplatform.dto.society.ReplyManager;

import java.util.List;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/21 22:08
 * @description：这个类是
 * @modified By：
 */
public interface IOfficialService {
    int getReplysCount(String table);

    int deleteReplyByID(int id,String table);

    List<ReplyManager> getLimitReplys(int offset, int limit,String table);

    boolean modifyReplyContentById(int id,String content,String table);

    int addLecture(Lecture lecture);

    int deleteLectureById(String id);

    List<Lecture> getLimitLectures(int offset, int limit);

    List<Lecture> shearchLectures(LectureSeach seach);

    List<DeptType> getAllType();

    int getSearchCount(LectureSeach seach);

    int getAllLectureCount();

    List<ClubActivity> showAllContests(int type);

    List<ClubActivity> showAllInfoDynas();
}
