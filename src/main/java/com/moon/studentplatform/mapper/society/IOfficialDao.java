package com.moon.studentplatform.mapper.society;

import com.moon.studentplatform.dto.official.DeptType;
import com.moon.studentplatform.dto.official.Lecture;
import com.moon.studentplatform.dto.official.LectureSeach;
import com.moon.studentplatform.dto.society.ClubActivity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/21 21:56
 * @description：这个类是
 * @modified By：
 */
@Repository
@Mapper
public interface IOfficialDao {

    @Insert("INSERT into `lecture_type`(title,type_id,context,dateStart,dateEnd)\n" +
            "VALUES(#{title},#{type},#{context},#{dateStart},#{dateEnd})")
    int addLecture(Lecture lecture);

    @Delete("delete from lecture_type where id=#{id}")
    int deleteLectureById(@Param("id") String id);

    @Select("SELECT\n" +
            "lecture_type.id as id,"+
            "type.`name` as type,\n" +
            "lecture_type.title,\n" +
            "lecture_type.dateStart,\n" +
            "lecture_type.dateEnd,\n" +
            "lecture_type.context\n" +
            "FROM\n" +
            "lecture_type\n" +
            "INNER JOIN type ON lecture_type.type_id = type.id\n" +
            "ORDER BY lecture_type.dateStart DESC\n" +
            "LIMIT #{offset},#{limit}")
    List<Lecture> getLimitLectures(@Param("offset") int offset, @Param("limit") int limit);


    @Select("select * from type")
    List<DeptType> getAllType();

    @Select({"<script>",
            "SELECT\n" +
                    "lecture_type.id,\n" +
                    "lecture_type.title,\n" +
                    "lecture_type.dateStart,\n" +
                    "lecture_type.dateEnd,\n" +
                    "lecture_type.context\n" +
                    "FROM\n" +
                    "lecture_type\n" +
                    "INNER JOIN type ON lecture_type.type_id = type.id",
            "WHERE 1=1",
            "<when test=\"typeId!=null\">",
            "AND lecture_type.type_id = #{typeId}",
            "</when>",
            "<when test=\"keyWord!=null\">",
            "AND lecture_type.title LIKE CONCAT('%',#{keyWord},'%')",
            "</when>",
            "ORDER BY lecture_type.dateStart ASC",
            "LIMIT #{offset},#{limit}",
            "</script>"})
    List<Lecture> shearchLectures(LectureSeach seach);

    @Select("select count(*) from lecture_type where title LIKE CONCAT('%',#{keyWord},'%') and type_id=#{typeId}")
    int getSearchCount(LectureSeach seach);

    @Select("select count(*) from lecture_type")
    int getAllLectureCount();

    /*展示一切动态*/
    @Select("SELECT\n" +
            "club_activity.id,\n" +
            "club_activity.userId,\n" +
            "club_activity.clubId,\n" +
            "club_activity.title,\n" +
            "club_activity.image,\n" +
            "club.`name` as author,\n" +
            "club_activity.date,\n" +
            "club_activity.text,\n" +
            "club_activity.description,\n" +
            "club_activity.pass\n" +
            "FROM\n" +
            "club_activity\n" +
            "INNER JOIN club ON club_activity.clubId = club.id\n" +
            "WHERE\n" +
            "club_activity.pass='true'\n" +
            "ORDER BY club_activity.date DESC")
    List<ClubActivity> showAllInfoDynas();

    @Select("SELECT\n" +
            "club_activity.id,\n" +
            "club_activity.userId,\n" +
            "club_activity.clubId,\n" +
            "club_activity.title,\n" +
            "club_activity.image,\n" +
            "club.`name` as author,\n" +
            "club_activity.date,\n" +
            "club_activity.text,\n" +
            "club_activity.description,\n" +
            "club_activity.pass\n" +
            "FROM\n" +
            "club_activity\n" +
            "INNER JOIN club ON club_activity.clubId = club.id\n" +
            "WHERE\n" +
            "club.count = #{type}\n" +
            "and club_activity.pass='true'\n" +
            "ORDER BY club_activity.date DESC")
    List<ClubActivity> showAllContests(@Param("type") int type);
}
