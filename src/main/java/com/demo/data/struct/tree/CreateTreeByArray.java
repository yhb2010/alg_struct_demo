package com.demo.data.struct.tree;

class Node<E>{
    Node<E> left = null;  //左子树
    Node<E> right = null; //右子树
    E data = null;          //数据域

    //初始化节点
    public Node(E data){
        this.data = data;
        this.left = null;
        this.right = null;
    }
    public Node(){

    }
	@Override
	public String toString() {
		return "Node [data=" + data + "]";
	}

}

//二叉树是一种非线性数据结构，属于树结构，最大的特点就是度为2，也就是每个节点只有一个左子树和一个右子树。
//二叉树的操作主要为创建，前序遍历（根、左、右），中序遍历（左、根、右），后序遍历（左、右、根）。还有层次遍历。
//遍历有两种方式，一是采用递归的方式，二是采用转换为栈进行遍历，对二叉树的遍历本质上市将非线性结构转换为线性序列。
public class CreateTreeByArray<E> {

    //将数组转化为一颗二叉树，转换规则：依次为节点列表中节点的左右孩子赋值。左孩子为i*2+1,右孩子为i*2+2
    @SuppressWarnings("unchecked")
    public Node<E> createTreeByArray(Object[] array, int index){
    	Node<E> node = null;

        //为二叉树指针赋值
    	if(index < array.length) {
    		if(array[index] != null){
	    		node = new Node<E>((E)array[index]);
	            node.left = createTreeByArray(array, index * 2 + 1);
	            node.right = createTreeByArray(array, index * 2 + 2);
    		}
        }

    	return node;
    }

    //前序遍历二叉树
    public void indorder(Node<E> root){
        if(root == null){
            return;
        }
        System.out.print(root.data + " -> ");
        indorder(root.left);  //递归输出左子树
        indorder(root.right); //递归遍历右子树
    }

    //中序遍历二叉树
    public void inOrderTraverse(Node<E> root){
        if(root == null){
            return;
        }
        inOrderTraverse(root.left);  //遍历左子树
        System.out.print(root.data + " -> ");
        inOrderTraverse(root.right); //遍历右子树
    }

    //后序遍历
    public void postOrderTraverse(Node<E> root){
        if(root == null){
            return;
        }
        postOrderTraverse(root.left);  //遍历左子数节点
        postOrderTraverse(root.right); //遍历右子树节点
        System.out.print(root.data + " -> "); //从下往上遍历
    }


    public static void main(String[] args) {
        CreateTreeByArray<Integer> createTreeByArray = new CreateTreeByArray<Integer>();
        //默认按前序遍历输入，即第一个输入的是根节点，然后是左子树、右子树
        //Object[] arrays = {new Integer(1),new Integer(2),new Integer(3),new Integer(4),5,6,7,8,9,10};
        Object[] arrays = {1, 2, 3, null, 4, 5, null, null, null, 6, 7, null, null, null, null, null, null, null, null, 8, 9};
        Node<Integer> node = createTreeByArray.createTreeByArray(arrays, 0);
        System.out.println("=============前序==================");
        createTreeByArray.indorder(node);
    }

}
