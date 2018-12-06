package com.demo.data.struct.sequencelist;

import java.util.Arrays;
import java.util.Date;

/**
 * 顺序表： 线性表的顺序表是，指的是用一组地址连续的存储单元一次存储线性表的数据元素。以元素在计算机内“物理位置相邻”来表示线性表中数据元素之间的逻辑关系。只要确定了存储线性表的起始位置，线性表中任何一数据元素都可以随机存取，所以线性表的存储结构是一种随机存取的存储结构。
由于高级程序设计语言中的数组类型也具有随机存取的特性，因此，通常都用数组来描述数据结构中的顺序存储结构。
优点——读取数据的速度快
缺点——当需要增加、删除数据时慢
 * @author DELL
 *
 */
public class SequenceList<E> {

	private E[] data = null; //data 用来保存此线性表的数据域
    private int length; //线性表的容量
    private int current; //实际表长

    /**
     * 默认将大小设置为10
     */
    public SequenceList(){
        this(10);
    }

    /**
     * 初始化线性表，声明数组大小
     * @param initialSize 数组大小
     */
    @SuppressWarnings("unchecked")
	public SequenceList(int initialSize){
        if(initialSize >= 0){
            this.length = initialSize; //设置线性表容量
            this.data = (E[])new Object[initialSize]; //初始化数组
            this.current = 0; //下标设置为0
        }else {
            throw new RuntimeException("初始化大小不能小于0:" + initialSize); //异常提示
        }
    }

    /**
     * 在线性表末尾添加元素,添加之前判断线性表是否已经满了
     * @param e 添加的元素
     * @return 成功返回真
     */
    public boolean add(E e){
        //判断是否已满
        ensureCapacity();
        //将元素添加到数组末尾
        this.data[current++] = e;
        return true;
    }

    /**
     * 删除指定位置的元素
     * @param index
     * @return
     */
    public boolean removeToIndex(int index){
        //删除数组的元素：使用改变数组下标的方式达到删除的效果。
        //遍历数组匹配指定下标，让指定下标右边的元素往左移动改变下标。最后再将最右边的下标删除
        //从待删除下标处开始遍历，将右边的元素往左移
        if(index >= current){  //如果index大于最大长度，返回假
            System.err.print(new Date() + ": 下标超出表长");
            return false;
        }
        for (int i = index; i < current - 1; i++) {
            data[i] = data[i+1]; //该表元素下标
        }
        data[current-1] = null;  //将原来下标最右边的一位元素变成null
        --current;  //实际表长-1
        return true;
    }


    /**
     * 根据下标返回元素值
     * @param index
     * @return
     */
    public E get(int index){
        if(index >= 0){
            return data[index];
        }else {
            throw new RuntimeException("下标不能小于0:" + index);
        }
    }

    /**
     * 判断表容量是否超出预定大小，如果超出将自动扩充容量
     *
     */
    public void ensureCapacity(){
        //判断表实际长度是否超出表最大容量
        if(current >= length){
            length *= 2; //将表最大容量*2
            data = Arrays.copyOf(data, length);  //将原数组进行拷贝
        }
    }

    /**
     * 返回顺序表实际表长
     * @return
     */
    public int size(){
        return this.current;
    }

    /**
     * 返回表容量
     * @return
     */
    public int length(){
        return this.length;
    }

    /**
     *
     * 判断表是否为空
     * @param args
     */
    public boolean isEmpty(){
        return current == 0; //如果current == 0，说明为空返回真，否则返回假
    }

    //主方法测试
    public static void main(String[] args) {
    	SequenceList<Integer> list = new SequenceList<>(); //创建arrayList
        for (int i = 1; i <= 22; i++) {
            list.add(i);
        }
        list.removeToIndex(0);
        //遍历list数组
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

    }

}
