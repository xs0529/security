package com.xs.example.demo.common.tree;

import com.xs.example.demo.common.base.mapstruct.BaseMapStructMapper;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author xieshuang
 * @date 2019-04-03 20:28
 */
public class TreeUtil<E extends BaseTree> {

    private BaseMapStructMapper<E> baseMapStructMapper;

    public TreeUtil(){

    }

    public TreeUtil(BaseMapStructMapper baseMapStructMapper){
        this.baseMapStructMapper = baseMapStructMapper;
    }

    public void setBaseMapStructMapper(BaseMapStructMapper baseMapStructMapper){
        this.baseMapStructMapper = baseMapStructMapper;
    }

   public static List<TreeNode> createDefaultTree(List<TreeNode> treeNodes){
        List<TreeNode> treeNodeList = new ArrayList<>();
        Iterator<TreeNode> nodeIterator = treeNodes.iterator();
        while (nodeIterator.hasNext()){
            TreeNode next = nodeIterator.next();
            if (next.getPid()==0 || next.getPid()==null){
                treeNodeList.add(addDefaultChildNode(next, treeNodes));
            }
        }
        return treeNodeList;
    }

    public  List<E> createTree(List<TreeNode> treeNodes){
        List<E> eList = new ArrayList<>();
        Iterator<TreeNode> nodeIterator = treeNodes.iterator();
        while (nodeIterator.hasNext()){
            TreeNode next = nodeIterator.next();
            if (next.getPid()==0 || next.getPid()==null){
                eList.add(this.addChildNode(next, treeNodes));
            }
        }
        return eList;
    }

    private E addChildNode(TreeNode treeNode, List<TreeNode> treeNodes){
        List<E> eList = new ArrayList<>();
        E e = baseMapStructMapper.create(treeNode);
        Iterator<TreeNode> nodeIterator = treeNodes.iterator();
        while (nodeIterator.hasNext()){
            TreeNode next = nodeIterator.next();
            if (next.getPid()!=null && next.getPid().equals(treeNode.getId())){
                eList.add(addChildNode(next, treeNodes));
            }
        }
        e.setBaseTrees(eList);
        return e;
    }

    private static TreeNode addDefaultChildNode(TreeNode treeNode, List<TreeNode> treeNodes){
        List<TreeNode> treeNodeList = new ArrayList<>();
        Iterator<TreeNode> nodeIterator = treeNodes.iterator();
        while (nodeIterator.hasNext()){
            TreeNode next = nodeIterator.next();
            if (next.getPid()!=null && next.getPid().equals(treeNode.getId())){
                treeNodeList.add(addDefaultChildNode(next, treeNodes));
            }
        }
        treeNode.setTreeNodes(treeNodeList);
        return treeNode;
    }
}
