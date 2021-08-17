package net.lab1024.smartadmin.module.business.peony.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.peony.dao.ActivityDao;
import net.lab1024.smartadmin.module.business.peony.domain.dto.ActivityAddDTO;
import net.lab1024.smartadmin.module.business.peony.domain.dto.ActivityQueryDTO;
import net.lab1024.smartadmin.module.business.peony.domain.entity.ActivityEntity;
import net.lab1024.smartadmin.module.business.peony.domain.vo.ActivityVO;
import net.lab1024.smartadmin.util.SmartBeanUtil;
import net.lab1024.smartadmin.util.SmartPageUtil;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

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
@Primary
public class ActivityServiceImpl extends ServiceImpl<ActivityDao, ActivityEntity> implements ActivityService {

    @Resource
    private ActivityDao activityDao;

    /**
     * 分页查询
     * @author 莫京
     * @date 2021-08-10 18:17:56
     */
    @Override
    public ResponseDTO<PageResultDTO<ActivityVO>> queryByPage(ActivityQueryDTO queryDTO) {
        Page page = SmartPageUtil.convert2QueryPage(queryDTO);
        IPage<ActivityVO> voList = activityDao.queryByPage(page, queryDTO);
        PageResultDTO<ActivityVO> pageResultDTO = SmartPageUtil.convert2PageResult(voList);
        return ResponseDTO.succData(pageResultDTO);
    }

    /**
     * 添加
     * @author 莫京
     * @date 2021-08-10 18:17:56
     */
    @Override
    public ResponseDTO<String> add(ActivityAddDTO addDTO) {
        ActivityEntity entity = SmartBeanUtil.copy(addDTO, ActivityEntity.class);
        activityDao.insert(entity);
        return ResponseDTO.succ();
    }

    @Override
    public List<ActivityVO> getOngoingActivities() {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.ge("end_time",new Date());
        return (List<ActivityVO>)activityDao.selectList(queryWrapper);
    }





//
//    /**
//     * 编辑
//     * @author 卓大
//     * @date 2020-04-06 18:17:56
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public ResponseDTO<String> update(PeonyUpdateDTO updateDTO) {
//        PeonyEntity entity = SmartBeanUtil.copy(updateDTO, PeonyEntity.class);
//        peonyDao.updateById(entity);
//        return ResponseDTO.succ();
//    }
//
//    /**
//     * 删除
//     * @author 卓大
//     * @date 2020-04-06 18:17:56
//     */
//    @Transactional(rollbackFor = Exception.class)
//    public ResponseDTO<String> deleteByIds(List<Long> idList) {
//        peonyDao.deleteByIdList(idList);
//        return ResponseDTO.succ();
//    }
//
//    /**
//     * 查询全部导出对象
//     * @author 卓大
//     * @date 2020-04-06 18:17:56
//     */
//    public List<PeonyExcelVO> queryAllExportData(PeonyQueryDTO queryDTO) {
//        return peonyDao.queryAllExportData( queryDTO);
//    }
//
//    /**
//     * 批量查询导出对象
//     * @author 卓大
//     * @date 2020-04-06 18:17:56
//     */
//    public List<PeonyExcelVO> queryBatchExportData(List<Long> idList) {
//        return peonyDao.queryBatchExportData(idList);
//    }
}
