package net.lab1024.smartadmin.util;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SmartBeanUtil {

    /**
     * 复制bean的属性
     *
     * @param source 源 要复制的对象
     * @param target 目标 复制到此对象
     */
    public static void copyProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target);
    }

    /**
     * 复制对象
     *
     * @param source 源 要复制的对象
     * @param target 目标 复制到此对象
     * @param <T>
     * @return
     */
    public static <T> T copy(Object source, Class<T> target) {
        if(source == null || target == null){
            return null;
        }
        try {
            T newInstance = target.newInstance();
            BeanUtils.copyProperties(source, newInstance);
            return newInstance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 复制list
     *
     * @param source
     * @param target
     * @param <T>
     * @param <K>
     * @return
     */
    public static <T, K> List<K> copyList(List<T> source, Class<K> target) {

        if (null == source || source.isEmpty()) {
            return Collections.emptyList();
        }
        return source.stream().map(e -> copy(e, target)).collect(Collectors.toList());
    }

    /**
     * 将PageInfo对象泛型中的Po对象转化为Vo对象
     *
     * @param pageInfoPo PageInfo<Po>对象，就是entity对象！</>
     * @param <V>        V类型
     * @return
     */
    public static <P,V> IPage<V> pageVoCovert(IPage<P> pageInfoPo, Class<V> v) {
        // 创建Page对象，实际上是一个ArrayList类型的集合
        try {
            if (pageInfoPo != null) {

                IPage<V> page = new Page<>(pageInfoPo.getCurrent(), pageInfoPo.getSize());
                page.setTotal(pageInfoPo.getTotal());
                List<P> records = pageInfoPo.getRecords();
                List<V> list = new ArrayList<>();
                for (P record : records) {
                    if(record!=null){
                        V newV = v.newInstance();
                        // 把原对象数据拷贝到新的对象
                        BeanUtil.copyProperties(record,newV);
                        list.add(newV);
                    }
                }
                page.setRecords(list);
                page.setTotal(pageInfoPo.getTotal());
                return page;
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
