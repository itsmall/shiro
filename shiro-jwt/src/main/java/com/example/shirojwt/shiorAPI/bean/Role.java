package com.example.shirojwt.shiorAPI.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 角色
 */
@Entity
@Table(name = "t_role")
public class Role implements Serializable {
    private static final long serialVersionUID = 343192691432380079L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name; //角色名称
    private String memo; //角色描述

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", memo='" + memo + '\'' +
                '}';
    }
}
