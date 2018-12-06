package com.demo.data.struct2.排序;

//归并排序（MERGE-SORT）是利用归并的思想实现的排序方法，该算法采用经典的分治（divide-and-conquer）策略（分治法将问题分(divide)成一些小的问题然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案"修补"在一起，即分而治之)。
public class MergeSort {

	public static void main(String []args){
        int []arr = {9,8,7,6,5,4,3,2,1};
        sort(arr);
        Util.display(arr);
    }

    public static void sort(int []arr){
        int []temp = new int[arr.length];//在排序前，先建好一个长度等于原数组长度的临时数组，避免递归中频繁开辟空间
        sort(arr, 0, arr.length-1, temp);
    }

    private static void sort(int[] arr, int low, int high, int []temp){
        if(low < high){
            int mid = (low+high)/2;
            sort(arr, low, mid, temp);//左边归并排序，使得左子序列有序
            sort(arr, mid+1, high, temp);//右边归并排序，使得右子序列有序
            merge(arr, low, mid, high, temp);//将两个有序子数组合并操作
        }
    }

	private static void merge(int[] arr, int low, int mid, int high, int[] temp) {
		int i = low;// 左序列指针
		int j = mid + 1;// 右序列指针
		int t = 0;// 临时数组指针
		while (i <= mid && j <= high) {
			if (arr[i] <= arr[j]) {
				temp[t++] = arr[i++];
			} else {
				temp[t++] = arr[j++];
			}
		}
		while (i <= mid) {// 将左边剩余元素填充进temp中
			temp[t++] = arr[i++];
		}
		while (j <= high) {// 将右序列剩余元素填充进temp中
			temp[t++] = arr[j++];
		}
		t = 0;
		// 将temp中的元素全部拷贝到原数组中
		while (low <= high) {
			arr[low++] = temp[t++];
		}
	}

}
