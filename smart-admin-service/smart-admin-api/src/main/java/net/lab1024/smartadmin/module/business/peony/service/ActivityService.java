package net.lab1024.smartadmin.module.business.peony.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.peony.domain.dto.ActivityAddDTO;
import net.lab1024.smartadmin.module.business.peony.domain.dto.ActivityQueryDTO;
import net.lab1024.smartadmin.module.business.peony.domain.entity.ActivityEntity;
import net.lab1024.smartadmin.module.business.peony.domain.vo.ActivityVO;

import java.util.List;


/**
 * @project: oahj
 * @Author: 莫京
 * @Date: 2021年08月12日 19:08
 * @Description:
 */
public interface ActivityService extends IService<ActivityEntity> {

    /**
     * 获取分页活动
     *
     * @param queryDTO
     * @return: net.lab1024.smartadmin.common.domain.ResponseDTO<net.lab1024.smartadmin.common.domain.PageResultDTO < net.lab1024.smartadmin.module.business.peony.domain.vo.ActivityVO>>
     * @Author: 莫京 2021/8/17
     */
    ResponseDTO<PageResultDTO<ActivityVO>> queryByPage(ActivityQueryDTO queryDTO);

    /**
     * 新增/更新活动
     *
     * @param addDTO
     * @return: net.lab1024.smartadmin.common.domain.ResponseDTO<java.lang.String>
     * @Author: 莫京 2021/8/17
     */
    ResponseDTO<String> saveAct(ActivityAddDTO addDTO);

    /**
     * 获取没到结束时间的活动
     *
     * @param
     * @return: java.util.List<net.lab1024.smartadmin.module.business.peony.domain.vo.ActivityVO>
     * @Author: 莫京 2021/8/17
     */
    List<ActivityVO> getOngoingActivities();

    /**
     * 批量删除活动
     *
     * @param idList
     * @return: net.lab1024.smartadmin.common.domain.ResponseDTO<java.lang.String>
     * @Author: 莫京 2021/8/18
     */
    ResponseDTO<String> deleteByIds(ValidateList<Long> idList);
}
