package com.demo.data.struct2.排序.基数排序;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 举个栗子：
假设待排序的数组arr={72, 11, 82, 32, 44, 13, 17, 95, 54, 28, 79, 56}
 * 基数排序的两个关键要点：
（1）基：被排序的元素的“个位”“十位”“百位”，暂且叫“基”，栗子中“基”的个数是2（个位和十位）；
（2）桶：“基”的每一位，都有一个取值范围，栗子中“基”的取值范围是0-9共10种，所以需要10个桶（bucket），来存放被排序的元素；
基数排序的算法步骤为：
FOR (每一个基) {
	//循环内，以某一个“基”为依据
	第一步：遍历数据集arr，将元素放入对应的桶bucket
	第二步：遍历桶bucket，将元素放回数据集arr
}
更具体的，对应到上面的栗子，“基”有个位和十位，所以，FOR循环会执行两次。
 * @author DELL
 *
 */
public class Demo {

	public static void main(String[] args) {
        int[] data = {73, 22, 300, 93, 43, 1, 2910, 55, 14, 5, 28, 65, 1920, 39, 81, 200, 100};
        long start = System.currentTimeMillis();
        Demo.sort(data, 10, 4);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        int[] data2 = {73, 22, 300, 93, 43, 1, 2910, 55, 14, 5, 28, 65, 1920, 39, 81, 200, 100};
        long start2 = System.currentTimeMillis();
        Demo.sort2(data2, 10, 4);
        long end2 = System.currentTimeMillis();
        System.out.println(end2 - start2);
    }

	/**
	 * @param data
	 * @param d 表示最大的数有多少位
	 */
	private static void sort(int[] number, int radix, int d) {
		int n = 1;
		List<LinkedList<Integer>> temp = new ArrayList<>(radix); //列表的第一维表示可能的余数0-9
		for(int i = 0; i < radix; i++){
			temp.add(new LinkedList<Integer>());
		}
		int m = 1; //控制键值排序依据在哪一位
		while(m <= d) {
			for(int i = 0; i < number.length; i++){
				int index = ((number[i] / n) % radix);
				temp.get(index).addLast(number[i]);
			}
			int index = 0;
			for(int i = 0; i < radix; i++){
				LinkedList<Integer> list = temp.get(i);
				while(!list.isEmpty()){
					number[index++] = list.removeFirst();
				}
			}
			n *= radix;
            m++;
        }
	}

	public static void sort2(int[] data, int radix, int d) {
		// 缓存数组
		int[] tmp = new int[data.length];
		// buckets用于记录待排序元素的信息
		// buckets数组定义了max-min个桶
		int[] buckets = new int[radix];

		for (int i = 0, rate = 1; i < d; i++) {

			// 重置count数组，开始统计下一个关键字
			Arrays.fill(buckets, 0);
			// 将data中的元素完全复制到tmp数组中
			System.arraycopy(data, 0, tmp, 0, data.length);

			// 计算每个待排序数据的子关键字
			for (int j = 0; j < data.length; j++) {
				int subKey = (tmp[j] / rate) % radix;
				buckets[subKey]++;
			}

			for (int j = 1; j < radix; j++) {
				buckets[j] = buckets[j] + buckets[j - 1];
			}

			// 按子关键字对指定的数据进行排序
			for (int m = data.length - 1; m >= 0; m--) {
				int subKey = (tmp[m] / rate) % radix;
				data[--buckets[subKey]] = tmp[m];
			}
			rate *= radix;
		}
	}

}
