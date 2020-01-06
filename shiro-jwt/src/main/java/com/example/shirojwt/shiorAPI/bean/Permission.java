package com.example.shirojwt.shiorAPI.bean;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 权限
 */
@Entity
@Table(name = "t_permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = -904615596818836291L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String url; //url地址
    private String name;  //url描述

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Permission{" +
                "id=" + id +
                ", url='" + url + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
