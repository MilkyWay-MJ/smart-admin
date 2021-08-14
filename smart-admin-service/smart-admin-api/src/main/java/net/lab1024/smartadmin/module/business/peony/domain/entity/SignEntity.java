package net.lab1024.smartadmin.module.business.peony.domain.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
 * @since JDK1.8 签到表映射实体
 */
@Data
@TableName("t_sign")
public class SignEntity {

    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Long id;

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

}
