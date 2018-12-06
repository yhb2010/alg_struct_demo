package com.demo.data.struct.tree.avl2;

public class TreeNode<T extends Comparable<T>> {

	public T data; //节点的值
	public TreeNode<T> lt; //节点左子树
	public TreeNode<T> rt; //节点右子树
	public int height; //用来记录节点的高度，进行单旋转或双旋转

    public TreeNode(T data) {
        this(data, null, null);
    }
    public TreeNode(T data, TreeNode<T> lt, TreeNode<T> rt) {
        this.data = data;
        this.lt = lt;
        this.rt = rt;
        this.height = 0;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    public TreeNode<T> getLt() {
        return lt;
    }
    public void setLt(TreeNode<T> lt) {
        this.lt = lt;
    }
    public TreeNode<T> getRt() {
        return rt;
    }
    public void setRt(TreeNode<T> rt) {
        this.rt = rt;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }

	@Override
	public String toString() {
		return "node [data=" + data + ", lt=" + lt + ", rt=" + rt + ", height=" + height + "]";
	}

}
