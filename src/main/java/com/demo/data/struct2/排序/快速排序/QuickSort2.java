package com.demo.data.struct2.排序.快速排序;

import com.demo.data.struct2.排序.Util;

/**
 * 优化算法：
1、优化选取枢轴
三数取中，即取三个关键字先进行排序，将中间数作为枢轴， 一般是取左端、右端和中间三个数， 也可以随机选取。
对于非常大的待排序的序列来说还是不足以保证能够选择出一个好的pivo tkey， 因此还有个办法是所谓的九数取中，先从数组中分三次取样，每次取三个数，三个样品各取出中数，然后从这三个中数当中再取出一个中数作为枢轴 。
 * @author DELL
 *
 */
public class QuickSort2 {

	public static void quickSort(int[] arr){
		qSort(arr, 0, arr.length-1);
    }

	//对顺序表子序列作快速排序     待排序序列的最小下标值low和最大下标值high
    public static void qSort(int[] arr, int low, int high){
        int pivot;
        if(low < high){
            pivot = partition(arr, low, high);//将数组子序列一分为二

            qSort(arr, low, pivot-1);//对低子表递归排序
            qSort(arr, pivot+1, high);//对高子表递归排序
        }
    }

	//选择一个关键字，想尽办法将它放到一个位置，使得它左边的值都比它小，
    //右边的值都比它大，我们称这个关键字叫枢轴。
    public static int partition(int[] arr, int low, int high){
        if(arr == null || low < 0 || high >= arr.length){
            new Exception();
        }

        int pivotkey;
        choosePivotkey(arr,low,high);//选取枢轴值
        pivotkey = arr[low];//选取第一个记录作枢轴记录

        while(low < high)//从表的两端向中间扫描
        {
            while(low < high && arr[high] >= pivotkey){//如果大于枢轴值，则下标减一，否则，跳出循环。
                high--;
            }
            if(low < high){
            	Util.swap(arr, low, high);//交换
            }
            while (low < high && arr[low] < pivotkey){//如果小于枢轴值，则下标加一，否则，跳出循环。
                low++;
            }
            if(low < high){
            	Util.swap(arr, low, high);//交换
            }
        }
        return low;
    }

    //三数取中 选择枢轴    将枢轴值调至第一个位置
	private static void choosePivotkey(int[] arr, int low, int high) {
		int mid = low + (int)(high-low)/2;
		if(arr[low]>arr[high]){//保证左端较小
			Util.swap(arr, low, high);
        }
        if(arr[mid]>arr[high]){//保证中间较小
        	Util.swap(arr, mid, high);
        }
        //此时最大值在最右边
        if(arr[mid]>arr[low]){//保证中间较小
        	Util.swap(arr, mid, low);
        }
	}

	public static void main(String[] args) {
        int[] arr = {48,15,24,59,64,79,97,40};
        quickSort(arr);
        for (int array : arr) {
            System.out.print(array+" ");
        }
        System.out.println();
    }

}
