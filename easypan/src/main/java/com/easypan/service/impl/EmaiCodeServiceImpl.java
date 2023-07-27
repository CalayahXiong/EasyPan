package com.easypan.service.impl;

import com.easypan.component.RedisComponent;
import com.easypan.entity.dto.SystemSettingDto;
import com.easypan.entity.config.AppConfig;
import com.easypan.entity.constants.Constants;
import com.easypan.entity.pojo.EmailCode;
import com.easypan.entity.pojo.UserInfo;
import com.easypan.entity.query.EmailCodeQuery;
import com.easypan.entity.query.SimplePage;
import com.easypan.entity.query.UserInfoExample;
import com.easypan.entity.vo.PaginationResultVO;
import com.easypan.enums.PageSize;
import com.easypan.enums.ResponseCodeEnum;
import com.easypan.exception.BusinessException;
import com.easypan.mappers.EmailCodeMapper;
import com.easypan.mappers.UserInfoMapper;
import com.easypan.service.EmailCodeService;
import com.easypan.utils.StringTools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service("emailCodeService") //一个注解，标注一个类是一个业务逻辑类，属于服务层，也可以用@Component
public class EmaiCodeServiceImpl implements EmailCodeService {

    private static final Logger logger = LoggerFactory.getLogger(EmaiCodeServiceImpl.class);
    @Resource
    private EmailCodeMapper<EmailCode, EmailCodeQuery> emailCodeMapper;
    @Resource
    private UserInfoMapper<UserInfo, UserInfoExample> userInfoMapper;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private AppConfig appConfig;
    @Resource
    private RedisComponent redis;
    @Value("${spring.mail.username")
    private String fromEmail;

    /*
    根据邮件代码查列表
     */
    public List<EmailCode> findListByParam(EmailCodeQuery param) {
        return this.emailCodeMapper.selectList(param);
    }

    /*
    符合条件的数量
     */
    public Integer findCountByParm(EmailCodeQuery param) {
        return this.emailCodeMapper.selectCount(param);
    }

    /*
    分页查询
     */
    @Override
    public PaginationResultVO<EmailCode> findListByPage(EmailCodeQuery param) {

        Integer count = this.findCountByParm(param); //符合条件的数量
        Integer pageSize = param.getPageSize() == null ? PageSize.SIZE15.getSize() : param.getPageSize();
        SimplePage page = null; //创建一个SP
        try {
            page = new SimplePage(param.getPageNo(), count, pageSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
        param.setSimplePage(page);
        List<EmailCode> list = this.findListByParam(param);
        PaginationResultVO<EmailCode> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;
    }

    public Integer add(EmailCode bean) {
        return this.emailCodeMapper.insert(bean);
    }

    public Integer addBatch(List<EmailCode> listBean) {
        if(listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.emailCodeMapper.insertBatch(listBean);
    }

    public Integer addOrUpdateBatch(List<EmailCode> listBean) {
        if(listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.emailCodeMapper.insertBatch(listBean);
    }

    @Override
    public EmailCode getEmailCodeByEmailAndCode(String email, String code) {
        return this.emailCodeMapper.selectByEmailAndCode(email, code);
    }

    @Override
    public Integer deleteEmailCodeByEmailAndCode(String email, String code) {
        return this.emailCodeMapper.deleteByEmailAndCode(email, code);
    }

    @Override
    public Integer updateEmailCodeByEmailAndCode(EmailCode ben, String email, String code) {
        return this.emailCodeMapper.updateByEmailAndCode(ben, email, code);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void sendEmailCode(String email, Integer type) {
        if(type==Constants.ZERO) { //状态：注册
            UserInfo userInfo = userInfoMapper.selectByEmail(email);
            if(null!=userInfo){
                throw new BusinessException(ResponseCodeEnum.CODE_906);
            }
        }
        String code = StringTools.getRandomNumber(Constants.FIXED_LENGTH);
        sendEmailCode(email, code);
        //TODO 发送验证码到controller
        //验证码无效
        emailCodeMapper.disableEmailCode(email); //重复点击发送emailCode,之前的定为无效
        EmailCode emailCode = new EmailCode();
        emailCode.setCode(code);
        emailCode.setEmail(email);
        emailCode.setStatus(Constants.ZERO);
        emailCode.setCreateTime(new Date());
        emailCode.setStatus(0);
        emailCodeMapper.insert(emailCode);
    }

    @Override
    public void checkEmailCode(String email, String code) {
        EmailCode emailCode = emailCodeMapper.selectByEmailAndCode(email, code);
        if(emailCode == null) {
            throw new BusinessException(ResponseCodeEnum.CODE_908);
        }
        if(emailCode.getStatus()==1 || emailCode.getCreateTime().getTime()+redis.getSysSettingDto().getRegistryMailExpireTime()*1000*60<System.currentTimeMillis()) {
            throw new BusinessException(ResponseCodeEnum.CODE_907);
        }
        emailCodeMapper.disableEmailCode(email);
    }

    private void sendEmailCode(String toEmail, String code){

        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(appConfig.getSendUserName());
            helper.setTo(toEmail);

            SystemSettingDto systemSettingDto = redis.getSysSettingDto();

            helper.setSubject(systemSettingDto.getRegisterMailTitle());
            //helper.setSubject("email title");
            helper.setText(systemSettingDto.getReisterEmailContent(), code);
            //helper.setSentDate(new Date());
            javaMailSender.send(message);
        } catch (MessagingException e) {
            //e.printStackTrace();
            logger.error("email send failed", e);
            throw new BusinessException(("send failed"));
        }
    }
}
