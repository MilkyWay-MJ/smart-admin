package net.lab1024.smartadmin.module.business.peony.controller;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import net.lab1024.smartadmin.common.controller.BaseController;
import net.lab1024.smartadmin.common.domain.PageResultDTO;
import net.lab1024.smartadmin.common.domain.ResponseDTO;
import net.lab1024.smartadmin.module.business.peony.domain.dto.ActivityAddDTO;
import net.lab1024.smartadmin.module.business.peony.domain.dto.ActivityQueryDTO;
import net.lab1024.smartadmin.module.business.peony.domain.vo.ActivityVO;

import net.lab1024.smartadmin.module.business.peony.service.Activity1Service;

import net.lab1024.smartadmin.util.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
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
@Api(tags = {"活动"})
public class ActivityController extends BaseController {

    @Autowired
    @Qualifier("activityServiceImpl")
    private Activity1Service activityService;

    /**
     * 关键词输入解析地址
     *
     * @param region  地区
     * @param address 搜索关键词
     * @return: java.util.Map
     * @Author: 莫京 2021/8/11
     */
    @GetMapping("/tmapA")
    public ResponseDTO<Map> getLocation(String region, String address, HttpServletResponse response) {
        Map data = new HashMap();
        String url = "http://apis.map.qq.com/ws/place/v1/suggestion/?key=L6ABZ-BIM65-36MIA-Q4CG5-N74OH-S7F46&region=" + region + "&keyword=" + address;
        String info = HttpUtils.doHttpPost(url, null, null, null, 5);
        Map<String, String> httpInfo = (Map<String, String>) JSON.parse(info);
        data.put("data", httpInfo);
        System.out.println("httpInfo = " + httpInfo);
        return ResponseDTO.succData(data, "tmapA方法调用成功");
    }

    /**
     * 逆地址解析（位置描述）
     *
     * @param location 经纬度（GCJ02坐标系），格式：location=lat<纬度>,lng<经度>, eg: location= 39.984154,116.307490
     * @return: java.util.Map
     * @Author: 莫京 2021/8/11
     */
    @GetMapping("/tmapL")
    public ResponseDTO<Map> getLongitudeAndLatitude(String location) {
        Map data = new HashMap();
        String url = "https://apis.map.qq.com/ws/geocoder/v1/?key=L6ABZ-BIM65-36MIA-Q4CG5-N74OH-S7F46&location=" + location;
        String info = HttpUtils.doHttpPost(url, null, null, null, 5);
        Map<String, String> httpInfo = (Map<String, String>) JSON.parse(info);
        data.put("data", httpInfo);
        System.out.println("httpInfo = " + httpInfo);
        return ResponseDTO.succData(data, "tmapL方法调用成功");
    }

    @ApiOperation(value = "分页查询活动",notes = "@author 莫京")
    @PostMapping("/activity/page/qu")
    public ResponseDTO<PageResultDTO<ActivityVO>> quByPage(@RequestBody ActivityQueryDTO queryDTO) {
        ResponseDTO<PageResultDTO<ActivityVO>> pageResultDTOResponseDTO = activityService.queryByPage(queryDTO);
        return pageResultDTOResponseDTO;
    }

    @ApiOperation(value = "添加活动",notes = "@author 莫京")
    @PostMapping("/activity/add")
    public ResponseDTO<String> add(@RequestBody @Validated ActivityAddDTO addTO){
        return activityService.add(addTO);
    }




//
//    @ApiOperation(value="修改牡丹花",notes = "@author 卓大")
//    @PostMapping("/peony/update")
//    public ResponseDTO<String> update(@RequestBody @Validated PeonyUpdateDTO updateDTO){
//        return peonyService.update(updateDTO);
//    }
//
//    @ApiOperation(value="批量删除牡丹花",notes = "@author 卓大")
//    @PostMapping("/peony/deleteByIds")
//    public ResponseDTO<String> delete(@RequestBody @Validated ValidateList<Long> idList) {
//        return peonyService.deleteByIds(idList);
//    }
//
//    @ApiOperation(value = "批量导出", notes = "@author 卓大")
//    @PostMapping("/peony/export/batch")
//    public void batchExport(@RequestBody @Validated ValidateList<Long> idList, HttpServletResponse response) {
//        //查询数据
//        List<PeonyExcelVO> peonyList = peonyService.queryBatchExportData(idList);
//        //导出操作
//        ExportParams ex = new ExportParams("牡丹花", "Sheet1");
//        Workbook workbook = ExcelExportUtil.exportExcel(ex, PeonyExcelVO.class, peonyList);
//        downloadExcel("牡丹花", workbook, response);
//    }
//
//    @ApiOperation(value = "导出全部", notes = "@author 卓大")
//    @PostMapping("/peony/export/all")
//    public void exportAll(@RequestBody @Validated PeonyQueryDTO queryDTO, HttpServletResponse response) {
//        //查询数据
//        List<PeonyExcelVO> peonyList = peonyService.queryAllExportData(queryDTO);
//        //导出操作
//        ExportParams ex = new ExportParams("牡丹花", "Sheet1");
//        Workbook workbook = ExcelExportUtil.exportExcel(ex, PeonyExcelVO.class, peonyList);
//        downloadExcel("牡丹花", workbook, response);
//    }

}
