package com.demo.data.struct.queue;

/**
 * 基于链表实现队列
 */
public class ListQueue<E> {

    @SuppressWarnings("hiding")
	private class Data<E>{
        private E obj;
        private Data<E> next = null;

        Data(E obj){
            this.obj = obj;
        }
    }

    private Data<E> head = null;
    private Data<E> tail = null;

    public boolean isEmpty() {
        return head == null;
    }

    public void offer(E e) {
    	Data<E> node = new Data<>(e);
        if (isEmpty()) {
            head = node;
            tail = node;
            return;
        }
        tail.next = node;
        tail = node;
    }

    public E poll() {
        if (isEmpty())
        	return null;
        E data = head.obj;
        head = head.next;
        if(head == null)
        	tail = null;
        return data;
    }

    public int size() {
    	Data<E> temp = head;
        int len = 0;
        while (temp != null) {
            len++;
            temp = temp.next;
        }
        return len;
    }

    public static void main(String[] args) {
        ListQueue<String> queue = new ListQueue<>();
        System.out.println(queue.isEmpty());
        queue.offer("a");
        queue.offer("b");

        System.out.println(queue.poll());
        System.out.println(queue.poll());
        System.out.println(queue.isEmpty());
    }
}