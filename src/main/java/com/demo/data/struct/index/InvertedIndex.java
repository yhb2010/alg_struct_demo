package com.demo.data.struct.index;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//倒排索引
public class InvertedIndex {

	public static List<String> invertedIndex(String[] array, String word) {
        HashMap<String, List<String>> lists = new HashMap<>();
        if (array != null && array.length > 0) {

            //建立索引
            for (int i = 0; i < array.length; i++) {
                String page = array[i];
                String[] words = page.split(" ");
                for (String str : words) {
                    if (lists.containsKey(str)) {
                        List<String> pages = lists.get(str);
                        pages.add(array[i]);
                    } else {
                        List<String> pages = new ArrayList<>();
                        pages.add(array[i]);
                        lists.put(str, pages);
                    }
                }
            }
            return lists.get(word);

        }
        return null;
    }

}
