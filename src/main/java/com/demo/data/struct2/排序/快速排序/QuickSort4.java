package com.demo.data.struct2.排序.快速排序;

import com.demo.data.struct2.排序.Util;

/**
 * 3、优化小数组时的排序方案
快速排序适用于非常大的数组的解决办法， 那么相反的情况，如果数组非常小，其实快速排序反而不如直接插入排序来得更好(直接插入是简单排序中性能最好的)。其原因在于快速排序用到了递归操作，在大量数据排序时，这点性能影响相对于它的整体算法优势是可以忽略的，但如果数组只有几个记录需要排序时，这就成了大材小用，因此我们需要改进一下 QSort函数。
 *
 */
public class QuickSort4 {

	final static int MAX_LENGTH_INSERT_SORT = 7;

	public static void quickSort(int[] arr){
		qSort(arr, 0, arr.length-1);
    }

	//对顺序表子序列作快速排序     待排序序列的最小下标值low和最大下标值high
    public static void qSort(int[] arr, int low, int high){
        int pivot;
        if((high-low)>MAX_LENGTH_INSERT_SORT){
	        if(low < high){
	            pivot = partition(arr, low, high);//将数组子序列一分为二

	            qSort(arr, low, pivot-1);//对低子表递归排序
	            qSort(arr, pivot+1, high);//对高子表递归排序
	        }
        }else{
        	insertSort(arr);
        }
    }

	private static void insertSort(int[] arr) {
		int i,j;
        //4,2,1,7,8
        for(i=1; i<arr.length; i++){
        	if(arr[i-1] > arr[i]){
        		int tmp = arr[i];//设置哨兵
        		for(j=i-1; j>=0&&arr[j]>tmp; j--){
        			arr[j+1] = arr[j];
        		}
        		//j=-1
        		arr[j+1] = tmp;
        		//2 4 1 7 8
        	}
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
        int tempCopy = pivotkey;//将枢轴值备份到tempCopy中

        while(low < high)//从表的两端向中间扫描
        {
            while(low < high && arr[high] >= pivotkey){//如果大于枢轴值，则下标减一，否则，跳出循环。
                high--;
            }
            if(low < high){
            	arr[low] = arr[high];//采用替换而不是交换的方式进行操作
            }
            while (low < high && arr[low] < pivotkey){//如果小于枢轴值，则下标加一，否则，跳出循环。
                low++;
            }
            if(low < high){
            	arr[high] = arr[low];//采用替换而不是交换的方式进行操作
            }
        }
        arr[high] = tempCopy;
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
        int[] arr = {48,15,100,59,64,79,97,40,1,90,45,13,11};
        quickSort(arr);
        for (int array : arr) {
            System.out.print(array+" ");
        }
        System.out.println();
    }

}
