package net.lab1024.smartadmin.module.business.peony.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * 新建 [ 活动 ]
 *
 * @author 莫京
 * @version 1.0
 * @company 华景城建筑设计有限公司(www.hjcadc.com)
 * @copyright (c)  华景城建筑设计有限公司( www.hjcadc.com )Inc. All rights reserved.
 * @date 2021-08-10 15:26:56
 * @since JDK1.8
 */
@Data
public class ActivityAddDTO {

    @ApiModelProperty("ID")
    private Long id;

    @ApiModelProperty("活动名称")
    private String name;

    @ApiModelProperty("活动地点")
    private String location;

    @ApiModelProperty("活动开始时间")
    private Date startTime;

    @ApiModelProperty("活动结束时间")
    private Date endTime;

    @ApiModelProperty("签到截止时间")
    private Date validTime;

    @ApiModelProperty("活动简介")
    private String brief;

    @ApiModelProperty("经度值")
    private Double longitude;

    @ApiModelProperty("纬度值")
    private Double latitude;

    @ApiModelProperty("签到半径")
    private Integer signRadius;

}
