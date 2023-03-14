package com.mk.controller;

import com.mk.exception.ApiException;
import com.mk.exception.ErrorCode;
import com.mk.pojo.MkUser;
import com.mk.trandata.dto.LoginBusinessRequest;
import com.mk.utils.JwtService;
import com.mk.utils.ResultVo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author:ChenChang
 * @Description: user handle
 * @Data: Create in 15:02 2023/3/1
 * @Modified By:
 */
@RestController
@RequestMapping("/mk/user")
public class UserController {


    @PostMapping("/login")
    public ResultVo userLogin(@RequestBody LoginBusinessRequest loginBusinessRequest){
        String username = loginBusinessRequest.getUsername();
        String password = loginBusinessRequest.getPassword();
        // db select
        if ("mkChang".equals(username) && "123456".equals(password)){
            Map<String, Object> params = new HashMap<>();
            params.put("uid", 517);
            params.put("username", username);
            String token = JwtService.createToken(params);
            params.put("token", token);
            return new ResultVo(params);
        }
        throw new ApiException(ErrorCode.LOGIN_FAIL);
    }

    //@RequiresPermissions(value = "select")
    //@RequiresRoles(value = "admin")
    @GetMapping("/userInfo")
    public ResultVo<MkUser> getUserInfo(){
        return new ResultVo("success");
    }

}
