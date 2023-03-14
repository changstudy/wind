package com.chang.user.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 居民信息
 * </p>
 *
 * @author ChenChang
 * @since 2021-11-21
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户id
     */
    private Integer id;

    /**
     * 姓名
     */
    private String name;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 学历信息
     */
    private String academic;

    /**
     * 是否军烈属
     */
    private Integer soldier;

    /**
     * 政治面貌
     */
    private String politicsStatus;

    /**
     * 银行卡号
     */
    private String idCard;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 身份证号
     */
    private String creditCard;

    /**
     * 身份证图片
     */
    private String image;

    /**
     * 头像
     */
    private String photo;

    /**
     * 与户主关系
     */
    private String relation;

    /**
     * 活动分
     */
    private Double activityPoint;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐值
     */
    private String salt;

    /**
     * 所属户编号
     */
    private Integer familyId;

    /**
     * 逻辑删除 0未删除 1删除
     */
    private Integer del;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;


}
