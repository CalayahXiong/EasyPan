package com.easypan.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SystemSettingDto implements Serializable {
    private String registerMailTitle = "邮箱验证码";

    private String registerEmailContent = "邮箱验证码是：%s, 5分钟内有效";
    //验证码过期时间
    private Integer RegisteryMailExpireTime = 5;

    private Integer userInitUserSpace = 5;

    public int getRegistryMailExpireTime() {
        return RegisteryMailExpireTime;
    }

    public String getRegisterMailTitle() {
        return registerMailTitle;
    }

    public String getReisterEmailContent() {
        return registerEmailContent;
    }
}
