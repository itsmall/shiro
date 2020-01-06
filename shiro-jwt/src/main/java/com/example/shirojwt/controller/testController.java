package com.example.shirojwt.controller;

import com.example.shirojwt.log.annotation.Log;
import com.example.shirojwt.log.bean.SysLog;
import com.example.shirojwt.log.service.SysLogService;
import com.example.shirojwt.shiorAPI.bean.Permission;
import com.example.shirojwt.shiorAPI.bean.Role;
import com.example.shirojwt.shiorAPI.service.PermissionService;
import com.example.shirojwt.shiorAPI.service.RoleService;
import com.example.shirojwt.shiorAPI.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class testController {
    @Autowired
    private SysLogService sysLogService;
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Log("test操作")
    @GetMapping("/test")
    @ResponseBody
    public Map test(HttpServletRequest request, String name) {
        List<Permission> permissionList = permissionService.findByUserName(name);
        List<Role> roleList = roleService.findByUserName(name);
        //给操作者赋值 传入Attribute
        request.setAttribute("user", name);

        Map<Object, Object> map = new HashMap<>();

        map.put("Permission", permissionList);
        map.put("role", roleList);
        return map;
    }

    @GetMapping("getTest")
    @ResponseBody
    public List<SysLog> listLog() {
        return sysLogService.listLog();
    }

    @RequestMapping({"/", "/index"})
    public String toIndex(Model model) {
        model.addAttribute("msg", "hello,shiro");
        return "index";

    }

    @GetMapping("/user/add")
    public String add() {
        return "user/add";
    }

    @GetMapping("/user/update")
    public String update() {
        return "user/update";
    }

    @GetMapping("/toLogin")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/login")
    public String login(String username, String password, Model model) {
        //获取当前的用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        //可以设置记住我    token.setRememberMe(true);
        try {
            //登录
            subject.login(token);
            return "index";
        } catch (UnknownAccountException e) {
            model.addAttribute("msg", "用户名错误");
            return "login";
        } catch (IncorrectCredentialsException e) {
            model.addAttribute("msg", "密码错误");
            return "login";
        }

    }

    @GetMapping("/401")
    public String err401(Model model) {
        model.addAttribute("msg", "权限不足");
        return "401";
    }

}
