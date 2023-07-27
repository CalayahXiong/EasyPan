package com.easypan.entity.query;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmailCodeQuery extends BaseQuery{

    private String email;

    private String emialAbstract;

    private String code;

    private String create_time;

    private Integer status;

    private String start_create_time;

    private String end_create_time;
}
