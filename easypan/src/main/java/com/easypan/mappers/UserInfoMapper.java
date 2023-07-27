package com.easypan.mappers;

import org.apache.ibatis.annotations.Param;

public interface UserInfoMapper<T, P> extends BaseMapper {

    T selectByUserId(@Param("userId") String userId);

    Integer deleteByUserId(@Param("userId") String userId);

    Integer updateByUserId(@Param("bean") T t, @Param("userId") String userId);

    T selectByEmail(@Param("email") String email);

    Integer deleteByEmail(@Param("email") String email);

    Integer updateByEmail(@Param("bean") T t, @Param("email") String email);

    T selectByQqOpenId(@Param("qqOpenId") String qqOpenId);

    Integer deleteByQqOpenId(@Param("qqOpenId") String qqOpenId);

    Integer updateByQqOpenId(@Param("bean") T t, @Param("qqOpenId") String qqOpenId);

    T selectByNickName(@Param("nickName") String nickName);

    Integer deleteByNickName(@Param("nickName") String nickName);

    Integer updateByNickName(@Param("bean") T t, @Param("nickName") String nickName);
}
