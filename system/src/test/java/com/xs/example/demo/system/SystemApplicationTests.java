package com.xs.example.demo.system;

import com.xs.example.demo.common.tree.TreeNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SystemApplicationTests {

    @Test
    public void contextLoads() {
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
    }

}
