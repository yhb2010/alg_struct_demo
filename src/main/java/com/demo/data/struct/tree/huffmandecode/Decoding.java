package com.demo.data.struct.tree.huffmandecode;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.demo.data.struct.tree.huffman.Node;

public class Decoding {

	public static String decode2(Node<Character> root, String encodingStr) {
		StringBuffer decodeStr = new StringBuffer();
		char[] charArray = encodingStr.toCharArray();
		Node<Character> node = root;
		for (char c : charArray) {
			node = viewTree(node, c);
			if(node.getLeft() == null && node.getRight() == null){
				decodeStr.append(node.getData());
				node = root;
			}
        }
		return decodeStr.toString();
	}

	private static Node<Character> viewTree(Node<Character> node, Character c){
		if(c.equals('1')){
			return node.getRight();
		}else{
			return node.getLeft();
		}
	}

	public static String decode(Map<Character, String> letterCode, String encodingStr) {
        // 解码得到的字符串
        StringBuffer decodeStr = new StringBuffer();
        // 获得解码器
        Map<String, Character> decodeMap = getDecoder(letterCode);
        // 解码器键集合
        Set<String> keys = decodeMap.keySet();
        // 从最短的开始匹配之所以能够成功，是因为哈夫曼编码的唯一前缀性质
        // 临时的可能的键值
        String temp = "";
        // 改变temp值大小的游标
        int i = 1;
        while (encodingStr.length() > 0) {
            temp = encodingStr.substring(0, i);
            if (keys.contains(temp)) {
                Character character = decodeMap.get(temp);
                decodeStr.append(character);
                encodingStr = encodingStr.substring(i);
                i = 1;
            } else {
                i++;
            }
        }
        return decodeStr.toString();
    }

    /**
     * 获得解码器，也就是通过字母/编码对得到编码/字符对。
     *
     * @param letterCode
     * @return
     */
    private static Map<String, Character> getDecoder(Map<Character, String> letterCode) {
        Map<String, Character> decodeMap = new HashMap<String, Character>();
        Set<Character> keys = letterCode.keySet();
        for (Character key : keys) {
            String value = letterCode.get(key);
            decodeMap.put(value, key);
        }
        return decodeMap;
    }

}
