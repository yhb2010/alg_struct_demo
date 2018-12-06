package com.demo.data.struct.tree;

//插入
public class InsertDemo<T extends Comparable<T>> {

	/**
     * 插入节点
     *
     * @param insertData 待插入的数据
     * @param node 开始比较的节点
     */
    private void insert(T insertData, Node<T> node) {

        int compareResult = insertData.compareTo(node.data);
        if (compareResult == 0)// 相等
            return;
        else if (compareResult > 0) {// 大于节点值
            if (node.right == null) {
                node.right = new Node<T>();
                node.right.data = insertData;// 插入值
                return;
            } else
                insert(insertData, node.right);// 继续对比右子节点
        } else {// 小于节点值
            if (node.left == null) {
                node.left = new Node<T>();
                node.left.data = insertData;// 插入值
                return;
            } else
                insert(insertData, node.left);// 继续对比左子节点
        }
    }

    /**
     * 插入节点
     *
     * @param insertData 待插入的数据
     */
    public void insertNode(T insertData, Node<T> treeRoot) {
        if (treeRoot.data == null) {
            treeRoot.data = insertData;
            return;
        }
        insert(insertData, treeRoot);
    }

    public static void main(String[] args) {
    	CreateTreeByArray<Integer> createTreeByArray = new CreateTreeByArray<Integer>();
        //默认按前序遍历输入，即第一个输入的是根节点，然后是左子树、右子树
        Object[] arrays = {56, 23, 98, 12, 54, 67, 99, null, null, 53, 55, null, 76, null, null, null, null, null, null, 45};
        Node<Integer> treeRoot = createTreeByArray.createTreeByArray(arrays, 0);

    	InsertDemo<Integer> insert = new InsertDemo<>();
    	insert.insertNode(65, treeRoot);

    	createTreeByArray.inOrderTraverse(treeRoot);
	}

}
