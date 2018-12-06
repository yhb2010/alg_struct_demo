package com.demo.data.struct.tree.huffmandecode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.demo.data.struct.tree.huffman.Node;

public class Stat {

	public static Map<Character, Integer> statistics(char[] charArray) {
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        for (char c : charArray) {
            Character character = new Character(c);
            map.compute(character, (key, oldValue) -> oldValue == null ? 1 : oldValue + 1);
        }

        return map;
    }

	public static List<Node<Character>> statistics2(char[] charArray) {
		List<Node<Character>> nodes = new ArrayList<>();
        Map<Character, Integer> map = statistics(charArray);
        Iterator<Entry<Character, Integer>> it = map.entrySet().iterator();
        while(it.hasNext()){
        	Entry<Character, Integer> entry = it.next();
        	nodes.add(new Node<Character>(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

	public static void main(String[] args) {
		String oriStr = "Huffman codes compress data very effectively: savings of 20% to 90% are typical, "
                + "depending on the characteristics of the data being compressed. 中华崛起";
        Map<Character, Integer> statistics = statistics(oriStr.toCharArray());
        System.out.println(statistics);
	}

}
