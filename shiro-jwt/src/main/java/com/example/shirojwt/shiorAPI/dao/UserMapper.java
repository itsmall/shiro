package com.example.shirojwt.shiorAPI.dao;

import com.example.shirojwt.shiorAPI.bean.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface UserMapper {

   /* @Select("SELECT *" +
            "FROM t_user u JOIN t_user_role ur ON(u.id = ur.uid) JOIN t_role r ON (ur.rid=r.id)" +
            "JOIN t_role_permission rp ON(rp.rid = r.id) JOIN t_permission p ON(p.id = rp.pid ) WHERE u.user_name =#{name}")
*/
   public User queryUserByName(String name);
}
