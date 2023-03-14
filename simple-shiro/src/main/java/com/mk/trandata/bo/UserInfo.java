package com.mk.trandata.bo;

import lombok.Data;

/**
 * @Author:ChenChang
 * @Description: <more hard, more luckier>
 * @Data: Create in 14:53 2023/1/11
 * @Modified By:
 */
@Data
public class UserInfo {

    private Long uid;

    private String username;

    private String tokenExp;

    private String token;
}
