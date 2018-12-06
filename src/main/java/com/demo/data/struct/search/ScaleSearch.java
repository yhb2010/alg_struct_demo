package com.demo.data.struct.search;

//有序表查找：折半查找、二分查找、差值查找和斐波那契查找
//【1】顺序查找：是最简单的查找方法，其时间复杂度为O(n)，是通过构造一个线性表，采用遍历的方法，将记录与关键字一个一个的对比，若相等则查找成功，若全都不相等，则查找失败即记录不存在；
//【2】有序查找：顺序表的记录一般是无序，而有序表的记录是有序的；使用有序表查找方法时，前提条件是待查找的记录必须是已经排好序的。 有序查找分为：折半查找即二分查找、差值查找和斐波那契查找方法
public class ScaleSearch {

	//（1）折半查找法：又称二分查找，是最经典的有序表查找，它的前提是线性表中的记录必须是有序的（通常从小到大排列），线性表采用顺序存储的方式；其基本思路是：将关键字key与中间记录（（low+high）/2）进行比较；若相等，则查找成功；若关键字小于中间记录，则说明关键字可能在下半区；若大于，则说明关键字可能在上半区；不断重复上述过程，直到查找成功或失败；
	public static int Binary_Search(int[] num, int key) {
        int low, high, mid;
        low = 0;
        high = num.length - 1;
        while (low <= high) {
            mid = (low + high) / 2;
            if (key < num[mid])
                high = mid - 1;
            else if (key > num[mid])
                low = mid + 1;
            else// 如果等于则直接还回下标值
                return mid;
        }
        return -1;
    }

	//（2）插值查找(适用于数据分布比较均匀时)：对二分法查找进行改进，
	//采用插值公式的方法，来确定查找分区。可简单这样理解，比如有100个数其值在0~1000范围之间从小到大排序，你要查找关键字为5的位置下标，若采用二分法，则大概在500的地方往下查找，但采用插值的方法，可以通过插值计算出5这个关键字应该在靠近0的地方，因此查找时从50往下开始查找，从而提高效率
	public static int Insert_Search(int[] num, int key) {
        int low, high, mid;
        low = 0;
        high = num.length - 1;
        while (low <= high) {
            mid = low + (high - low) * (key - num[low])/ (num[high] - num[low]); // 插值查找
            if (key < num[mid])
                high = mid - 1;
            else if (key > num[mid])
                low = mid + 1;
            else
                // 如果等于则直接还回下标值
                return mid;
        }
        return -1;
    }

	public static void main(String[] args) {
        int[] num = { 1, 2, 3, 4, 5, 6 };//必须有序
        int index = Binary_Search(num, 5);
        System.out.println(index);

        index = Insert_Search(num, 5);
        System.out.println(index);
    }

}
