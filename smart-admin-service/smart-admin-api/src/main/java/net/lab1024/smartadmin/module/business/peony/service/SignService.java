package net.lab1024.smartadmin.module.business.peony.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.peony.domain.dto.ActivityAddDTO;
import net.lab1024.smartadmin.module.business.peony.domain.dto.ActivityQueryDTO;
import net.lab1024.smartadmin.module.business.peony.domain.entity.ActivityEntity;
import net.lab1024.smartadmin.module.business.peony.domain.entity.SignEntity;
import net.lab1024.smartadmin.module.business.peony.domain.vo.ActivityVO;
import net.lab1024.smartadmin.module.business.peony.domain.vo.SignVO;

import java.util.List;


/**
 * @project: oahj
 * @Author: 莫京
 * @Date: 2021年08月12日 19:08
 * @Description:
 */
public interface SignService extends IService<SignEntity> {

    /**
     * 根据openid和活动id获取签到信息
     *
     * @param openid 微信openid
     * @return: java.util.Map
     * @Author: 莫京 2021/8/5
     */
    SignEntity selectByOpenidAndActivityid(String openid, String activityid);

    /**
     * 根据活动id获取签到人信息
     *
     * @param
     * @return:
     * @Author: 莫京 2021/8/31
     */
    ResponseDTO<IPage<SignVO>> getUserByActId(ActivityQueryDTO queryDTO);
}
