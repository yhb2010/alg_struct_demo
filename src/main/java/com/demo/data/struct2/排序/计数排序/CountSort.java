package com.demo.data.struct2.排序.计数排序;

/**
计数排序的适用范围？
待排序的元素在某一个范围[MIN, MAX]之间。

计数排序的空间复杂度？
计数排序需要一个辅助空间，空间大小为O(MAX-MIN)，用来存储所有元素出现次数（“计数”）。

计数排序的关键步骤？
步骤一：扫描待排序数据arr[N]，使用计数数组counting[MAX-MIN]，对每一个arr[N]中出现的元素进行计数；
步骤二：扫描计数数组counting[]，还原arr[N]，排序结束；
 * @author DELL
 *
 */
public class CountSort {

	/**
	 * @param array
	 * @param k 最大值减最小值加1
	 * @param offset 偏移量(最小值+偏移量=0)
	 * @return
	 */
	private static int[] countSort(int[] array, int k, int offset) {
		int[] C = new int[k];// 构造C数组
		int length = array.length;// 获取A数组大小用于构造B数组
		int[] B = new int[length];// 构造B数组
		for (int i = 0; i < length; i++) {
			C[array[i] + offset] += 1;// 统计A中各元素个数，存入C数组
		}
		int index = 0;
		for(int i = 0; i < length;)//遍历A数组，构造B数组
        {
			while(C[index] > 0){
            	B[i++] = index - offset;
            	C[index]--;
            }
            index++;
        }
		return B;// 将排序好的数组返回，完成排序

	}

	public static void main(String[] args) {
		int[] A = new int[] { 2, 5, 3, 0, 2, 3, 15, 0, -1, -6, 20, 3, 10 };
		int[] B = countSort(A, 27, 6);
		for (int i = 0; i < A.length; i++) {
			System.out.print(B[i] + ", ");
		}
	}

}
