package com.demo.data.struct2.树;

//线索化
public class ThreadTree<AnyType extends Comparable<? super AnyType>> {

	private BinaryNode<AnyType> preNode;   //线索化时记录前一个节点

	private static class BinaryNode<AnyType> {
		BinaryNode(AnyType theElement) {
			this(theElement, null, null);
		}

		BinaryNode(AnyType theElement, BinaryNode<AnyType> lt, BinaryNode<AnyType> rt) {
			element = theElement;
			left = lt;
			right = rt;
		}


		@Override
		public String toString() {
			return "BinaryNode [element=" + element + ", left=" + left
					+ ", right=" + right + ", isLeftThread=" + isLeftThread
					+ ", isRightThread=" + isRightThread + "]";
		}

		AnyType element;
		BinaryNode<AnyType> left;
		BinaryNode<AnyType> right;
		boolean isLeftThread = false;   //左指针域类型  false：指向子节点、true：前驱或后继线索
        boolean isRightThread = false;  //右指针域类型  false：指向子节点、true：前驱或后继线索
	}

	private BinaryNode<AnyType> root;

	public void insert(AnyType x) {
		root = insert(x, root);
	}

	public boolean isEmpty() {
		return root == null;
	}

	private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
		if (t == null) {
			return new BinaryNode<>(x, null, null);
		}

		int compareResult = x.compareTo(t.element);

		if (compareResult < 0) {
			t.left = insert(x, t.left);
		} else if (compareResult > 0) {
			t.right = insert(x, t.right);
		} else {
			;
		}

		return t;
	}

	/**
	 * 中序线索化
	 */
	public void preOrder(BinaryNode<AnyType> Node) {
		if(Node == null) {
            return;
        }
        preOrder(Node.left);
		//左指针为空,将左指针指向前驱节点
        if(Node.left == null) {
        	Node.left = preNode;
        	Node.isLeftThread = true;
        }
        //前一个节点的后继节点指向当前节点
        if(preNode != null && preNode.right == null) {
            preNode.right = Node;
            preNode.isRightThread = true;
        }
        preNode = Node;
        preOrder(Node.right);
	}

	/**
     * 中序遍历线索二叉树（按照后继线索遍历）
     * @param node
     */
    void preThreadList(BinaryNode<AnyType> node) {
    	//1、找中序遍历方式开始的节点
        while(node != null && !node.isLeftThread) {
            node = node.left;
        }
        while(node != null) {
            System.out.print(node.element + ", ");

            //如果右指针是线索
            if(node.isRightThread) {
                node = node.right;

            } else {    //如果右指针不是线索，找到右子树开始的节点
                node = node.right;
                while(node != null && !node.isLeftThread) {
                    node = node.left;
                }
            }
        }
    }

    /**
     * 中序遍历线索二叉树，按照前驱方式遍历（思路：找到最右子节点开始倒序遍历）
     * @param node
     */
    void inPreThreadList(BinaryNode<AnyType> node) {
        //1、找最后一个节点
        while(node.right != null && !node.isRightThread) {
            node = node.right;
        }

        while(node != null) {
            System.out.print(node.element + ", ");

            //如果左指针是线索
            if(node.isLeftThread) {
                node = node.left;

            } else {    //如果左指针不是线索，找到左子树开始的节点
                node = node.left;
                while(node.right != null && !node.isRightThread) {
                    node = node.right;
                }
            }
        }
    }

	public static void main(String[] args) {
		double[] input = { 4, 2, 6, 1, 3, 5, 7, 8, 10, 3.5, 3.8 };
		ThreadTree<Double> tree = new ThreadTree<>();
		for (int i = 0; i < input.length; i++) {
			tree.insert(input[i]);
		}
		System.out.print("递归前序遍历 ：");
		tree.preOrder(tree.root);
		tree.preThreadList(tree.root);
		System.out.println("");
		tree.inPreThreadList(tree.root);
	}
}