package cn.scau.hjr.model;

import java.util.Date;
import java.util.Set;

public class User {
    private Integer userId;

    private String  account;

    private String password;

    private String username;

    private Date registerTime;

    private String sex;

    private Integer age;

    private String  phone;

    public Set<String> getHasRole() {
        return hasRole;
    }

    public void setHasRole(Set<String> hasRole) {
        this.hasRole = hasRole;
    }

    public Set<String> getLacksRole() {
        return lacksRole;
    }

    public void setLacksRole(Set<String> lacksRole) {
        this.lacksRole = lacksRole;
    }

    private Set<String> hasRole;
    private Set<String> lacksRole;
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String  getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}