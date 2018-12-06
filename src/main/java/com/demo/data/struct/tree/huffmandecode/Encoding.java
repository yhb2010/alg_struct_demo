package com.demo.data.struct.tree.huffmandecode;

import java.util.Map;
import com.demo.data.struct.tree.huffman.Node;

public class Encoding {

	public static void buildEncodingInfo(Node<Character> rooNode, Map<Character, String> letterCode, String suffix) {
		if (rooNode != null) {
            if (rooNode.getLeft() == null && rooNode.getRight() == null) {
                Character character = rooNode.getData();
                letterCode.put(character, suffix);
            }
            buildEncodingInfo(rooNode.getLeft(), letterCode, suffix + "0");
            buildEncodingInfo(rooNode.getRight(), letterCode, suffix + "1");
        }
    }

	public static String encode(Map<Character, String> letterCode, String letters) {
        StringBuilder encode = new StringBuilder();
        for (int i = 0, length = letters.length(); i < length; i++) {
            Character character = letters.charAt(i);
            encode.append(letterCode.get(character));
        }
        return encode.toString();
    }

}
