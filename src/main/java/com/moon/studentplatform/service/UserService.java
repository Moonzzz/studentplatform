package com.moon.studentplatform.service;

import com.moon.studentplatform.dto.User;

/**
 * @author Moon
 */
public interface UserService {
    /**
     * @param name name
     * @return user
     */
    User findUserByName(String name);
}
