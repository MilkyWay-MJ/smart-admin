package net.lab1024.smartadmin.module.business.peony.service;

import com.baomidou.mybatisplus.extension.service.IService;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
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
public interface Activity1Service extends IService<ActivityEntity> {

    ResponseDTO<PageResultDTO<ActivityVO>> queryByPage(ActivityQueryDTO queryDTO);
    ResponseDTO<String> add(ActivityAddDTO addDTO);
}
