package net.lab1024.smartadmin.module.business.peony.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.lab1024.smartadmin.common.domain.BaseEntity;

import java.util.Date;

/**
 * 数据库映射 [ 活动 ]
 *
 * @author 莫京
 * @version 1.0
 * @company 华景城建筑设计有限公司(www.hjcadc.com)
 * @copyright (c)  华景城建筑设计有限公司( www.hjcadc.com )Inc. All rights reserved.
 * @date 2021-08-10 15:26:56
 * @since JDK1.8
 */
@Data
@TableName("t_activity")
public class ActivityEntity extends BaseEntity{


    /**
     * 活动名称
     */
    private String name;

    /**
     * 活动地点
     */
    private String location;

    /**
     * 经度值
     */
    private Double longitude;

    /**
     * 纬度值
     */
    private Double latitude;

    /**
     * 签到半径
     */
    private Integer signRadius;

    /**
     * 活动开始时间
     */
    private Date startTime;

    /**
     * 活动结束时间
     */
    private Date endTime;

    /**
     * 签到截止时间
     */
    private Date validTime;

    /**
     * 参加人员
     */
    private String attendee;

    /**
     * 活动简介
     */
    private String brief;

}
