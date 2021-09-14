package net.lab1024.smartadmin.module.business.peony.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.common.domain.ValidateList;
import net.lab1024.smartadmin.module.business.peony.dao.ActivityDao;
import net.lab1024.smartadmin.module.business.peony.dao.SignDao;
import net.lab1024.smartadmin.module.business.peony.domain.dto.ActivityAddDTO;
import net.lab1024.smartadmin.module.business.peony.domain.dto.ActivityQueryDTO;
import net.lab1024.smartadmin.module.business.peony.domain.dto.PeonyQueryDTO;
import net.lab1024.smartadmin.module.business.peony.domain.entity.ActivityEntity;
import net.lab1024.smartadmin.module.business.peony.domain.vo.ActivityExcelVO;
import net.lab1024.smartadmin.module.business.peony.domain.vo.ActivityVO;
import net.lab1024.smartadmin.module.business.peony.domain.vo.PeonyExcelVO;
import net.lab1024.smartadmin.module.system.employee.domain.bo.EmployeeBO;
import net.lab1024.smartadmin.module.system.login.domain.RequestTokenBO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import net.lab1024.smartadmin.util.SmartRequestTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * [ 活动 ]
 *
 * @author 莫京
 * @version 1.0
 * @company 1024创新实验室(www.1024lab.net)
 * @copyright (c)  1024创新实验室( www.1024lab.net )Inc. All rights reserved.
 * @date 2020-04-06 18:17:56
 * @since JDK1.8
 */
@Service
@Primary
public class ActivityServiceImpl extends ServiceImpl<ActivityDao, ActivityEntity> implements ActivityService {

    @Autowired
    private ActivityDao activityDao;

    @Override
    public ResponseDTO<PageResultDTO<ActivityVO>> queryByPage(ActivityQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ActivityVO> voList = activityDao.queryByPage(page, queryDTO);
        PageResultDTO<ActivityVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> saveAct(ActivityAddDTO addDTO) {
        ResponseDTO responseDTO = ResponseDTO.error();
        ActivityEntity ae = new ActivityEntity();
        EmployeeBO user = SmartRequestTokenUtil.getRequestUser().getEmployeeBO();

        try {
            // 新增
            if (addDTO.getId() == null) {
                ae.setCreateUser(user.getId());

            } else { // 修改
                ae = activityDao.selectById(addDTO.getId());
                ae.setUpdateUser(user.getId());
                ae.setUpdateTime(null);
            }
            SmartBeanUtil.copyProperties(addDTO, ae);
            this.saveOrUpdate(
                    ae,
                    Wrappers.<ActivityEntity>lambdaUpdate()
                            .set(ActivityEntity::getStartTime, addDTO.getStartTime())
                            .set(ActivityEntity::getEndTime, addDTO.getEndTime())
                            .set(ActivityEntity::getValidTime, addDTO.getValidTime())
                            .eq(ActivityEntity::getId, addDTO.getId())
            );
            responseDTO = ResponseDTO.succ();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return responseDTO;
    }

    @Override
    public List<ActivityVO> getOngoingActivities() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ge("end_time", new Date());
        return (List<ActivityVO>) activityDao.selectList(queryWrapper);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO<String> deleteByIds(ValidateList<Long> idList) {
        activityDao.deleteBatchIds(idList);
        return ResponseDTO.succ();
    }

    @Override
    public List<ActivityExcelVO> queryBatchExportData(ValidateList<Long> idList) {
        return activityDao.queryBatchExportData(idList);
    }

    @Override
    public List<ActivityExcelVO> queryAllExportData(ActivityQueryDTO queryDTO) {
        return activityDao.queryAllExportData(queryDTO);
    }
}
