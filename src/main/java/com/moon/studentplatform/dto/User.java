package com.moon.studentplatform.dto;

import lombok.Data;

import java.util.Set;

@Data
public class User {

    private Long id;

    private String username;

    private String password;

    private String salt;

    private Set<Role> roleSet;

    private Integer isLocked;

    /**
     * 密码盐.
     *重新对盐重新进行了定义，用户名+salt，这样就更加不容易被破解
     * @return
     */
    public String getCredentialsSalt() {
        return this.username + this.salt;
    }
}