package com.moon.studentplatform.mapper.society;

import com.moon.studentplatform.dto.society.ClubActivity;
import com.moon.studentplatform.dto.society.ClubUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/16 16:05
 * @description：这个类是
 * @modified By：
 */
@Repository
@Mapper
public interface IClubUserDao {
    @Select("SELECT\n" +
            "club_user.id,\n" +
            "club.`name` as clubname,\n" +
            "`user`.username,\n" +
            "club_user.reason,\n" +
            "club_user.experience,\n" +
            "club_user.joindate,\n" +
            "club_user.pass\n" +
            "FROM\n" +
            "club\n" +
            "INNER JOIN club_user ON club_user.club_id = club.id AND '' = ''\n" +
            "INNER JOIN `user` ON club_user.user_id = `user`.id\n" +
            "WHERE club.count=#{count}\n" +
            "ORDER BY\n" +
            "club_user.pass ASC\n" +
            "LIMIT #{offset},#{limit}")
    List<ClubUser> showAllClubUsers(@Param("offset") int offset, @Param("limit") int limit, @Param("count") int count);

    @Select("SELECT\n" +
            "count(*)\n" +
            "FROM\n" +
            "club\n" +
            "INNER JOIN club_user ON club_user.club_id = club.id\n" +
            "INNER JOIN `user` ON club_user.user_id = `user`.id\n" +
            "where club.count=#{type}")
    int getClubUserCount(@Param("type") int type);

    @Update("update club_user set pass=#{pass}\n" +
            " where id=#{id}")
    boolean setIsPass(@Param("id") String id, @Param("pass") String pass);


    @Update("update club_activity set pass=#{pass}\n" +
            " where id=#{id}")
    boolean setArticleIsPass(@Param("id") String id, @Param("pass") String pass);


    @Delete("delete from club_activity where id=#{id}")
    int deleteArticleById(@Param("id") String id);

    @Delete("delete from club_user where id=#{id}")
    int deleteClubUserById(@Param("id") String id);


    @Insert("INSERT into `club_activity`\n" +
            "(userId,clubId,title,image,author,date,text,description,pass)\n" +
            "VALUES(#{userId},#{clubId},#{title},#{image},#{author}\n" +
            ",#{date},#{text},#{description},#{pass})")
    boolean editArticle(ClubActivity activity);


    @Select("select count(*) from club_user WHERE club_id=#{club_id} and user_id=#{user_id} and pass='true'")
    int isUserPass(@Param("club_id") int club_id, @Param("user_id") int user_id);

    @Select("select count(*) from club_user WHERE club_id=#{club_id} and user_id=#{user_id}")
    int isUserApply(@Param("club_id") int club_id, @Param("user_id") int user_id);

    @Select("select * from club_activity ORDER BY pass ASC LIMIT #{offset},#{limit}")
    List<ClubActivity> getLimitArticles(@Param("offset") int offset, @Param("limit") int limit);

    @Select("select count(*) from club_activity")
    int getArticlesCount();
}
