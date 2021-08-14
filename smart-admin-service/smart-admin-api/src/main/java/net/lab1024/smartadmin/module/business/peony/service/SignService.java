package net.lab1024.smartadmin.module.business.peony.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.lab1024.smartadmin.module.business.peony.dao.SignDao;
import net.lab1024.smartadmin.module.business.peony.domain.entity.SignEntity;
import net.lab1024.smartadmin.module.business.peony.domain.vo.SignVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
@Service
public class SignService extends ServiceImpl<SignDao, SignEntity> implements SignServiceInterface {

    @Autowired
    private SignDao signDao;

    @Override
    public SignEntity selectByOpenid(String openid) {
        System.out.println("openid = " + openid);
        QueryWrapper<SignEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.allEq(new HashMap<String, Object>() {{
            put("open_id", openid);
        }});
        SignEntity signEntity = signDao.selectOne(queryWrapper);

        return signEntity;
    }




}
