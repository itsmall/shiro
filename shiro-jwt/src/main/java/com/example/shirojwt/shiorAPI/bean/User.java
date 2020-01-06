package com.example.shirojwt.shiorAPI.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户
 */
/*@Entity
@Table(name = "t_user")*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = -1256342057960878856L;

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String userName; //用户名称
    private String password; //密码
    //    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;//创建时间
    private boolean status;  // '是否有效 1：有效  0：锁定' 0:false 1:true
    private List<String> perms;
    private List<String> roles;

}
