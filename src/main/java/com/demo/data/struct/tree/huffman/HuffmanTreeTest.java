package com.demo.data.struct.tree.huffman;

import java.util.ArrayList;
import java.util.List;

public class HuffmanTreeTest {

	public static void main(String[] args) {
        List<Node<String>> nodes = new ArrayList<Node<String>>();
        nodes.add(new Node<String>("b", 13));
        nodes.add(new Node<String>("a", 12));
        nodes.add(new Node<String>("d", 11));
        nodes.add(new Node<String>("c", 10));
        Node<String> root = HuffmanTree.createTree(nodes);
        System.out.println(HuffmanTree.breath(root));
    }

}
