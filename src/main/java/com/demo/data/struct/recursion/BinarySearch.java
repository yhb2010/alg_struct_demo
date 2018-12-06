package com.demo.data.struct.recursion;

//递归版二分查找算法
public class BinarySearch {

	public static int rank(int key, int[] arr, int start, int end) {
		if (start > end) {
			return -1;
		}
		int mid = start + (end - start) / 2;
		if (key < arr[mid]) {
			return rank(key, arr, start, mid - 1);
		} else if (key > arr[mid]) {
			return rank(key, arr, mid + 1, end);
		} else {
			return mid;
		}
	}

	public static void main(String[] args) {
		int arr[] = { 0, 1, 3, 5, 6, 7, 8, 8, 9 };
		System.out.println("resultPosition=" + rank(3, arr, 0, arr.length - 1));
	}

}
