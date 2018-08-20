package com.anson.dyanmic.datasource.test.biz;



import com.anson.dyanmic.datasource.test.po.User;
import com.baomidou.mybatisplus.service.IService;


/**
 * <p>
 * 用户登录信息 服务类
 * </p>
 *
 * @author simon.pei
 * @since 2018-01-08
 */
public interface IUserBiz extends  IService<User> {

    User login(Long id) ;

}
