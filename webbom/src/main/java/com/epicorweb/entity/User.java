package com.epicorweb.entity;



import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Administrator on 2016-09-21.
 */
@Entity
@Table(name = "tb_user")
public class User implements java.io.Serializable {
    long id;
    String username;
    String password;
    String email;
    String nickname;
    Date createDate;
    Integer sex;

    public User() {
    }

    public User(Integer sex, long id, String username, String password, String email, String nickname, Date createDate) {

        this.sex = sex;
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickname = nickname;
        this.createDate = createDate;
    }
    @Id
    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @NotBlank(message = "用户名不能为空")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotNull(message = "密码不能为null")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Email(message = "邮箱格式不正确")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }
}
