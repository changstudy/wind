package com.chang.base.bean;

import lombok.Data;

import java.util.Date;

/**
 * @Author:ChenChang
 * @Description: <more hard, more luckier>
 * @Data: Create in 15:41 2022/4/1
 * @Modified By:
 */
@Data
public class BaseBean {

    private Integer del; // 删除标记位 0未删除 1删除

    private Date createdTime;

    private Date updatedTime;
}
