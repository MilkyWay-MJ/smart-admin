package net.lab1024.smartadmin.module.business.peony.domain.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import net.lab1024.smartadmin.common.domain.PageParamDTO;

import java.util.Date;

/**
 * 查询参数 [ 活动 ]
 *
 * @author 莫京
 * @version 1.0
 * @company 华景城建筑设计有限公司(www.hjcadc.com)
 * @copyright (c)  华景城建筑设计有限公司( www.hjcadc.com )Inc. All rights reserved.
 * @date 2021-08-10 15:26:56
 * @since JDK1.8
 */
@Data
public class ActivityQueryDTO extends PageParamDTO {

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

    @ApiModelProperty("活动结束时间-开始")
    private Date endTimeBegin;

    @ApiModelProperty("活动结束时间-截止")
    private Date endTimeEnd;

    @ApiModelProperty("创建时间-开始")
    private Date createTimeBegin;

    @ApiModelProperty("创建时间-截止")
    private Date createTimeEnd;

    @ApiModelProperty("上次更新时间-开始")
    private Date updateTimeBegin;

    @ApiModelProperty("上次更新时间-结束")
    private Date updateTimeEnd;
}
