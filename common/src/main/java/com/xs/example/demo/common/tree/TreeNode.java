package com.xs.example.demo.common.tree;

import lombok.Data;

import java.util.List;

/**
 * @author xieshuang
 * @date 2019-04-03 20:25
 */
@Data
public class TreeNode {

    private Integer id;
    private Integer pid;
    private String name;
    private List<TreeNode> treeNodes;
}
