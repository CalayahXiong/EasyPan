package com.easypan.entity.vo;

/**
 * @Description 网页回应
 * @status 200OK 404NOT FOUND
 * @code 1xx信息响应 2xx成功响应 3xx重定位 4xx客户端响应 5xx服务器错误响应
 * @info 响应的状态信息，用于描述状态码的含义
 * @data 网页返回的内容数据
 * @param <T>
 */
public class ResponseVO<T> {
    private String status;
    private Integer code;
    private String info;
    private T data;

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
