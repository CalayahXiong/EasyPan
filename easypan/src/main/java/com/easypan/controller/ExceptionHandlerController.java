package com.easypan.controller;

import com.easypan.entity.vo.ResponseVO;
import com.easypan.enums.ResponseCodeEnum;
import com.easypan.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletRequest;
import java.net.BindException;

public class ExceptionHandlerController extends BaseController{
    //把ExceptionHandlerController.class表示这个类的元数据信息，是一个常量
    private static final Logger logger =LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(value = Exception.class)
    Object handleException(Exception e, HttpServletRequest request) {
        logger.error("请求错误，请求地址{}，错误信息：", request.getRequestURI(), e);
        ResponseVO ajaxResponse = new ResponseVO();
        //404
        if(e instanceof NoHandlerFoundException) {
            ajaxResponse.setCode(ResponseCodeEnum.CODE_404.getCode());
            ajaxResponse.setInfo(ResponseCodeEnum.CODE_404.getMsg());
            ajaxResponse.setStatus(STATUC_ERROR);
        } else if (e instanceof BusinessException) {
            //事务错误
            BusinessException biz = (BusinessException) e;
            ajaxResponse.setCode(biz.getCode());
            ajaxResponse.setInfo(biz.getMessage());
            ajaxResponse.setStatus(STATUC_ERROR);
        } else if (e instanceof DuplicateKeyException) {
            //主键冲突:当往表里插入数据时，本数据的主键已存在，破坏了主键uniqueness
            ajaxResponse.setCode(ResponseCodeEnum.CODE_601.getCode());
            ajaxResponse.setInfo(ResponseCodeEnum.CODE_600.getMsg());
            ajaxResponse.setStatus(STATUC_ERROR);
        } else if (e instanceof BindException) {
            ajaxResponse.setCode(ResponseCodeEnum.CODE_600.getCode());
            ajaxResponse.setInfo(ResponseCodeEnum.CODE_600.getMsg());
            ajaxResponse.setStatus(STATUC_ERROR);
        } else {
            ajaxResponse.setCode(ResponseCodeEnum.CODE_901.getCode());
            ajaxResponse.setInfo(ResponseCodeEnum.CODE_901.getMsg());
            ajaxResponse.setStatus(STATUC_ERROR);
        }
        return ajaxResponse;
    }
}
