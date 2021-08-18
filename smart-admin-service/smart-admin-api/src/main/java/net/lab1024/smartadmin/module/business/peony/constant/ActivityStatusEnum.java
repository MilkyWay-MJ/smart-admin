package net.lab1024.smartadmin.module.business.peony.constant;

/**
 * @project: oahj
 * @Author: 莫京
 * @Date: 2021年08月18日 16:33
 * @Description: 活动状态枚举
 */
public enum ActivityStatusEnum {

    ACTIVITY_STATUS_GOING(0,"进行中"),
    ACTIVITY_STATUS_END(1,"已结束");


    private Integer statusNum;

    private String statusName;

    ActivityStatusEnum(Integer statusNum, String statusName) {
        this.statusNum = statusNum;
        this.statusName = statusName;
    }

    public Integer getStatusNum() {
        return statusNum;
    }

    public void setStatusNum(Integer statusNum) {
        this.statusNum = statusNum;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}
