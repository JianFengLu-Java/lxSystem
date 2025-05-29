package com.learnings.markup.markuplive.dto;

import com.learnings.markup.markuplive.entity.User;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;


public class RegisterRequest {

    private Long account;//用户账号【唯一】

    private String trueName;//用户真实姓名

    private String avatarUrl;//用户头像地址

    private String password;//用户设置的密码【BC加密】

    private List<String> type;//用户类型【管理员、普通用户】

    private String email;//用户邮箱

    private Long phone;//用户手机号

    private String birthday;//用户生日

    private String address;//用户地址

    private String gender;//用户性别

    private String nickname;//用户昵称

    private Long createTime;//用户创建时间

    private Long userLevel;//用户等级

    public User toUser(Long account, BCryptPasswordEncoder bCryptPasswordEncoder) {

        User user = new User();
        user.setTrueName(this.trueName);
        user.setAvatarUrl(this.avatarUrl);
        user.setPassword(bCryptPasswordEncoder.encode(this.password));
        user.setType(this.type);
        user.setEmail(this.email);
        user.setPhone(this.phone);
        user.setBirthday(this.birthday);
        user.setAddress(this.address);
        user.setGender(this.gender);
        user.setAccount(account);
        return user;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }

    public String getTrueName() {
        return trueName;
    }

    public void setTrueName(String trueName) {
        this.trueName = trueName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getType() {
        return type;
    }

    public void setType(List<String> type) {
        this.type = type;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUserLevel() {
        return userLevel;
    }

    public void setUserLevel(Long userLevel) {
        this.userLevel = userLevel;
    }
}
