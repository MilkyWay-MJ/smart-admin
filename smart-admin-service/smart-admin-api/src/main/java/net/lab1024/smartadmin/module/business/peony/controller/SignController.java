package net.lab1024.smartadmin.module.business.peony.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.anno.NoNeedLogin;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.module.business.peony.domain.dto.SignDTO;
import net.lab1024.smartadmin.module.business.peony.domain.entity.SignEntity;
import net.lab1024.smartadmin.module.business.peony.domain.entity.WxBean;
import net.lab1024.smartadmin.module.business.peony.domain.vo.SignVO;
import net.lab1024.smartadmin.module.business.peony.service.SignServiceInterface;
import net.lab1024.smartadmin.util.HttpUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * [ 活动 ]
 *
 * @author 莫京
 * @version 1.0
 * @company 华景城建筑设计有限公司(www.hjcadc.com)
 * @copyright (c)  华景城建筑设计有限公司( www.hjcadc.com )Inc. All rights reserved.
 * @date 2021-08-10 15:26:56
 * @since JDK1.8
 */
@RestController
@RequestMapping("/sign")
@Api(tags = {"小程序端请求"})
public class SignController extends BaseController {

    @Autowired
    private SignServiceInterface signService;

    @Autowired
    private WxBean wxBean;

    /**
     * 登录凭证校验,用code换取openid
     *
     * @param code 微信小程序登录成功的code
     * @param from 小程序appid(后台已存有，可以不用这个)
     * @return: java.util.Map
     * @Author: 莫京 2021/8/5
     */
    @GetMapping("/code2openid")
    @NoNeedLogin
    public Map code2openid(String code, String from) {
        Map data = new HashMap();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" +
                wxBean.getAppId() + "&secret=" + wxBean.getAppSecret() + "&js_code=" + code + "&grant_type=authorization_code";
        String info = HttpUtils.doHttpPost(url, null, null, null, 5);
        Map<String, String> httpInfo = (Map<String, String>) JSON.parse(info);
        // 获取openid失败
        if (httpInfo.get("openid") == null) {
            data.put("msg", "获取openid失败");
        } else {
            data.put("openid", httpInfo.get("openid"));
        }
        data.put("data", httpInfo);
        return data;
    }

    /**
     * 根据openid获取签到信息
     *
     * @param openid 微信openid
     * @return: java.util.Map
     * @Author: 莫京 2021/8/5
     */
    @GetMapping("/getByOpenid")
    @NoNeedLogin
    @ApiOperation(value = "根据openid获取签到信息", notes = "@author 莫京")
    public Map getByOpenid(String openid) {
        Map data = new HashMap();
        if (StringUtils.isEmpty(openid)) {
            data.put("msg", "获取openid失败");
            return data;
        }
        SignEntity one = signService.selectByOpenid(openid);
        data.put("user", one);
        System.out.println("one = " + one);
        return data;
    }

    /**
     * 点击"立即签到"按钮,新增签到信息
     *
     * @param signDTO 接收前端签到信息的封装类
     * @return: java.util.Map
     * @Author: 莫京 2021/8/5
     */
    @GetMapping("/registerByOpenid")
    @NoNeedLogin
    @ApiOperation(value = "新增签到信息", notes = "@author 莫京")
    public Map registerByOpenid(SignDTO signDTO) {
        Map data = new HashMap();
        // 获取openid失败
        if (StringUtils.isEmpty(signDTO.getOpenId())) {
            data.put("msg", "获取openid失败");
        } else {
            // 查询是否已签到过
            QueryWrapper<SignEntity> queryWrapper = new QueryWrapper<SignEntity>().eq("open_id", signDTO.getOpenId()).last("LIMIT 1");
            SignEntity one = signService.getOne(queryWrapper);
            System.out.println("one = " + one);
            if (one != null) {
                data.put("is_registered", true);
                return data;
            }
            SignEntity sign = new SignEntity();
            sign.setOpenId(signDTO.getOpenId());
            sign.setPhone(signDTO.getPhone());
            sign.setNickName(signDTO.getNickName());
            sign.setLatitude(signDTO.getLatitude());
            sign.setLongitude(signDTO.getLongitude());
            sign.setName(signDTO.getName());
            sign.setActivityId(signDTO.getActivityId());
            sign.setSignTime(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant()));
            // 数据库插入用户数据
            boolean save = signService.save(sign);
            data.put("is_registered", false);
            if (!save) {
                data.put("msg", "数据保存失败");
                data.put("is_registered", true);
            }
            data.put("user", sign);
        }
        return data;
    }

}
