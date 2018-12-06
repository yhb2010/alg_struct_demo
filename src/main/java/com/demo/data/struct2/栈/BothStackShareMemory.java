package com.demo.data.struct2.栈;

//两栈共享空间
//大话数据结构中以合租房子为例，来比喻两栈共享空间这种结构，我想说得已经比较清楚了，在Java实现中，我们定义一个数组作为基准存储容器，两个栈，栈1的栈底是在数组的始端，栈2的栈底在数组的顶端，两个栈在push元素时候主键朝数组的中间靠拢，当栈1的栈顶top1+1=栈2 栈顶top2，那么此时栈满；
public class BothStackShareMemory {

	private Object[] array;//定义一个数组存储
    private int stackSize;//栈长度
    private int top1;//第一个栈的栈顶指针
    private int top2;//第2个栈的栈顶指针

    /**
     * 初始化构建栈
     */
    public BothStackShareMemory() {
        stackSize = 10;
        array = new Object[stackSize];
        top1 = -1;
        top2 = stackSize;//都是空栈
    }

    /**
     * 压栈
     *
     * @param stackNum
     * @param element
     * @return
     */
    public boolean push(int stackNum, Object element) {
        if (top1 + 1 == top2) {
            System.out.println("栈满");
            return false;
        }
        if (stackNum == 1) {
            top1++;
            array[top1] = element;
        } else {
            top2--;
            array[top2] = element;
        }
        return true;
    }

    /**
     * 弹栈
     *
     * @param stackNum
     * @return
     */
    public Object pop(int stackNum) {
        if(stackNum == 1){
        	if (top1 == -1) {
                System.out.println("栈为空");
                return null;
            }
        	return array[top1--];
        }else {
        	if (top2 == stackSize) {
        		System.out.println("栈为空");
        		return null;
        	}
            return array[top2++];
        }
    }

    /**
     * 判断是否为空
     *
     * @param i
     * @return
     */
    public boolean isEmpty(int i) {
        if (i == 1) {
            if (top1 == -1) {
                return true;
            } else {
                return false;
            }
        } else {
            if (top2 == stackSize) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * 获取栈顶元素
     *
     * @param i
     * @return
     */
    public Object peek(int i) {
        if (i == 1) {
            if(top1 == -1){
                System.out.println("栈为空");
                return null;
            }
            return array[top1];
        } else {
            if(top2 == stackSize){
                System.out.println("栈为空");
                return null;
            }
            return array[top2];
        }
    }


    public static void main(String[] args) {
        BothStackShareMemory memory = new BothStackShareMemory();
        memory.push(1, 111);
        System.out.println(memory.pop(1));
        memory.push(2, 222);
        System.out.println(memory.pop(2));
        System.out.println(memory.isEmpty(1));
        System.out.println(memory.isEmpty(2));
    }

}
