package com.moon.studentplatform.service.impl;

import com.moon.studentplatform.dto.User;
import com.moon.studentplatform.mapper.UserMapper;
import com.moon.studentplatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Moon
 */
@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public User findUserByName(String name) {
        return userMapper.findByUsername(name);
    }
}
