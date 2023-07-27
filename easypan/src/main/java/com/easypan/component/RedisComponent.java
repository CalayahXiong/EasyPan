package com.easypan.component;


import com.easypan.entity.constants.Constants;
import com.easypan.entity.dto.SystemSettingDto;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("redisComponent")
public class RedisComponent {
    @Resource
    private RedisUtil redisUtil;

    public SystemSettingDto getSysSettingDto() {
        SystemSettingDto systemSettingDto = (SystemSettingDto)redisUtil.get(Constants.REDIS_KEY_SYS_SETTING);
        if(null == systemSettingDto) {
            systemSettingDto = new SystemSettingDto();
            redisUtil.set(Constants.REDIS_KEY_SYS_SETTING, systemSettingDto);
        }
        return systemSettingDto;
    }
}
