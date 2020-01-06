package com.example.shirojwt.shiorAPI.dao;

import com.example.shirojwt.shiorAPI.bean.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserPermissionDao extends JpaRepository<Permission, Long> {

    @Query(nativeQuery = true, value = "SELECT p.* FROM t_role r JOIN t_user_role ur ON(r.id = ur.rid) JOIN t_user u ON(u.id = ur.uid) " +
            "JOIN t_role_permission rp ON(rp.rid = r.id) JOIN t_permission p ON(p.id = rp.pid ) WHERE u.user_name =?1")
    List<Permission> findByUserName(String userName);
}
