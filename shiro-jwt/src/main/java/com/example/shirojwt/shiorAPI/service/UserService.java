package com.example.shirojwt.shiorAPI.service;

import com.example.shirojwt.shiorAPI.bean.User;
import com.example.shirojwt.shiorAPI.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User queryUserByName(String name) {
        return userMapper.queryUserByName(name);
    }
}
