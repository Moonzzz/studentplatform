package com.moon.studentplatform.mapper.society;

import com.moon.studentplatform.dto.society.Club;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ：Mr Tang
 * @date ：Created in 2019/4/16 15:43
 * @description：这个类是社区会员dao层接口
 * @modified By：
 */
@Repository
@Mapper
public interface IUserClubDao {
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
            "WHERE club.count=2\n"+
            "ORDER BY\n" +
            "club_user.pass ASC")
    List<Club> showAllClubUsers();
}
