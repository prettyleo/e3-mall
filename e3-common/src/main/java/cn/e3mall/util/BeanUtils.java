package cn.e3mall.util;

import cn.e3mall.annotation.DiffName;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * FileName: BeanUtils
 * DESCRIPTION: 对象转换工具类
 *
 * @author: Liyou Shen
 * @create: 2019/1/13
 */
public class BeanUtils {


    /**
     * 不同对象的属性复制
     * @param source 源对象
     * @param targetClass 目标对象class类型
     * @param <T> 目标对象泛型
     * @return
     */
    public static<T> T a2b(Object source, Class<T> targetClass) {
        if (ObjectUtils.isEmpty(source)) {
            return null;
        }

        T t = null;
        try {
            t = targetClass.newInstance();
            org.springframework.beans.BeanUtils.copyProperties(source, t);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     *
     * @param source 源对象
     * @param targetClass 目标对象class类型
     * @param <T> 目标对象泛型
     * @return
     */
    public static<T> T a2bDiff(Object source, Class<T> targetClass) {
        if (ObjectUtils.isEmpty(source)) {
            return null;
        }

        // 将同名属性先复制
        T t = a2b(source, targetClass);

        // 处理不同名属性
        Field[] fields = t.getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                DiffName diffName = field.getAnnotation(DiffName.class);
                if (diffName != null) {
                    String sourceFieldName = diffName.value();
                    Field sourceField = source.getClass().getDeclaredField(sourceFieldName);
                    Object value = sourceField.get(source);
                    field.set(t, value);
                }
                field.setAccessible(false);
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return t;
    }


    /**
     * 不同泛型的集合转换
     * @param srcList 源集合
     * @param targetClass 目标集合
     * @param <T> 目标集合泛型
     * @return
     */
    public static<T> List<T> a2bList(List srcList, Class<T> targetClass) {
        if (ObjectUtils.isEmpty(srcList)) {
            return null;
        }

        List<T> list = new ArrayList<>();
        try {
            T t = targetClass.newInstance();
            for (Object srcObj : srcList) {
                org.springframework.beans.BeanUtils.copyProperties(srcObj, t);
                list.add(t);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return list;
    }


    /**
     * 集合复制, 属性名不同
     * @param srcList 源集合
     * @param targetClass 目标集合泛型class对象
     * @param <T> 目标集合泛型
     * @return
     */
    public static<T> List<T> a2bListDiff(List srcList, Class<T> targetClass) {
        if (ObjectUtils.isEmpty(srcList)) {
            return null;
        }

        List<T> list = new ArrayList<>();
        for (Object source : srcList) {
            T t = a2bDiff(source, targetClass);
            list.add(t);
        }
        return list;
    }

}
