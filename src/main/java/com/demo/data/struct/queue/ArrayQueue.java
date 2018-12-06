package com.demo.data.struct.queue;

import java.util.Arrays;

/**
 * 基于数组实现队列。每次出队都要数组元素移位，每次入队要判断数组空间，不够要扩容。
 */
public class ArrayQueue<E> {
    E[] queue;
    int size;

    @SuppressWarnings("unchecked")
	public ArrayQueue() {
        queue = (E[])new Object[10];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public E poll() {
        if (isEmpty()) return null;
        E data = queue[0];
        System.arraycopy(queue, 1, queue, 0, size-1);
        size--;
        return data;
    }

    private void ensureCapacity(int size) {
        if (size > queue.length) {
            int len = queue.length + 10;
            queue = Arrays.copyOf(queue, len);
        }
    }

    public void offer(E data) {
        ensureCapacity(size+1);
        queue[size++] = data;
    }

    public static void main(String[] args) {
        ArrayQueue<Integer>  queue = new ArrayQueue<>();

        for (int i = 0; i < 20; i++) {
            queue.offer(i);
        }

        for (int i = 0; i < 20; i++) {
            System.out.println(queue.poll());
        }
    }
}