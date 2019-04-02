package com.moon.studentplatform.mapper;

import com.moon.studentplatform.dto.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Moon
 */
@Repository
@Mapper
public interface UserMapper {
    /**
     * @param username
     * @return
     */
    User findByUsername(@Param("username") String username);

    List<User> getAllUser();
}
