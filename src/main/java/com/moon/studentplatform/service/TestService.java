package com.moon.studentplatform.service;

import com.moon.studentplatform.mapper.TestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Moon
 */
@Service
public class TestService {
    @Autowired
    TestMapper testMapper;
}
