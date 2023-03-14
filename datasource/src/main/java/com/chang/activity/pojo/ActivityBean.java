package com.chang.activity.pojo;

import com.chang.base.bean.BaseBean;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author:ChenChang
 * @Description: <more hard, more luckier>
 * @Data: Create in 15:38 2022/4/1
 * @Modified By:
 */
@Data
public class ActivityBean extends BaseBean implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 活动编号
     */
    private Long id;

    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动价格
     */
    private Integer activityPrice;

    /**
     * 活动图片
     */
    private String activityInfoUrl;

    /**
     * 活动类型(1线上, 2线下)
     */
    private Integer activityType;

    /**
     * 活动类型描述
     */
    private String activityTypeWords;

    /**
     * 活动地点
     */
    private String activityAddress;

    /**
     * 活动关键词
     */
    private String activityKeywords;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 报名开始时间
     */
    private Date signStartTime;

    /**
     * 报名结束时间
     */
    private Date signEndTime;

    /**
     * 活动介绍信息
     */
    private String activityIntro;

    /**
     * 报名须知
     */
    private String applicationNote;

    /**
     * 底部图片
     */
    private String footerImage;

    /**
     * 报名次数 0 一次 1多次
     */
    private Integer voteTimes;

    /**
     * 系统通知
     */
    private String noticeTemplate;

    /**
     * 报名男生人数限制
     */
    private Integer numberNan;

    /**
     * 报名女生人数限制
     */
    private Integer numberNv;

    /**
     * 分享图片
     */
    private String shareImgUrl;

    /**
     * 分享标题
     */
    private String shareTitle;

    /**
     * 分享描述
     */
    private String shareDescribe;

    /**
     * 浏览数
     */
    private Long views;

    /**
     * 置顶标志(0否 1是)
     */
    private Integer stick;

}
