package com.example.shirojwt.shiorAPI.service;

import com.example.shirojwt.shiorAPI.bean.Permission;
import com.example.shirojwt.shiorAPI.dao.UserPermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {
    @Autowired
    private UserPermissionDao userPermissionDao;


    public List<Permission> findByUserName(String userName) {
        return this.userPermissionDao.findByUserName(userName);
    }

}
