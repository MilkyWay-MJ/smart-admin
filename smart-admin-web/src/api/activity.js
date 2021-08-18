import { postAxios, getAxios, postDownloadAxios } from '@/lib/http';

export const activityApi = {
    // 新增和修改是一样的接口，后端根据是id是否有值判断是哪种情况
    // 添加活动 @author 莫京
    addActivity: (data) => {
        return postAxios('/activity/save', data);
    },
    // 分页查询活动 @author 莫京
    queryActivity: (data) => {
        return postAxios('/activity/page/query', data);
    },
    // 批量删除活动 @author 莫京
    batchDeleteActivity: (idList) => {
        return postAxios('/activity/deleteByIds', idList);
    },
    // 修改活动  @author 莫京
    updateActivity: (data) => {
        return postAxios('/activity/save', data);
    },
    // 导出全部  @author 莫京
    exportAll: (data) => {
        return postDownloadAxios('/activity/export/all', data);
    },
    // 批量导出  @author 莫京
    batchExport: (idList) => {
        return postDownloadAxios('/activity/export/batch', idList);
    },
};