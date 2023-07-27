package com.easypan.controller;

import com.easypan.annotation.GlobalInterceptor;
import com.easypan.entity.constants.Constants;
import com.easypan.entity.dto.CreateImageCode;
import com.easypan.entity.vo.ResponseVO;
import com.easypan.enums.ResponseCodeEnum;
import com.easypan.exception.BusinessException;
import com.easypan.service.EmailCodeService;
import com.easypan.service.UserInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * user_info管理
 */
@RestController // controller + response body
//@RequestMapping("userInfo") //来自前端的user info 如果是api，则不用加requestMapping
public class AccountController extends BaseController{

    @Resource
    //与service层双向
    private UserInfoService userInfoService;
    @Resource
    private EmailCodeService emailCodeService;

    @RequestMapping("/checkCode") //useInfo/checkCode, 是这样的；前端控制拦截器请求/checkCode, servlet决定使用哪个handler处理
    //百度可得验证码
    //验证码获取接口
    public void checkCode(HttpServletResponse response, HttpSession session, Integer type) {
        CreateImageCode vCode = new com.easypan.entity.dto.CreateImageCode(130, 38, 5, 10);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        String code = vCode.getCode();
        if (type == null || type == 0) {
            session.setAttribute(Constants.CHECK_CODE_KEY, code);
        } else {
            session.setAttribute(Constants.CHECK_CODE_KEY_EMAIL, code);
        }
        try {
            vCode.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //邮件发送接口
    @RequestMapping("/sendEmailCode")
    //aop实现参数校验
    @GlobalInterceptor() //之前定义的是全局的拦截器，所以在网页中操作的时候会自动进入GlobalInterceptor
    public ResponseVO sendEmailCode( String email, String checkCode, Integer type, HttpSession session) {
        try {
            if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY_EMAIL)))
                throw new BusinessException("图片验证码错误");
            emailCodeService.sendEmailCode(email, type);
            return getSuccessResponseVO(null);
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY_EMAIL);
        }
    }

    //注册接口
    @RequestMapping("/registry")
    @GlobalInterceptor
    public ResponseVO registry(HttpSession session, String email, String checkCode, int type) {
        try {
            if (checkCode == null) {
                return getSuccessResponseVO("get verify code first");
            }
            if (!checkCode.equalsIgnoreCase((String) session.getAttribute(Constants.CHECK_CODE_KEY))) {
                throw new BusinessException(ResponseCodeEnum.CODE_905);
            }
            emailCodeService.sendEmailCode(email, type);

            return getSuccessResponseVO("send successfully");
        } finally {
            session.removeAttribute(Constants.CHECK_CODE_KEY_EMAIL);
        }
    }
}
