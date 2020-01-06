package cn.itcast.shiro.controller;

import cn.itcast.shiro.domain.User;
import cn.itcast.shiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.authz.annotation.RequiresUser;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //个人主页
    //使用shiro注解鉴权
    //@RequiresPermissions()  -- 访问此方法必须具备的权限
    //@RequiresRoles() -- 访问此方法必须具备的角色

    /**
     * 1.过滤器：如果权限信息不匹配setUnauthorizedUrl地址
     * 2.注解：如果权限信息不匹配，抛出异常
     */
    @RequiresPermissions("user-home")
    @GetMapping(value = "/user/home")
    public String home() {
        return "访问个人主页成功";
    }

    //添加
    @RequiresRoles("admin")
    @PostMapping("/user")
    public String add(User user) {

        return "添加用户成功";
    }

    //查询
    @RequiresUser
    @GetMapping(value = "/user")
    public String find() {
        System.out.println("查询用户成功");
        return "查询用户成功";
    }

    @GetMapping(value = "/getUser")
    public String get() {
        System.out.println("查询用户成功");
        return "查询用户成功";
    }

    //更新
    @PutMapping(value = "/user/{id}")
    public String update(String id) {
        System.out.println(id);
        System.out.println("更新用户成功");
        return "更新用户成功";
    }

    //删除
    @DeleteMapping(value = "/user/{id}")
    public String delete() {
        System.out.println("删除用户成功");
        return "删除用户成功";
    }

    /**
     * 1.传统登录
     * 前端发送登录请求 => 接口部分获取用户名密码 => 程序员在接口部分手动控制
     * 2.shiro登录
     * 前端发送登录请求 => 接口部分获取用户名密码 => 通过subject.login =>  realm域的认证方法
     */
    //用户登录
    @PostMapping(value = "/login")
    public String login(String username, String password) {
        /**
         * 密码加密：
         *     shiro提供的md5加密
         *     Md5Hash:
         *      参数一：加密的内容
         *              111111   --- abcd
         *      参数二：盐（加密的混淆字符串）（用户登录的用户名）
         *              111111+混淆字符串
         *      参数三：加密次数
         *
         */
        //密码加密
        password = new Md5Hash(password, username, 3).toString();
//        System.out.println(password);
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();

        // 如果用户已经登录
        if (subject.isAuthenticated()) {
            return "已登陆";
        }

        //可以设置记住我    token.setRememberMe(true);
        try {
            //登录
            subject.login(token);
            String sid = subject.getSession().getId().toString();
//            new Result();
            return "登录成功\t" + sid;
        } catch (UnknownAccountException e) {
            return "用户名错误";
        } catch (IncorrectCredentialsException e) {
            return "密码错误";
        }

    }

    /**
     * 权限不足，shiro应重定向到403界面，此处返回权限不足信息，由前端控制跳转页面
     */
//    @ResponseBody
//    @RequestMapping(value = "/unauthorized")
//    public Result unauthorized(){
//        return new Result().success(false).error(403).msg(propertiesUtil.getValue("权限不足"));
//    }
    @GetMapping(value = "/autherror")
    public String autherror(int code) {
        return code == 1 ? "未登录" : "未授权";
    }

    @PostMapping("/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

    }

    @RequiresUser
    @GetMapping("show")
    public String show(HttpSession session) {
//获取session中所有的键值
        Enumeration<String> enumeration = session.getAttributeNames();
        while (enumeration.hasMoreElements()) {
            //获取session的键值
            String name = enumeration.nextElement().toString();
            //根据键值取session中的值
            Object value = session.getAttribute(name);
            System.out.println("键：" + name + "\t值：" + value);
        }
        return "查看session成功";
    }
}
