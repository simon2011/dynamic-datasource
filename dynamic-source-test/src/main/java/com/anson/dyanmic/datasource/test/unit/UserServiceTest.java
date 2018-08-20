package com.anson.dyanmic.datasource.test.unit;


import com.anson.dyanmic.datasource.test.ApplicationStarter;
import com.anson.dyanmic.datasource.test.biz.IUserBiz;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * @ClassName BackendServiceTest
 * @Description TODO
 * @Author simon.pei
 * @Date 2018/7/5 10:49
 * @Version 1.0
 **/


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ApplicationStarter.class )
@Slf4j
public class UserServiceTest {

    @Resource(name="userBizImpl")
    private IUserBiz iUserBiz;



    @Test
    public void loadBackendAction(){
        Long id = 900552582362091520L;
        log.info("result-----: "+iUserBiz.login(id).toString());

    }

}
