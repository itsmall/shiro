package com.example.shirojwt.shiorAPI.dao;

import com.example.shirojwt.shiorAPI.bean.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRoleDao extends JpaRepository<Role, Long> {

    @Query(nativeQuery = true, value = "SELECT r.* FROM t_role r JOIN t_user_role ur ON(r.id = ur.rid) JOIN t_user u ON(u.id = ur.uid) WHERE u.user_name = ?1 ")
    List<Role> findByUserName(String userName);

}
