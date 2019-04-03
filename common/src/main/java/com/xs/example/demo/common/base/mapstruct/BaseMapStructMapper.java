package com.xs.example.demo.common.base.mapstruct;

import com.xs.example.demo.common.tree.BaseTree;
import com.xs.example.demo.common.tree.TreeNode;

/**
 * @author xieshuang
 * @date 2019-04-03 21:28
 */
public interface BaseMapStructMapper<E extends BaseTree> {

    E create(TreeNode treeNode);
}
