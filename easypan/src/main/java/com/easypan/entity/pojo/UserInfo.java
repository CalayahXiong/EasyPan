package com.easypan.entity.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

public class UserInfo implements Serializable {

    private String userId;

    private String nickName;

    private String email;

    private String qqOpenId;

    private String qqAvatar;

    private String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date joinTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastJoinTime;

    private Integer status;

    private Long totalSpace;

    private Long useSpace;

    public String getUserId() {
        return userId;
    }

    public String getNick_name() {
        return nickName;
    }

    public void setLastJoinTime(Date lastJoinTime) {
        this.lastJoinTime = lastJoinTime;
    }

    public void setUseSpace(Long useSpace) {
        this.useSpace = useSpace;
    }

    public String getEmail() {
        return email;
    }

    public String getQq_open_id() {
        return qqOpenId;
    }

    public String getQq_avatar() {
        return qqAvatar;
    }

    public Date getJoin_time() {
        return joinTime;
    }

    public Date getLast_join_time() {
        return lastJoinTime;
    }

    public Long getUse_space() {
        return useSpace;
    }

    public Long getTotal_space() {
        return totalSpace;
    }

    public void setUserId(String userId){
        this.userId=userId;
    }
    /**
     * 提供用户昵称的set方法
     */
    public void setNickName(String nickName){
        this.nickName = nickName;
    }
    /**
     * 提供用户邮箱的set方法
     */
    public void setEmail(String email){
        this.email = email;
    }
    /**
     * 提供用户关联QQ开放ID的set方法
     */
    public void setQqOpenId(String qqOpenId){
        this.qqOpenId = qqOpenId;
    }
    /**
     * 提供用户头像的set方法
     */
    public void setQqAvatar(String qqAvatar){
        this.qqAvatar = qqAvatar;
    }
    /**
     * 提供用户密码的set方法
     */
    public void setPassword(String password){
        this.password = password;
    }
    /**
     * 提供用户创建时间的set方法
     */
    public void setJoinTime(Date joinTime){
        this.joinTime = joinTime;
    }
    /**
     * 提供用户最后一次登录时间的set方法
     */
    public void setLastLoginTime(Date lastLoginTime){
        this.lastJoinTime = lastLoginTime;
    }
    /**
     * 提供用户状态:0:禁用 1:启用的set方法
     */
    public void setStatus(Integer status){
        this.status=status;
    }
    /**
     * 提供用户使用空间的set方法
     */
    public void setUserSpace(Long userSpace){
        this.useSpace = userSpace;
    }
    /**
     * 提供用户总空间的set方法
     */
    public void setTotalSpace(Long totalSpace){
        this.totalSpace = totalSpace;
    }
}
