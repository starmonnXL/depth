package com.depth.cms;

import com.depth.cms.content.metatype.DTO;
import com.depth.cms.content.metatype.impl.BaseDTO;
import com.depth.cms.content.service.UpmsFacade;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * <p>@Title   </p>
 * <p>@Version 1.0.0 </p>
 * <p>@author chenjinxiu</p>
 * <p>@date 2017/11/23 16:19 </p>
 * <p>@Copyright © dgg group.All Rights Reserved. 版权信息</p>
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/spring-context.xml")
public class UpmsFacadeTest {

    @Resource
    private UpmsFacade upmsFacade;

    @Test
    public void testInsertUpmsRegister() {
        DTO params = new BaseDTO();
        params.put("phone", "13808069365");
        params.put("passWord", "chenxin");
        params.put("name", "starmoon");
        params.put("state", "0");

        upmsFacade.insertUpmsRegister(params);
    }

    @Test
    public void updateUpmsRegisterByIdTest(){
        DTO params = new BaseDTO();
        params.put("id", "933612442322341888");
        params.put("uid", "0");
        params.put("uname", "starmoon");
        params.put("state", "1");
        params.put("level", "0");
        upmsFacade.updateUpmsRegisterById(params);
    }
}
