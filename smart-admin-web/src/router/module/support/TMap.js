import maps from 'qqmap';
import { postAxios, getAxios, getDownloadAxios } from '@/lib/http';
// 腾讯地图
export function TMap() {
    return new Promise(resolve => {
        maps.init('L6ABZ-BIM65-36MIA-Q4CG5-N74OH-S7F46', () => {
            resolve(maps);
        });
    });
}
// 根据关键词搜索坐标信息
export function getLocation(params) {
    return getAxios(
        "http://192.168.13.240:10086/smart-admin-api/activity/tmapA",
        params,

    );

}
// 根据坐标信息获取位置描述
export function getDescribe(params) {
    return getAxios(
        "http://192.168.13.240:10086/smart-admin-api/activity/tmapL",
        params,

    );

}