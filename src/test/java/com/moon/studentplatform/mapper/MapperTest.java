package com.moon.studentplatform.mapper;

import com.moon.studentplatform.BasicTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MapperTest extends BasicTest {

    @Autowired
    TestMapper testMapper;

    @Test
    public void SelectTest(){
        System.out.println(testMapper.getNames());
    }
}
