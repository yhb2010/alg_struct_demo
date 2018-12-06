package com.demo.data.struct.index;

import java.util.Arrays;

//稠密索引
public class DenseIndex {

	public static Student denseIndex(Student[] array, int key) {

        if (array != null && array.length > 0) {
            Arrays.sort(array);

            Index[] list = new Index[array.length];
            //建立索引
            for (int i = 0; i < array.length; i++) {
                Index index = new Index();
                index.key = array[i].no;
                index.value = array[i];
                list[i] = index;
            }

            //根据索引关键码搜索
            int code = binarySearch(list, key);
            if (code != -1) {
                return (Student) list[code].value;
            }
        }
        return null;
    }

	public static int binarySearch(Index[] array, int key) {
        if (array.length > 0) {
            int low, high, mid;
            low = 0;
            high = array.length - 1;
            while (low <= high) {
                mid = (low + high) / 2;//折半
                if (key < array[mid].key)
                    high = mid - 1;
                else if (key > array[mid].key)
                    low = mid + 1;
                else
                    return mid;

            }
        }

        return -1;
    }

	public static void main(String[] args) {
		System.out.println(DenseIndex.denseIndex(new Student[]{new Student(1, "zsl"), new Student(2, "lisi"), new Student(3, "lili")}, 3));
	}

}
