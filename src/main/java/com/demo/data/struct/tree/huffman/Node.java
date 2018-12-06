package com.demo.data.struct.tree.huffman;

public class Node<T> implements Comparable<Node<T>> {
    private T data;
    private int weight;
    private Node<T> left;
    private Node<T> right;

    public Node(T data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "data:" + this.data + ",weight:" + this.weight + ";   ";
    }

    @Override
    public int compareTo(Node<T> o) {
        if (o.weight > this.weight) {
            return 1;
        } else if (o.weight < this.weight) {
            return -1;
        }
        return 0;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

}