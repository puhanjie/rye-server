package com.puhj.rye.common.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author puhanjie
 * @description 比较工具类
 * @create 2023-09-01
 */
public class ContrastUtil {

    /**
     * 对比新旧两集合中的元素,返回与新集合对比后,旧集合被移除的元素和新增的元素
     *
     * @param olds 旧集合
     * @param news 新集合
     * @return 被移除和新增的元素集合
     */
    public static Map<String, List<Integer>> getContrast(List<Integer> olds, List<Integer> news) {
        Map<String, List<Integer>> result = new HashMap<>();
        List<Integer> removes = new ArrayList<>();
        List<Integer> adds = new ArrayList<>();

        // 若比较的集合为null,则初始化为空数组
        if (olds == null) {
            olds = new ArrayList<>();
        }
        if (news == null) {
            news = new ArrayList<>();
        }

        // 被移除的值
        for (Integer item : olds) {
            if (!news.contains(item)) {
                removes.add(item);
            }
        }
        // 新增的值
        for (Integer item : news) {
            if (!olds.contains(item)) {
                adds.add(item);
            }
        }

        result.put("remove", removes);
        result.put("add", adds);
        return result;
    }

}
