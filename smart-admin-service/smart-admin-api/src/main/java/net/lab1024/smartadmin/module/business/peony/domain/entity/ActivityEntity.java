package net.lab1024.smartadmin.module.business.peony.domain.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import net.lab1024.smartadmin.common.domain.BaseEntity;

import java.util.Date;

/**
 * [ 活动 ]
 *
 * @author 莫京
 * @version 1.0
 * @company 1024创新实验室( www.1024lab.net )
 * @copyright (c)  1024创新实验室( www.1024lab.net )Inc. All rights reserved.
 * @date 2020-04-06 18:17:56
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
