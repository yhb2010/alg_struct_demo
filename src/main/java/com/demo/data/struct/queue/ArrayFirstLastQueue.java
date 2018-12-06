package com.demo.data.struct.queue;

/**
 * 基于数组实现队列。使用头尾指针，这样无需移动数组元素。
 */
public class ArrayFirstLastQueue<E> {

	private static final int MAX_SIZE = 100;
	private E[] queue; // 队列
	private int front; // 头指针
	private int rear; // 尾指针
	private int length; // 队列初始化长度

	public ArrayFirstLastQueue(int length) {
		this.length = length;
		init();
	}

	public ArrayFirstLastQueue() {
		this.length = MAX_SIZE;
		init();
	}

	// 初始化队列
	@SuppressWarnings("unchecked")
	private void init() {
		queue = (E[])new Object[this.length + 1];
		front = rear = 0;
	}

	// 入队
	public void put(E object) throws Exception {
		if (isFull()) {
			throw new Exception("入队失败!队列已满!");
		}
		queue[rear] = object;
		rear = (rear + 1) % queue.length;
	}

	// 出队
	public E get() throws Exception {
		if (isEmpey()) {
			throw new Exception("出队失败!队列为空!");
		}
		E object = queue[front];
		queue[front] = null; // 释放对象
		front = (front + 1) % queue.length;
		return object;
	}

	// 清空队列
	public void clear() {
		init();
	}

	// 获得队列当前大小
	public int size() {
		return (rear - front + queue.length) % queue.length;
	}

	// 判断队列是否已满
	public boolean isFull() {
		return (rear + 1) % queue.length == front;
	}

	// 判断队列是否为空
	public boolean isEmpey() {
		return front == rear;
	}

	public void display(){
		for(int i=front; i<front + (rear-front+queue.length)%queue.length; i++){
			System.out.print(queue[i%queue.length]);
			System.out.print(" -> ");
		}
		System.out.println();
	}

	public static void main(String[] args) {
		ArrayFirstLastQueue<Character> queue = new ArrayFirstLastQueue<>(5);
		char[] data = new char[] { '沧', '海', '一', '声', '笑'};
		try {
			for (int i = 0; i < data.length; i++) {
				System.out.println("入队数据:" + data[i]);
				queue.put(data[i]);
			}
			System.out.println("出队数据:" + queue.get());
			System.out.println("出队数据:" + queue.get());
			queue.put('2');
			queue.put('3');
			queue.display();
			System.out.println("---------------------");
			while (!queue.isEmpey()) {
				System.out.println("出队数据:" + queue.get());
			}
			System.out.println("队空否?\t" + queue.isEmpey());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}