package com.moon.studentplatform.service;

import com.moon.studentplatform.dto.User;

public interface UserService {
    User findUserByName(String name);
}
