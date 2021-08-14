package net.lab1024.smartadmin.module.business.peony.domain.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @project: oahj
 * @Author: 莫京
 * @Date: 2021年08月03日 14:43
 * @Description: 微信appId,appSecret,位置服务key的属性注入
 */
@Component
@ConfigurationProperties(prefix = "weixin")
@Data
public class WxBean {

    private String appId;

    private String appSecret;
    
    private String key;


}
