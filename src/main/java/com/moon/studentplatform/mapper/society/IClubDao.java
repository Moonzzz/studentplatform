package com.moon.studentplatform.mapper.society;

import com.moon.studentplatform.dto.society.Club;
import com.moon.studentplatform.dto.society.ClubActivity;
import com.moon.studentplatform.dto.society.ClubUser;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/6 18:31
 * @description：这个类是社团社区接口dao文件
 * @modified By：
 */
@Repository
@Mapper
public interface IClubDao {
    @Select("SELECT * from club where pass ='true'")
    List<Club> showAllClubs();

    @Delete("delete from club where id=#{id}")
    int deleteClubById(@Param("id") String id);

    @Update("update club set name=#{name},description=#{description},datepublished=#{datepublished},\n" +
            "firstman=#{firstman},phonum=#{phonum}\n" +
            " where id=#{id}")
    boolean modifyClub(Club club);

    @Select("SELECT * from club where count=1 and pass='true'")
    List<Club> showAllStuOrganizes();

    @Select("select * from club where count=#{count} LIMIT #{offset},#{limit}")
    List<Club> getLimitClubs(@Param("offset") int offset, @Param("limit") int limit, @Param("count") int count);

    @Select("select count(*) from club where count=#{count}")
    int getClubCount(@Param("count") int count);

    @Select("select * from club where count <> 1 and pass='true'")
    List<Club> showAllColleges();

    @Select("SELECT * from club where id=#{id}")
    Club showClubDetailById(@Param("id") int id);

    @Insert("INSERT into `club`(name,description,firstman,datepublished,icon,phonum)\n" +
            "VALUES(#{name},#{description},#{firstman},#{datepublished},#{icon},#{phonum})")
    boolean addClub(Club club);


    @Insert("INSERT into `club_user`(user_id,club_id,reason,experience,joindate,pass)\n" +
            "VALUES(#{user_id},#{club_id},#{reason},#{experience},#{joindate},#{pass})")
    boolean joinClub(ClubUser member);

    @Select("select club_activity.id as id,club_activity.clubId as clubId,title,image,author\n" +
            ",date,text,club_activity.description as description from club,club_activity \n" +
            "where club_activity.clubId=#{clubId} and club.id=club_activity.clubId and club_activity.pass='true'")
    List<ClubActivity> showClubActsByCId(@Param("clubId") int clubId);

    @Select("select * from club_activity where id=#{id}")
    ClubActivity showClubActDetailById(@Param("id") int id);

    @Update("update club set pass=#{pass}\n" +
            " where id=#{id}")
    boolean setIsClubPass(@Param("id") String id, @Param("pass") String pass);

}
