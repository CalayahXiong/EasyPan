package com.easypan.service;

import com.easypan.entity.pojo.UserInfo;
import com.easypan.entity.query.UserInfoExample;
import com.easypan.entity.vo.PaginationResultVO;

import java.io.IOException;
import java.util.List;

public interface UserInfoService {

    List<UserInfo> findListByParam(UserInfoExample query);

    Integer findCountByParam(UserInfoExample query);

    PaginationResultVO<UserInfo> findListByPage(UserInfoExample query) throws IOException;
}
