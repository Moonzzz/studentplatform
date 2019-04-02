package com.moon.studentplatform.dto;

import lombok.Data;

import java.util.Set;

@Data
public class Role {

    private Integer id;

    private String name;

    private Set<Permission> permissionSet;

}
