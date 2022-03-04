package com.daryl.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * List转换工具类
 *
 * @author tz
 * @create 2020-12-21
 */
public class ListTransUtil {

    /**
     * 将Object转换成List
     *
     * @param obj   Object
     * @param clazz List中的数据类型
     * @return List
     */
    public static <T> List<T> castList(Object obj, Class<T> clazz) {
        List<T> result = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                result.add(clazz.cast(o));
            }
            return result;
        }
        return result;
    }

    /**
     * 将Object转换成List
     *
     * @param obj   Object
     * @param clazz List中的数据类型
     * @return List
     */
    public static <T> List<List<T>> castTwoList(Object obj, Class<T> clazz) {
        List<List<T>> result = new ArrayList<>();
        if (obj instanceof List<?>) {
            for (Object o : (List<?>) obj) {
                if (o instanceof List<?>) {
                    List<T> data = new ArrayList<>();
                    for (Object s : (List<?>) o) {
                        data.add(clazz.cast(s));
                    }
                    result.add(data);
                }
            }
        }
        return result;
    }
}
