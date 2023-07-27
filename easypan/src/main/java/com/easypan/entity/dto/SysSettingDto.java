package com.easypan.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

public class SysSettingDto {
    @JsonIgnoreProperties(ignoreUnknown = true)
    @Data
    public class SysSettingsDto implements Serializable {

        private String registryMailTitle = "邮箱注册验证码";

        private String registryMailContent = "您的验证码为：%s，请于%s分钟内输入，如非本人操作，请忽略此邮件。";
        //注册验证码过期时间
        private Integer RegistryMailExpireTime = 15;
        //初始空间大小
        private Integer userInitUserSpace = 5;
    }
}
