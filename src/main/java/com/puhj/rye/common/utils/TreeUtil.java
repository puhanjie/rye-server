package com.puhj.rye.common.utils;

import com.puhj.rye.bo.TreeNodeBO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author puhanjie
 * @description 树形数据生成工具类
 * @create 2023-09-03
 */
public class TreeUtil {

    public static <T extends TreeNodeBO> List<T> build(List<T> treeNodes) {
        List<T> trees = new ArrayList<>();
        List<Integer> treeNodeIds = treeNodes.stream().map(TreeNodeBO::getId).toList();
        for (T treeNode : treeNodes) {
            if (!treeNodeIds.contains(treeNode.getParentId())) {
                trees.add(treeNode);
            }
            for (T node : treeNodes) {
                if (node.getParentId().equals(treeNode.getId())) {
                    treeNode.addTreeNode(node);
                }
            }
        }
        return trees;
    }

}
