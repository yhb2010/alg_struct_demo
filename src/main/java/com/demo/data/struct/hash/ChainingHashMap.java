package com.demo.data.struct.hash;

//使用拉链法处理碰撞
public class ChainingHashMap<K, V>  {
    private int num; //当前散列表中的键值对总数
    private int capacity; //桶数
    private SeqSearchST<K, V>[] st; //链表对象数组

    public ChainingHashMap(int initialCapacity) {
        capacity = initialCapacity;
        st = new SeqSearchST[capacity];
        for (int i = 0; i < capacity; i++) {
            st[i] = new SeqSearchST<>();
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % capacity;
    }

    public V get(K key) {
        return st[hash(key)].get(key);
    }

    public void put(K key, V value) {
        st[hash(key)].put(key, value);
    }

    public static void main(String[] args) {
    	ChainingHashMap<Integer, String> hash = new ChainingHashMap<>(3);
    	hash.put(1, "a");
    	hash.put(2, "b");
    	hash.put(3, "c");
    	hash.put(4, "d");
    	hash.put(5, "e");
    	hash.put(6, "f");
    	System.out.println(hash.get(1));
	}

}