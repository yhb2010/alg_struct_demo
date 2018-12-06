package com.demo.data.struct.tree.huffman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HuffmanTree<T> {

	//构造
	//假设有n个权值，则构造出的哈夫曼树有n个叶子结点。 n个权值分别设为 w1、w2、…、wn，则哈夫曼树的构造规则为：
	//(1)将w1、w2、…，wn看成是有n棵树的森林(每棵树仅有一个结点)；
	//(2)在森林中选出两个根结点的权值最小的树合并，作为一棵新树的左、右子树，且新树的根结点权值为其左、右子树根结点权值之和；
	//(3)从森林中删除选取的两棵树，并将新树加入森林；
	//(4)重复(2)、(3)步，直到森林中只剩一棵树为止，该树即为所求得的哈夫曼树。
    public static <T> Node<T> createTree(List<Node<T>> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node<T> left = nodes.get(nodes.size() - 1);
            Node<T> right = nodes.get(nodes.size() - 2);
            Node<T> parent = new Node<T>(null, left.getWeight() + right.getWeight());
            parent.setLeft(left);
            parent.setRight(right);
            nodes.remove(left);
            nodes.remove(right);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    public static <T> List<Node<T>> breath(Node<T> root) {
        List<Node<T>> list = new ArrayList<Node<T>>();
        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node<T> pNode = queue.poll();
            list.add(pNode);
            if (pNode.getLeft() != null) {
                queue.add(pNode.getLeft());
            }
            if (pNode.getRight() != null) {
                queue.add(pNode.getRight());
            }
        }
        return list;
    }

}