package net.lab1024.smartadmin.module.business.peony.domain.vo;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

import static net.lab1024.smartadmin.module.business.peony.constant.ActivityStatusEnum.ACTIVITY_STATUS_END;
import static net.lab1024.smartadmin.module.business.peony.constant.ActivityStatusEnum.ACTIVITY_STATUS_GOING;

/**
 *  [ 活动 ]
 *
 * @author 莫京
 * @version 1.0
 * @company 1024创新实验室( www.1024lab.net )
 * @copyright (c) 1024创新实验室( www.1024lab.net )Inc. All rights reserved.
 * @date  2021-08-10 16:17:56
 * @since JDK1.8
 */
@Data
public class ActivityExcelVO {
    @Excel(name = "ID")
    private Long id;

    @Excel(name = "活动名称")
    private String name;

    @Excel(name = "活动地点")
    private String location;

    @Excel(name = "活动开始时间", format = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date startTime;

    @Excel(name = "活动结束时间", format = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date endTime;

    @Excel(name = "创建时间", format = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;

    @Excel(name = "更新时间", format = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updateTime;

    @Excel(name = "活动状态")
    private String status;



    public String getStatus() {
        Date now = new Date();
        if (now.compareTo(this.endTime) <= 0) {
            this.status = ACTIVITY_STATUS_GOING.getStatusName();
        } else {
            this.status = ACTIVITY_STATUS_END.getStatusName();
        }
        return status;
    }
}
