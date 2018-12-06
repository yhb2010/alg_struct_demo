package com.demo.data.struct2.排序;

public class Util {

	public static void display(int[] arr){
		for(int num:arr){
	    	System.out.print(num + " ");
	    }
	    System.out.println();
	}

	public static void swap(int[] arr, int i, int j){
		int temp = arr[i];
    	arr[i] = arr[j];
    	arr[j] = temp;
	}

}
