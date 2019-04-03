package com.xs.example.demo.common.test;

import com.xs.example.demo.common.base.mapstruct.BaseMapStructMapper;
import com.xs.example.demo.common.tree.TreeNode;
import com.xs.example.demo.common.tree.TreeNode2;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;


@Mapper
public interface TestMapper extends BaseMapStructMapper<TreeNode2> {

    public static TestMapper MAPPER = Mappers.getMapper(TestMapper.class);

    @Override
    @Mappings({
            @Mapping(source = "id", target = "key"),
            @Mapping(source = "name", target = "label")
    })
    TreeNode2 create(TreeNode treeNode);
}
