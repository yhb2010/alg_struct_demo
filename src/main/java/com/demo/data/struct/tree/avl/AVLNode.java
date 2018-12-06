package com.demo.data.struct.tree.avl;

/**
 * Created by zejian on 2016/12/25.
 * Blog : http://blog.csdn.net/javazejian [原文地址,请尊重原创]
 * 平衡二叉搜索树(AVL树)节点
 */
public class AVLNode<E> {

	E element;
    AVLNode<E> parent;
    AVLNode<E> left;
    AVLNode<E> right;
    int balance = Contants.EH;   //平衡因子，只能为1或0或-1

    public AVLNode(E element,AVLNode<E> parent){
        this.element = element;
        this.parent = parent;
    }

    public AVLNode(){}

    @Override
    public String toString() {
        return element+" BF="+balance;
    }
}