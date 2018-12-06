package com.demo.data.struct.hash;

public class SeqSearchST<K, V> {
    private Node first;

    private class Node {
        K key;
        V val;
        Node next;
        public Node(K key, V val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
		@Override
		public String toString() {
			return "Node [key=" + key + ", val=" + val + ", next=" + next + "]";
		}
    }

    public V get(K key) {
        for (Node node = first; node != null; node = node.next) {
            if (key.equals(node.key)) {
                return node.val;
            }
        }
        return null;
    }

    public void put(K key, V val) {
        //先查找表中是否已存在相应key
        Node node;
        for (node = first; node != null; node = node.next) {
            if (key.equals(node.key)) {
                node.val = val;
                return;
            }
        }
        //表中不存在相应key
        first = new Node(key, val, first);
    }

}