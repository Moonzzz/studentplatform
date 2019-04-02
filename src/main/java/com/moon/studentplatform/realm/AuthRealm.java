package com.moon.studentplatform.realm;

import com.moon.studentplatform.dto.Permission;
import com.moon.studentplatform.dto.Role;
import com.moon.studentplatform.dto.User;
import com.moon.studentplatform.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 从session中拿出用户对象
        User user = (User) principalCollection.fromRealm(this.getClass().getName()).iterator().next();
        List<String> permissionList = new ArrayList<>();
        Set<String> roleNameSet = new HashSet<>();

        // 获取用户的角色集
        Set<Role> roleSet = user.getRoleSet();
        if (!CollectionUtils.isEmpty(roleSet)) {
            for (Role role : roleSet) {
                // 添加角色名称
                roleNameSet.add(role.getName());

                // 获取角色的权限集
                Set<Permission> permissionSet = role.getPermissionSet();
                if (!CollectionUtils.isEmpty(permissionSet)) {
                    for (Permission permission : permissionSet) {
                        // 添加权限名称
                        permissionList.add(permission.getName());
                    }
                }
            }
        }

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        info.setRoles(roleNameSet);

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        // 获取登录的用户名
        String userName = usernamePasswordToken.getUsername();
        // 从数据库中查询用户
        User user = userService.findUserByName(userName);

        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getClass().getName());
    }
}
