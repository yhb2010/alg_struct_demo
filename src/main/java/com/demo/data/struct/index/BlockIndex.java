package com.demo.data.struct.index;

import java.util.Arrays;

//分块索引
public class BlockIndex {

	public static Student blockIndex(Student[] array, int block, int key) {

        if (array != null && array.length > 0) {
            if (block < 1 || block > array.length) {
                return null;
            }
            Arrays.sort(array);//为了方便构造 块内有序 其实可以无序
            Index[] list = new Index[block];
            int blocksize = array.length / block;
            //建立索引及数据分块
            for (int i = 0; i < array.length; i++) {

                if (i % blocksize == 0 || block == 1) {//分块建立索引
                    Index index = new Index();
                    index.key = array[i].no;
                    index.value = array[i];
                    if (block == 1) {//只分一块 首个是索引
                        index.size = array.length;
                        list[0] = index;
                        break;
                    } else if (i / blocksize == block - 1) {//长度和分块个数不均匀，剩下最后的数据放在最后一块
                        index.size = array.length - i;
                        list[block - 1] = index;
                        break;
                    } else if (i / blocksize < block - 1) {
                        index.size = blocksize;
                        list[i / blocksize] = index;
                    }

                }

            }

            //根据索引关键码搜索对应的块
            int mid = blockSearch(list, key);
            System.out.println("mid:" + mid);
            if (mid != -1) {
                Index index = list[mid];//找出索引
                System.out.println("index:" + index);
                for (int i = 0; i < index.size; i++) {//块内无序 顺序查找
                    System.out.println("array[i]:" + array[(mid * blocksize) + i]);
                    if (key == array[(mid * blocksize) + i].no) {
                        return array[(mid * blocksize) + i];
                    }
                }
            }
        }
        return null;
    }

	public static int blockSearch(Index[] array, int key) {
        if (array.length > 0) {
            int result, low, high, mid;
            result = 0;
            low = 0;
            high = array.length - 1;
            while (low <= high) {
                mid = (low + high) / 2;//折半
                if (key < array[mid].key) {
                    high = mid - 1;
                    result = high;
                } else if (key > array[mid].key) {
                    low = mid + 1;
                    result = mid;
                } else
                    return mid;

            }
            return result;
        }

        return -1;
    }

	public static void main(String[] args) {
		System.out.println(BlockIndex.blockIndex(
				new Student[]{
					new Student(1, "zsl"),
					new Student(2, "lisi"),
					new Student(3, "lili"),
					new Student(4, "wangwu"),
					new Student(5, "zhaosi"),
					new Student(6, "lixao"),
					new Student(7, "shenyang"),
					new Student(8, "jiuliu"),
					new Student(9, "wubin"),
					new Student(10, "hafu"),
					new Student(11, "zhaonan")
				},
				3,
				3));
	}

}
