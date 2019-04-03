package com.xs.example.demo.common.tree;

import com.alibaba.fastjson.JSONObject;
import com.xs.example.demo.common.test.TestMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @author xieshuang
 * @date 2019-04-03 21:31
 */
public class Test {

    public static void main(String[] args) {

        int id = 1;
        List<TreeNode> treeNodeList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            TreeNode treeNode = new TreeNode();
            treeNode.setId(id++);
            treeNode.setPid(0);
            treeNode.setName(i+"一级菜单");
            treeNodeList.add(treeNode);
            for (int j = 0; j < 10; j++) {
                TreeNode treeNode1 = new TreeNode();
                treeNode1.setId(id++);
                treeNode1.setPid(treeNode.getId());
                treeNode1.setName(j+"二级菜单");
                treeNodeList.add(treeNode1);
                for (int k = 0; k < 10; k++) {
                    TreeNode treeNode2 = new TreeNode();
                    treeNode2.setId(id++);
                    treeNode2.setPid(treeNode1.getId());
                    treeNode2.setName(k+"三级菜单");
                    treeNodeList.add(treeNode2);
                    for (int l = 0; l < 10; l++) {
                        TreeNode treeNode3 = new TreeNode();
                        treeNode3.setId(id++);
                        treeNode3.setPid(treeNode2.getId());
                        treeNode3.setName(l+"四级菜单");
                        treeNodeList.add(treeNode3);
                    }
                }
            }
        }
        TreeUtil<TreeNode2> treeUtil = new TreeUtil(TestMapper.MAPPER);
        long millis1 = System.currentTimeMillis();
        List<TreeNode2> tree = treeUtil.createTree(treeNodeList);
        long millis2 = System.currentTimeMillis();
        System.out.println(JSONObject.toJSON(tree));
        long millis3 = System.currentTimeMillis();
        List<TreeNode> tree1 = TreeUtil.createDefaultTree(treeNodeList);
        long millis4 = System.currentTimeMillis();
        //System.out.println(JSONObject.toJSON(tree1));
        long millis5 = System.currentTimeMillis();
        System.out.println("生成树1 list用时："+ (millis2-millis1));
        System.out.println("生成树1 json用时："+ (millis2-millis1));
        System.out.println("生成树2 list用时："+ (millis4-millis3));
        System.out.println("生成树2 json用时："+ (millis5-millis4));
    }
}
