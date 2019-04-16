package com.moon.studentplatform.mapper.society;

import com.moon.studentplatform.dto.society.ClubUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
            "WHERE club.count=#{count}\n"+
            "ORDER BY\n" +
            "club_user.pass ASC\n"+
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
}
