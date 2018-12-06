package com.demo.data.struct.index;

public class Index {
    public int key;
    public Object value;
    public int size;//分块存储数量

    @Override
    public String toString() {
        return "Index{" +
                "key=" + key +
                ", value=" + value +
                ", size=" + size +
                '}';
    }
}