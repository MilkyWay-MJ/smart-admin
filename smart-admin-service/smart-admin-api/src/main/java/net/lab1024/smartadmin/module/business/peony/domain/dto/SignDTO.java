package net.lab1024.smartadmin.module.business.peony.domain.dto;

import lombok.Data;

import java.util.Date;

/**
 * @project: oahj
 * @Author: 莫京
 * @Date: 2021年08月14日 10:00
 * @Description:
 */
@Data
public class SignDTO {

    /**
     * 姓名
     */
    private String name;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 经度值
     */
    private Double longitude;

    /**
     * 纬度值
     */
    private Double latitude;

    /**
     * 签到时间
     */
    private Date signTime;

    /**
     * 微信昵称
     */
    private String nickName;

    /**
     * 活动id
     */
    private String activityId;

    /**
     * 微信openId
     */
    private String openId;

    /**
     * 测试用
     */
    private String globalData;

}
