package com.demo.data.struct2.排序.快速排序;

import com.demo.data.struct2.排序.Util;

//通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列。
/**
 * Demo步骤解析：
1.一开始选定数组的最后一个元素5作为基准值，也就是最终排序结果应该是以5为界限划分为左右两边。
2.从左边开始，寻找比5大的值，然后与5进行调换(因为如果比5小的值本来就应该排在5前面，比5大的值调换之后就去到了5的后面)，一路过来找到了7，将7与5调换，结束此次遍历。
3.从右边开始，由于7已经是上一轮排好序的便不再动它，从10开始，一路向左遍历，寻找比5小的值，然后与5进行调换(因为如果比5大的值本来就应该排在5后面，比5小的值调换之后就去到了5的后前面)，一路过来找到了4，将4与5调换，结束此次遍历。
4.从左边开始，由于3和4都是前两轮已经排好序的便不再动它，从2开始，一路向右遍历，寻找比5大的值，然后与5进行调换（道理同步骤2），一路过来找到了9，将9与5调换，结束此次遍历。
5.从右边开始，由于排在9后面的那几个数字都是上几轮排好序的便不再动它，从1开始，一路向右遍历，寻找比5小的值，然后与5进行调换(道理同步骤3)，一下子就找到了1，将1与5调换，结束此次遍历。
6.这个时候，发现5的左右两边都是排好序了的，所以结束此轮排序，5的左右两边抽出来各自进行下一轮的排序，规则同上，直到无法再拆分下去，即完成了整体的快速排序。
 * @author DELL
 *
 */
public class QuickSort {

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

	public static void main(String[] args) {
        int[] arr = {48,15,24,59,64,79,97,40};
        quickSort(arr);
        for (int array : arr) {
            System.out.print(array+" ");
        }
        System.out.println();
    }

}
