package com.example.shirojwt.shiorAPI.conf;

import com.example.shirojwt.shiorAPI.bean.Permission;
import com.example.shirojwt.shiorAPI.bean.User;
import com.example.shirojwt.shiorAPI.service.PermissionService;
import com.example.shirojwt.shiorAPI.service.RoleService;
import com.example.shirojwt.shiorAPI.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PermissionRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    /**
     * 自定义realm名称
     *
     * @param name
     */
    public void setName(String name) {
        super.setName("permissionRealm");
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行=>授权功能--------------------------");

        //拿到当前对象
//        Subject subject = SecurityUtils.getSubject();
//        User currentUser = (User) subject.getPrincipal();
        User currentUser = (User) principalCollection.getPrimaryPrincipal();

        System.out.println(currentUser);

        List<Permission> permissions = permissionService.findByUserName(currentUser.getUserName());
        List<String> perms = new ArrayList<>();
        for (Permission permission : permissions) {
            perms.add(permission.getName());
        }

        //设置当前用户的权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(perms);

        roleService.findByUserName(currentUser.getUserName());
        //info.addRole();

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        UsernamePasswordToken userToken = (UsernamePasswordToken) token;
        //连接真实数据库
        User user = userService.queryUserByName(userToken.getUsername());
        if (user == null) { //没有这个人
            return null;
        }

//        Subject subject = SecurityUtils.getSubject();
//        subject.getSession().setAttribute("loginUser", user);
        //todo 密码加密

        //密码认证
        return new SimpleAuthenticationInfo(user, user.getPassword(), getName());
    }
}
