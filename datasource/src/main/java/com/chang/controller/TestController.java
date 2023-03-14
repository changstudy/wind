package com.chang.controller;

import com.chang.annotation.TowLimiter;
import com.chang.configuration.SpringContext;
import com.chang.user.pojo.UserBean;
import com.chang.user.service.UserService;
import com.chang.user.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:ChenChang
 * @Description: <more hard, more luckier>
 * @Data: Create in 15:52 2022/11/10
 * @Modified By:
 */
@RestController
public class TestController {

    @Autowired
    public UserService userService;

    @TowLimiter
    @GetMapping("/userInfo")
    public UserBean getUserInfo(@RequestParam("uid") String uid){
        TestController testController = SpringContext.getBean("testController", TestController.class);
        // 第一个问题 这两个对象地址相同吗？
        System.out.println(testController);
        System.out.println(this);
        System.out.println(this == testController); // 第二问题 这个返回什么
        // 第三个问题 这个会执行 @TowLimiter注解吗(被AOP增强后的效果吗)
        this.doTest("2");
        this.doTest("3");
        return userService.selectUserById();
    }

    @TowLimiter
    @GetMapping("/doTest")
    public void doTest(@RequestParam("uid") String uid){
        System.out.println("do");
    }
}
