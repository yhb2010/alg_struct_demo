package com.demo.data.struct2.排序;

public class InsertSort {

	public static int[] sort(int[] arr) {
		for(int i=1; i<arr.length; i++){
			int j = i;
			while(j>0 && arr[j] < arr[j-1]){
				Util.swap(arr, j, j-1);
				j--;
			}
		}
		return arr;
	}

	public static void main(String[] args) {
		int[] arr = {9,3,1,4,2,7,8,6,5};
		Util.display(InsertSort.sort(arr));
	}

}
