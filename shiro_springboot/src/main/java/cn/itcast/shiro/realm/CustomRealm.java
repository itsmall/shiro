package cn.itcast.shiro.realm;

import cn.itcast.shiro.domain.Permission;
import cn.itcast.shiro.domain.Role;
import cn.itcast.shiro.domain.User;
import cn.itcast.shiro.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

/**
 * 自定义的realm
 */
public class CustomRealm extends AuthorizingRealm {

    public void setName(String name) {
        super.setName("customRealm");
    }

    @Autowired
    private UserService userService;

    /**
     * 授权方法
     * 操作的时候，判断用户是否具有响应的权限
     * 先认证 -- 安全数据
     * 再授权 -- 根据安全数据获取用户具有的所有操作权限
     */
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //1.获取已认证的用户数据
        User user = (User) principalCollection.getPrimaryPrincipal();//得到唯一的安全数据

        //2.根据用户数据获取用户的权限信息（所有角色，所有权限）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<String> roles = new HashSet<>();//所有角色
        Set<String> perms = new HashSet<>();//所有权限
        //todo 数据库查询当前用的权限
        for (Role role : user.getRoles()) {
            roles.add(role.getName());
            for (Permission perm : role.getPermissions()) {
                perms.add(perm.getName());
            }
        }
        info.setStringPermissions(perms);
        info.setRoles(roles);
        return info;
    }

    /**
     * 认证方法
     * 参数：传递的用户名密码
     */
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //1.获取登录的用户名密码（token）
        UsernamePasswordToken upToken = (UsernamePasswordToken) authenticationToken;
        String username = upToken.getUsername();
        //2.根据用户名查询数据库
        User user = userService.findByName(username);
        //判断用户是否存在
        if (user == null) { //没有这个人
            return null;
        }

/*
        if (user.getStatus() == 1) { //账户冻结
            throw new LockedAccountException();
        }
        */

        //判断密码是否一致
        return new SimpleAuthenticationInfo(user, user.getPassword(), this.getName());
    }

    public static void main(String[] args) {
        System.out.println(new Md5Hash("123456", "wangwu", 3).toString());
    }
}
