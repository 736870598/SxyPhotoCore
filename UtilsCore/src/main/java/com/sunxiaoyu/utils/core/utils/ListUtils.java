package com.sunxiaoyu.utils.core.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * List 工具类
 * Created by Administrator on 2018/1/10 0010.
 */
public class ListUtils {

    /**
     * list是否为空
     */
    public static boolean isNull(List list){
        return (list == null || list.isEmpty());
    }

    /**
     * list是否不为空
     */
    public static boolean isNotNull(List list){
        return ! isNull(list);
    }

    /**
     * list -- 数组
     */
    public static Object[] list2StringArray(List list){
        if (isNull(list)){
            return null;
        }
        Object[] arrays = new Object[list.size()];
        list.toArray(arrays);
        return arrays;
    }

    /**
     * 数组 -- list
     */
    public static ArrayList stringArrat2List(Object[] arrays){
        return new ArrayList<>(Arrays.asList(arrays));
    }

}
