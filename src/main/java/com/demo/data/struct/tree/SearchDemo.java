package com.demo.data.struct.tree;

//查找
public class SearchDemo<T extends Comparable<T>> {

	/**
     * 从某个节点开始搜索
     *
     * @param target 目标值
     * @param startSearchNode 开始搜索的节点
     * @return
     */
    public Node<T> search(T target, Node<T> startNode) {
        int compareResult = target.compareTo(startNode.data);

        if (compareResult == 0)
            return startNode;
        else if (startNode.right != null && compareResult > 0)
            return search(target, startNode.right);
        else if (startNode.left != null && compareResult < 0)
            return search(target, startNode.left);
        else
            return null;
    }

    /**
     * 查找数据所在节点
     *
     * @param target 目标数据
     * @return null或数据所在节点
     */
    public Node<T> searchNode(T target, Node<T> treeRoot) {
        if (treeRoot.data == null)
            return null;
        return search(target, treeRoot);
    }

    public static void main(String[] args) {
    	CreateTreeByArray<Integer> createTreeByArray = new CreateTreeByArray<Integer>();
        //默认按前序遍历输入，即第一个输入的是根节点，然后是左子树、右子树
        Object[] arrays = {56, 23, 98, 12, 54, 67, 99, null, null, 53, 55, null, 76, null, null, null, null, null, null, 45};
        Node<Integer> treeRoot = createTreeByArray.createTreeByArray(arrays, 0);

    	SearchDemo<Integer> search = new SearchDemo<>();
    	System.out.println(search.searchNode(67, treeRoot));
	}

}
