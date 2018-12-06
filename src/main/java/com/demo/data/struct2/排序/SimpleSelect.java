package com.demo.data.struct2.排序;

/**简单选择排序：
 * 每一趟从待排序的数据元素中选出最小（最大）的元素，顺序放在待排序的数列最前，直到全部待排序的数据元素全部排完。
 * 初始关键字：『 8，5，2，6，9，3，1，4，0，7 』

 第一趟排序后：0，『5，2，6，9，3，1，4，8，7』

 第二趟排序后：0，1，『2，6，9，3，1，4，8，7』

 第三趟排序后：0，1，2，『6，9，3，5，4，8，7』

 第四趟排序后：0，1，2，3，『9，6，5，4，8，7』

 第五趟排序后：0，1，2，3，4，『6，5，9，8，7』

 第六趟排序后：0，1，2，3，4，5，『6，9，8，7』

 第七趟排序后：0，1，2，3，4，5，6，『9，8，7』

 第八趟排序后：0，1，2，3，4，5，6，7，『8，9』

 第九趟排序后：0，1，2，3，4，5，6，7，8，『9』

 结果：          『 0，1，2，3，4，5，6，7，8，9 』
 *
 */
public class SimpleSelect {

	public static int[] sort(int[] arr){
		int i, j, min;
	    for (i = 0; i < arr.length; i++){
	        min = i;//将当前下标定义为最小值下标
	        for (j = i + 1; j < arr.length; j++){
	            if (arr[j] < arr[min]){//找出未排序序列中的最小值
	            	min = j;
	            }
	        }
	        if (min != i){//说明找到新的最小值，交换
	        	Util.swap(arr, i, min);
	        }
	    }
	    return arr;
	}

	public static void main(String[] args) {
		int[] arr = {6,3,8,2,9,1};
		Util.display(SimpleSelect.sort(arr));
	}

}
