package com.xs.example.demo.system;

import com.xs.example.demo.common.base.mapstruct.BaseMapStructMapper;
import com.xs.example.demo.common.tree.TreeNode;
import com.xs.example.demo.common.tree.TreeNode2;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

/**
 * @author xieshuang
 * @date 2019-04-03 21:43
 */
@Mapper(componentModel = "spring")
public interface TestMapper1 extends BaseMapStructMapper<TreeNode2> {

    @Override
    @Mappings({
        @Mapping(source = "id", target = "key"),
        @Mapping(source = "name", target = "label")
    })
    TreeNode2 create(TreeNode treeNode);
}