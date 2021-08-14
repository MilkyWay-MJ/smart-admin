package net.lab1024.smartadmin.module.business.peony.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import net.lab1024.smartadmin.module.business.peony.domain.dto.ActivityQueryDTO;
import net.lab1024.smartadmin.module.business.peony.domain.entity.ActivityEntity;
import net.lab1024.smartadmin.module.business.peony.domain.entity.SignEntity;
import net.lab1024.smartadmin.module.business.peony.domain.vo.ActivityVO;
import net.lab1024.smartadmin.module.business.peony.domain.vo.SignVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * [ 活动 ]
 *
 * @author 卓大
 * @version 1.0
 * @company 1024创新实验室( www.1024lab.net )
 * @copyright (c)  1024创新实验室( www.1024lab.net )Inc. All rights reserved.
 * @date 2020-04-06 18:17:56
 * @since JDK1.8
 */
@Mapper
@Component
public interface SignDao extends BaseMapper<SignEntity> {

//    /**
//     * 分页查询
//     * @param queryDTO
//     * @return ActivityVO
//    */
//    IPage<ActivityVO> queryByPage(Page page, @Param("queryDTO") ActivityQueryDTO queryDTO);

//    /**
//     * 根据id删除
//     * @param id
//     * @return
//    */
//    void deleteById(@Param("id") Long id);
//
//    /**
//     * 根据id批量删除
//     * @param idList
//     * @return
//    */
//    void deleteByIdList(@Param("idList") List<Long> idList);
//
//        /**
//     * 查询所有导出数据
//     * @param queryDTO
//     * @return
//     */
//    List<PeonyExcelVO> queryAllExportData(@Param("queryDTO") PeonyQueryDTO queryDTO);
//
//        /**
//         * 查询批量导出数据
//         * @param idList
//         * @return
//         */
//    List<PeonyExcelVO> queryBatchExportData(@Param("idList") List<Long> idList);
}
