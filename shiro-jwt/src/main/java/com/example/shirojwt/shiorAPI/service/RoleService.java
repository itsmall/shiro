package com.example.shirojwt.shiorAPI.service;

import com.example.shirojwt.shiorAPI.bean.Role;
import com.example.shirojwt.shiorAPI.dao.UserRoleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private UserRoleDao userRoleDao;

    public List<Role> findByUserName(String userName) {
        return this.userRoleDao.findByUserName(userName);
    }

}
