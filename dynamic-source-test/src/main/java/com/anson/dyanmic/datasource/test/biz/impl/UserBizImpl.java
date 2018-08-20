package com.anson.dyanmic.datasource.test.biz.impl;

import com.anson.dyanmic.datasource.test.biz.IUserBiz;
import com.anson.dyanmic.datasource.test.mapper.UserMapper;
import com.anson.dyanmic.datasource.test.po.User;
import com.anson.dynamic.datasource.annotation.DS;


import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 用户登录信息 服务实现类
 * </p>
 *
 * @author simon.pei
 * @since 2018-01-08
 */
@Service
@Slf4j
@DS("slave")
public class UserBizImpl extends ServiceImpl<UserMapper, User> implements IUserBiz {

    @Resource
    private UserMapper userMapper;



    @Override
    public User login(Long id)  {
        log.info("------------执行-----------------");
        return userMapper.selectById(id);

    }


}
