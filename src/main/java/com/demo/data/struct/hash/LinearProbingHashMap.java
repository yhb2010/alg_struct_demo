package com.demo.data.struct.hash;

//使用线性探测法处理碰撞
public class LinearProbingHashMap<K, V> {
    private int num; //散列表中的键值对数目
    private int capacity;
    private K[] keys;
    private V[] values;

    public LinearProbingHashMap(int capacity) {
        keys = (K[]) new Object[capacity];
        values = (V[]) new Object[capacity];
        this.capacity = capacity;
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public V get(K key) {
        int index = hash(key);
        while (keys[index] != null && !key.equals(keys[index])) {
            index = (index + 1) % capacity;
        }
        return values[index]; //若给定key在散列表中存在会返回相应value，否则这里返回的是null
    }

    public void put(K key, V value) {
        int index = hash(key);
        while (keys[index] != null && !key.equals(keys[index])) {
            index = (index + 1) % capacity;
        }
        if (keys[index] == null) {
            keys[index] = key;
            values[index] = value;
            return;
        }
        values[index] = value;
        num++;
    }

    public void addCapacity(){
    	if (num == capacity / 2) {
            resize(2 * capacity);
        }
    }

    private void resize(int newCapacity) {
        LinearProbingHashMap<K, V> hashmap = new LinearProbingHashMap<>(newCapacity);
        for (int i = 0; i < capacity; i++) {
            if (keys[i] != null) {
                hashmap.put(keys[i], values[i]);
            }
        }
        keys  = hashmap.keys;
        values = hashmap.values;
        capacity = hashmap.capacity;
    }
}