package com.easypan.service;

import com.easypan.entity.pojo.EmailCode;
import com.easypan.entity.query.EmailCodeQuery;
import com.easypan.entity.vo.PaginationResultVO;

import java.io.IOException;
import java.util.List;

public interface EmailCodeService {

    List<EmailCode> findListByParam(EmailCodeQuery param);

    Integer findCountByParm(EmailCodeQuery param);

    PaginationResultVO<EmailCode> findListByPage(EmailCodeQuery param) throws IOException;

    Integer add(EmailCode bean);

    Integer addBatch(List<EmailCode> listBean);

    Integer addOrUpdateBatch(List<EmailCode> listBean);

    EmailCode getEmailCodeByEmailAndCode(String email, String code);

    Integer deleteEmailCodeByEmailAndCode(String email, String code);

    Integer updateEmailCodeByEmailAndCode(EmailCode ben, String email, String code);

    void sendEmailCode(String email, Integer type);

    void checkEmailCode(String emial, String code);
}
