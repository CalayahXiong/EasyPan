package com.easypan.mappers;

import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @param <T> 插入
 * @param <P> 查询
 */
public interface BaseMapper<T, P> {

    Integer insert(@Param("bean") T t);

    Integer insertOrUpdate(@Param("bean") T t);

    Integer insertBatch(@Param("list") List<T> list);

    Integer insertOrUpdateBatch(@Param("list") List<T> list);

    List<T> selectList(@Param("query") P p);

    Integer selectCount(@Param("query") P p);

}
