package com.demo.data.struct2.排序.快速排序;

import com.demo.data.struct2.排序.Util;

/**
 * 4、优化递归操作
我们知道，递归对性能是有一定影响的， QSort 函数在其尾部有两次递归操作。
如果待排序的序列划分极端不平衡，递归深度将趋近与N ，而不是平衡时的 logN,就不仅仅是速度快慢的问题了，栈的大小是很有限的，每次递归调用都会耗费一定的空间 ，函数的参数越多，每次递归耗费的空间也越多。如果能减少递归，将会提高性能。我们对 QSort 实施尾递归优化。 *
 */
public class QuickSort5 {

	final static int MAX_LENGTH_INSERT_SORT = 7;

	public static void quickSort(int[] arr){
		qSort(arr, 0, arr.length-1);
    }

	//对顺序表子序列作快速排序     待排序序列的最小下标值low和最大下标值high
    public static void qSort(int[] arr, int low, int high){
        int pivot;
        if((high-low)>MAX_LENGTH_INSERT_SORT){
        	//当我们将 if 改成 while 后，因为第一次递归以后，变量low就没有用处了，所以可以将 pivot+1 赋值给low，再循环后，来一次 Partition
        	//(arr,low,high)时，其效果等同于 “QSort(arr, pivot+1, high);”。结果相同，但因采用迭代而不是递归的方法可以缩减堆栈深度，从而提高了整体性能。
        	while(low < high){
	            pivot = partition(arr, low, high);//将数组子序列一分为二

	            qSort(arr, low, pivot-1);//对低子表递归排序
	            low = pivot + 1;
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
