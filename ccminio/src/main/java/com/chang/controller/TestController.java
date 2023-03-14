package com.chang.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:ChenChang
 * @Description: <more hard, more luckier>
 * @Data: Create in 11:12 2022/11/7
 * @Modified By:
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("interceptorTest")
    public void test01(){
        System.out.println("controller执行了");
    }
}
