package com.moon.studentplatform.mapper;

import com.moon.studentplatform.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class UserTest extends BasicTest {
    @Autowired
    UserMapper userMapper;

    @Test
    public void getUser(){
        System.out.println(userMapper.findByUsername("ss"));
    }

    @Test
    public void showAll(){
        System.out.println(userMapper.getAllUser());
    }
}
