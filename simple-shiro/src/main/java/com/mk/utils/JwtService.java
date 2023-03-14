package com.mk.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.mk.exception.ApiException;
import com.mk.exception.ErrorCode;
import com.mk.trandata.bo.UserInfo;
import com.xiaoleilu.hutool.date.DateUtil;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

/**
 * @Author:ChenChang
 * @Description: jwt service
 * @Data: Create in 15:47 2023/03/01
 * @Modified By:
 */
public class JwtService {

    private static String secret = "3@##%$#%!@";

    /**
     * 创建token
     * @param params
     * @return
     */
    public static String createToken(Map<String, Object> params){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.DATE,7);   //设置过期时间
        String token = JWT.create()
                .withClaim("userInfo", params)    //设置payload
                .withExpiresAt(instance.getTime())  //设置过期时间
                .sign(Algorithm.HMAC256(secret));   //签名 解签时编码格式需要一致
        return token;
    }

    /**
     * 验证，并返回payload
     * 如果在验签的过程中发生异常，则说明验签失败
     */
    public static void signToken(String token){
        DecodedJWT jwt = JWT.require(Algorithm.HMAC256(secret))
                .build()
                .verify(token);//验签的时候格式需要一致
    }


    public static UserInfo getUserInfo(String token){
        UserInfo userInfo = new UserInfo();
        try{
            DecodedJWT jwt = JWT.require(Algorithm.HMAC256(secret))
                    .build()
                    .verify(token);
            Map<String, Object> user = jwt.getClaim("userInfo").asMap();
            Date date = jwt.getClaim("exp").asDate();
            userInfo.setUid(Long.parseLong(user.get("uid").toString()));
            userInfo.setUsername(user.get("username").toString());
            userInfo.setTokenExp(DateUtil.format(date, DateUtil.NORM_DATETIME_PATTERN));
            userInfo.setToken(token);
        }catch (Exception e){
            throw new ApiException(ErrorCode.TOKEN_IS_INVALID);
        }

        return userInfo;
    }
}
