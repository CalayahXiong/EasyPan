package com.easypan.service.impl;

import com.easypan.component.RedisComponent;
import com.easypan.entity.pojo.UserInfo;
import com.easypan.entity.query.SimplePage;
import com.easypan.entity.query.UserInfoExample;
import com.easypan.entity.vo.PaginationResultVO;
import com.easypan.enums.PageSize;
import com.easypan.mappers.UserInfoMapper;
import com.easypan.service.EmailCodeService;
import com.easypan.service.UserInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    private UserInfoMapper< UserInfo , UserInfoExample > userInfoMapper;

    @Resource
    private EmailCodeService emailCodeService;

    @Resource
    private RedisComponent redisComponent;

    /**
     * 根据条件查询列表
     */
    public List<UserInfo> findListByParam(UserInfoExample query) {

        return this.userInfoMapper.selectList(query);
    }

    /**
     * 根据条件查询数量
     */
    public Integer findCountByParam(UserInfoExample query) {

        return this.userInfoMapper.selectCount(query);
    }

    /**
     * 分页查询
     */
    public PaginationResultVO<UserInfo> findListByPage(UserInfoExample query) throws IOException {

        Integer count = this.findCountByParam(query);
        Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
        SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
        query.setSimplePage(page);
        List<UserInfo> list = this.findListByParam(query);
        PaginationResultVO<UserInfo> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
        return result;

    }

    /**
     * 新增
     */
    public Integer add(UserInfo bean ) {

        return this.userInfoMapper.insert(bean);
    }

    /**
     * 批量新增
     */
    public Integer addBatch(List<UserInfo> listBean) {

        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.userInfoMapper.insertBatch(listBean);
    }

    /**
     * 批量新增/修改
     */
    public Integer addOrUpdateBatch(List<UserInfo> listBean) {

        if (listBean == null || listBean.isEmpty()) {
            return 0;
        }
        return this.userInfoMapper.insertOrUpdateBatch(listBean);
    }

    /**
     * 根据UserId查询
     */
    public UserInfo getUserInfoByUserId(String userId) {

        return this.userInfoMapper.selectByUserId(userId);
    }

    /**
     * 根据UserId删除
     */
    public Integer deleteUserInfoByUserId(String userId) {

        return this.userInfoMapper.deleteByUserId(userId);

    }

    /**
     * 根据UserId更新
     */
    public Integer updateUserInfoByUserId( UserInfo bean, String userId) {

        return this.userInfoMapper.updateByUserId(bean,userId);

    }

    /**
     * 根据Email查询
     */
    public UserInfo getUserInfoByEmail(String email) {

        return this.userInfoMapper.selectByEmail(email);
    }

    /**
     * 根据Email删除
     */
    public Integer deleteUserInfoByEmail(String email) {

        return this.userInfoMapper.deleteByEmail(email);

    }

    /**
     * 根据Email更新
     */
    public Integer updateUserInfoByEmail( UserInfo bean, String email) {

        return this.userInfoMapper.updateByEmail(bean,email);

    }

    /**
     * 根据QqOpenId查询
     */
    public UserInfo getUserInfoByQqOpenId(String qqOpenId) {

        return this.userInfoMapper.selectByQqOpenId(qqOpenId);
    }

    /**
     * 根据QqOpenId删除
     */
    public Integer deleteUserInfoByQqOpenId(String qqOpenId) {

        return this.userInfoMapper.deleteByQqOpenId(qqOpenId);

    }

    /**
     * 根据QqOpenId更新
     */
    public Integer updateUserInfoByQqOpenId( UserInfo bean, String qqOpenId) {

        return this.userInfoMapper.updateByQqOpenId(bean,qqOpenId);

    }

    /**
     * 根据NickName查询
     */
    public UserInfo getUserInfoByNickName(String nickName) {

        return this.userInfoMapper.selectByNickName(nickName);
    }

    /**
     * 根据NickName删除
     */
    public Integer deleteUserInfoByNickName(String nickName) {

        return this.userInfoMapper.deleteByNickName(nickName);

    }

    /**
     * 根据NickName更新
     */
    public Integer updateUserInfoByNickName( UserInfo bean, String nickName) {

        return this.userInfoMapper.updateByNickName(bean,nickName);

    }
}