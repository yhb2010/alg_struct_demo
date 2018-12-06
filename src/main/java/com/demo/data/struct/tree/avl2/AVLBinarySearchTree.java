package com.demo.data.struct.tree.avl2;

/**
 * 带有平衡条件的二叉查找树
 * */
public class AVLBinarySearchTree<T extends Comparable<T>> {

    /**
     * 计算节点的高度
     * @param t 输入子树
     * @return 返回树的高度
     */
    public int height(TreeNode<T> t){
        return t==null ? -1 : t.height;
    }

    /**
     * 给树中添加节点
     * @param data 要插入的节点值
     * @param t 要插入的子树
     * @return 插入后形成的新的树
     */
    public TreeNode<T> insert(T data, TreeNode<T> t){
        if(t == null){
            //树为空
            return new TreeNode<T>(data, null ,null);
        }
        int compareReslt = data.compareTo(t.data);
        if(compareReslt < 0){
            //插入的数小于节点数，放入左子树中
            t.lt = insert(data, t.lt) ; //递归插入左子树

            //插入后检查当前节点的左右子树是否平衡
            if(height(t.lt)-height(t.rt) == 2){
                if(data.compareTo(t.lt.data) < 0){
                    t = rotateRight(t); //单旋转
                }else if(data.compareTo(t.lt.data) > 0){
                    t = doubleWithLeftChild(t); //双旋转
                }
            }
        }else if(compareReslt > 0){
            //插入的数大于节点数，放入右子树中
            t.rt = insert(data, t.rt) ; //递归插入左子树

            //插入后检查当前节点的左右子树是否平衡
            if(height(t.rt)-height(t.lt) == 2){
                if(data.compareTo(t.rt.data) > 0){
                    t = rotateLeft(t); //单旋转
                }else if(data.compareTo(t.rt.data) < 0){
                    t = doubleWithRightChild(t); //双旋转
                }
            }
        }
        t.height = Math.max(height(t.lt), height(t.rt)) + 1;
        return t;
    }

    /**
     * 单旋转
     * @param t
     * @return
     */
    private TreeNode<T> rotateLeft(TreeNode<T> k2) {
    	System.out.println("绕"+k2.data+"左旋");
        TreeNode<T> k1 = k2.rt;
        k2.rt = k1.lt; //左子树的右节点介于左子树根节点和根节点之间，赋值给根节点的左子树
        k1.lt = k2; //将根节点赋值给左节点的右节点
        k2.height = Math.max(height(k2.lt), height(k2.rt)) + 1;
        k1.height = Math.max(height(k1.rt), k2.height) + 1;
        return k1;
    }
    private TreeNode<T> rotateRight(TreeNode<T> k2) {
    	System.out.println("绕"+k2.data+"右旋");
        TreeNode<T> k1 = k2.lt; //左子树的根节点赋给K1
        k2.lt = k1.rt; //左子树的右节点介于左子树根节点和根节点之间，赋值给根节点的左子树
        k1.rt = k2; //将根节点赋值给左节点的右节点
        k2.height = Math.max(height(k2.lt), height(k2.rt)) + 1;
        k1.height = Math.max(height(k1.lt), k2.height) + 1;
        return k1;
    }
    /**
     * 双旋转
     * @param t
     * @return
     */
    private TreeNode<T> doubleWithLeftChild(TreeNode<T> k3) {
        k3.lt = rotateLeft(k3.lt);
        return rotateRight(k3);
    }
    private TreeNode<T> doubleWithRightChild(TreeNode<T> k3) {
        k3.rt = rotateRight(k3.rt);
        return rotateLeft(k3);
    }

    /*中序遍历*/
    public void printTree(TreeNode<T> t){
        if(t != null){
            printTree(t.lt);
            System.out.print(t.data+"、");
            printTree(t.rt);
        }
    }

    public TreeNode<T> search(T target, TreeNode<T> startNode) {
        int compareResult = target.compareTo(startNode.data);

        if (compareResult == 0)
            return startNode;
        else if (startNode.rt != null && compareResult > 0)
            return search(target, startNode.rt);
        else if (startNode.lt != null && compareResult < 0)
            return search(target, startNode.lt);
        else
            return null;
    }

    /**
     * 删除方法
     * @param data
     */
    public TreeNode<T> remove(TreeNode<T> target, TreeNode<T> root) {
        if (target == null){
            throw new RuntimeException("data can\'t not be null ");
        }
        return remove(target.data, root);
    }

    /**
     * 删除操作
     * @param data
     * @param p
     * @return
     */
    private TreeNode<T> remove(T data, TreeNode<T> p){

        if(p == null)
            return null;

        int result=data.compareTo(p.data);

        //从左子树查找需要删除的元素
        if(result<0){
            p.lt=remove(data,p.lt);

            //检测是否平衡
            if(height(p.rt)-height(p.lt)==2){
            	TreeNode<T> currentNode=p.rt;
                //判断需要那种旋转
                if(height(currentNode.rt)>height(currentNode.lt)){
                    p=rotateLeft(p);//单旋转
                }else{
                    p=doubleWithRightChild(p);//双旋转
                }
            }

        }
        //从右子树查找需要删除的元素
        else if(result>0){
            p.rt=remove(data,p.rt);
            //检测是否平衡
            if(height(p.lt)-height(p.rt)==2){
            	TreeNode<T> currentNode=p.lt;
                //判断需要那种旋转
                if(height(currentNode.lt)>height(currentNode.rt)){
                    p=rotateRight(p);//单旋转
                }else{
                    p=doubleWithLeftChild(p);//双旋转
                }
            }
        }
        //已找到需要删除的元素,并且要删除的结点拥有两个子节点
        else if(p.rt!=null&&p.lt!=null){

            //寻找替换结点
            p.data=getSuccessorNode(p).data;

            //移除用于替换的结点
            p.rt = remove( p.data, p.rt );
        }
        else {
            //只有一个孩子结点或者只是叶子结点的情况
            p = (p.lt != null) ? p.lt : p.rt;
        }

        //更新高度值
        if(p != null)
            p.height = Math.max( height( p.lt ), height( p.rt ) ) + 1;
        return p;
    }

    /**
     * 获取一个节点的后继节点
     * @param node
     * @return
     */
    private TreeNode<T> getSuccessorNode(TreeNode<T> node) {
        if (node.rt == null) {// 没有右子树
            return null;
        }

        TreeNode<T> targetNode = node.rt;
        while (targetNode.lt != null) {// 找右子树的最左孩子，保证返回的节点一定没有左子树
            targetNode = targetNode.lt;
        }

        return targetNode;
    }

    public static void main(String[] args) {
        AVLBinarySearchTree<Integer> aVLBinarySearchTree = new AVLBinarySearchTree<Integer>();
        TreeNode<Integer> node = new TreeNode<Integer>(12);
        node = aVLBinarySearchTree.insert(17, node);
        node = aVLBinarySearchTree.insert(6, node);
        node = aVLBinarySearchTree.insert(3, node);
        node = aVLBinarySearchTree.insert(8, node);
        node = aVLBinarySearchTree.insert(15, node);
        node = aVLBinarySearchTree.insert(7, node);
        node = aVLBinarySearchTree.insert(10, node);
        aVLBinarySearchTree.printTree(node);

        System.out.println();
        int detele = 17;
        TreeNode<Integer> target = aVLBinarySearchTree.search(detele, node);
        System.out.println(target);

        node = aVLBinarySearchTree.remove(target, node);
        aVLBinarySearchTree.printTree(node);
        System.out.println();
        System.out.println(aVLBinarySearchTree.search(detele, node));
    }
}