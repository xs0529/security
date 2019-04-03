package com.xs.example.demo.common.tree;

import lombok.Data;

import java.util.List;

/**
 * @author xieshuang
 * @date 2019-04-03 20:25
 */
@Data
public class TreeNode2 extends BaseTree{

    private Integer key;
    private Integer pid;
    private String label;
    private List<TreeNode2> children;

    @Override
    public void setBaseTrees(Object baseTrees) {
        children = (List<TreeNode2>) baseTrees;
    }
}
