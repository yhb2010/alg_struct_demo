package com.demo.data.struct.tree.huffmandecode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.demo.data.struct.tree.huffman.HuffmanTree;
import com.demo.data.struct.tree.huffman.Node;

public class HuffmanTreeTest {

	public static void main(String[] args) {
		String oriStr = "Huffman codes compress data very effectively: savings of 20% to 90% are typical, "
                + "depending on the characteristics of the data being compressed. 中华崛起";
		//oriStr = "abaacc";
		List<Node<Character>> nodes = Stat.statistics2(oriStr.toCharArray());
		System.out.println(nodes);
        Node<Character> root = HuffmanTree.createTree(nodes);
        Map<Character, String> letterCode = new HashMap<Character, String>();
        Encoding.buildEncodingInfo(root, letterCode, "");
        System.out.println(letterCode);
        String encodingStr = Encoding.encode(letterCode, oriStr);
        System.out.println(encodingStr);
        System.out.println(Decoding.decode(letterCode, encodingStr));
        System.out.println(Decoding.decode2(root, encodingStr));
    }

}
