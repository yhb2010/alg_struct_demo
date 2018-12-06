package com.demo.data.struct.tree;

//删除
//1.被删除的节点只有左节点或者只有右节点，这种情况好办，因为节点在一条链上，没有分叉，就像处理链表一样把这个节点摘掉就行了。让它的父节点关联它的子节点，它的子节点关联它的父节点就完事。如果它没有父节点，说明它是根节点，直接将其子节点作为根节点就行。
//2.被删除的节点没有子节点，这种情况也很简单，它是叶子节点，直接置空，将其父节点对应的子节点也置空，就完事。
//3.被删除的节点有左右子节点。这种情况就有点麻烦了。
//这里需要了解两个概念，叫“前驱”和“后继”。分别是树中小于它的最大值和大于它的最小值，如果把树结构中的所有节点按顺序拍好的话，它的前驱和它的后继两个节点刚好在它左右紧挨着它。当一个节点被删除时，为了保证二叉树的结构不被破坏，要让它的前驱或者后继节点来代替它的位置，然后将它的前驱或者后继节点同样做删除操作。
//那么怎样找前驱或者后继呢。小于它的最大值，就是在树中在它左边最靠右的那个节点。同样，大于它的最小值，就是在树中在它右边最靠左的那个节点。当一个节点既有左子节点又有右子节点时，前驱就是它的左子节点的右子节点的右子节点...直到最右子节点；后继就是它的右子节点的左子节点的左子节点...直到最左子节点。
public class DeteleDemo<T extends Comparable<T>> {

	public class ParentNode<T>{
		Node<T> pNode;
		int leftOrRighr = -1;//要查找的节点是父节点的左子树还是右子树：0左1右
		public ParentNode(Node<T> pNode, int leftOrRighr) {
			super();
			this.pNode = pNode;
			this.leftOrRighr = leftOrRighr;
		}
		@Override
		public String toString() {
			return "ParentNode [pNode=" + pNode + ", leftOrRighr="
					+ leftOrRighr + "]";
		}
	}

	/**
     * 删除节点
     *
     * @param node 待删除节点
     */
    private void deleteNode(Node<T> node, Node<T> root) {
    	ParentNode<T> parentNode = getParentNode(node.data, root);
        // 如果按顺序排列好节点，它的前驱和后继就是这个序列上紧挨着它左右两侧的节点.

    	if(node.left == null && node.right == null){// 没有子节点
    		if (parentNode.leftOrRighr == 0) {
    			parentNode.pNode.left = null;
            } else if (parentNode.leftOrRighr == 1) {
            	parentNode.pNode.right = null;
            }
    	}else if (node.left != null && node.right == null) {// 只有左节点
    		if (parentNode.leftOrRighr == 0) {
    			parentNode.pNode.left = node.left;
            } else if (parentNode.leftOrRighr == 1) {
            	parentNode.pNode.right = node.left;
            }
        } else if (node.left == null && node.right != null) {// 只有右节点
        	if (parentNode.leftOrRighr == 0) {
    			parentNode.pNode.left = node.right;
            } else if (parentNode.leftOrRighr == 1) {
            	parentNode.pNode.right = node.right;
            }
        } else {// 有左右子节点
            Node<T> successorNode = getSuccessorNode(node);
            deleteNode(successorNode, root);//删除后继节点
            replaceNode(node, successorNode);
        }

        node = null;
    }

    private ParentNode<T> getParentNode(T data, Node<T> root){
        Node<T> current;
        int leftOrRighr = -1;//要查找的节点是父节点的左子树还是右子树：0左1右
        current = root;
        Node<T> parent = root;
        while(current != null){
        	int compareResult = data.compareTo(current.data);
            if(compareResult < 0){
                parent = current;
                current = current.left;
                leftOrRighr = 0;
            }else if(compareResult > 0){
                parent = current;
                current = current.right;
                leftOrRighr = 1;
            }else{
                if(current.data.compareTo(root.data) == 0){
                    return null;
                }
                else
                    return new ParentNode<T>(parent, leftOrRighr);
            }
        }
        return null;
    }

    /**
     * 获取一个节点的后继节点
     * @param node
     * @return
     */
    private Node<T> getSuccessorNode(Node<T> node) {
        if (node.right == null) {// 没有右子树
            return null;
        }

        Node<T> targetNode = node.right;
        while (targetNode.left != null) {// 找右子树的最左孩子，保证返回的节点一定没有左子树
            targetNode = targetNode.left;
        }

        return targetNode;
    }

    /**
     * 非相邻节点的替换逻辑(非相邻加粗!)
     * @param node 被替换节点
     * @param replaceNode 替换的节点
     */
    private void replaceNode(Node<T> node, Node<T> replaceNode) {
    	node.data = replaceNode.data;
    }

    public static void main(String[] args) {
    	CreateTreeByArray<Integer> createTreeByArray = new CreateTreeByArray<Integer>();
        //默认按前序遍历输入，即第一个输入的是根节点，然后是左子树、右子树
        Object[] arrays = {56, 23, 98, 12, 54, 67, 99, null, null, 53, 55, null, 76, null, null, null, null, null, null, 45};
        Node<Integer> treeRoot = createTreeByArray.createTreeByArray(arrays, 0);
        createTreeByArray.inOrderTraverse(treeRoot);
        System.out.println();

    	SearchDemo<Integer> search = new SearchDemo<>();
    	Node<Integer> node = search.searchNode(56, treeRoot);

    	DeteleDemo<Integer> detele = new DeteleDemo<>();
    	detele.deleteNode(node, treeRoot);

    	createTreeByArray.inOrderTraverse(treeRoot);
	}

}
