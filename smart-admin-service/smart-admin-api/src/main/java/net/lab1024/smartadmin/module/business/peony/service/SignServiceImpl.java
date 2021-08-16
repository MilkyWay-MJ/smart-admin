package net.lab1024.smartadmin.module.business.peony.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.lab1024.smartadmin.module.business.peony.dao.SignDao;
import net.lab1024.smartadmin.module.business.peony.domain.entity.SignEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
@Service
@Primary
public class SignServiceImpl extends ServiceImpl<SignDao, SignEntity> implements SignService {

    @Autowired
    private SignDao signDao;

    @Override
    public SignEntity selectByOpenid(String openid) {
        QueryWrapper<SignEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(new HashMap<String, Object>() {{
            put("open_id", openid);
        }});
        SignEntity signEntity = signDao.selectOne(queryWrapper);

        return signEntity;
    }




}
