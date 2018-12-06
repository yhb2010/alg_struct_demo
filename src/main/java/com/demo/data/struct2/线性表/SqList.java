package com.demo.data.struct2.线性表;

// 线性表的顺序存储操作
public class SqList {

	public Object[] listElem;	//线性表存储空间
	public int curLength;		//线性表当前长度

	public SqList(int maxSize) {
		curLength = 0;
		listElem = new Object[maxSize];
	}

	public void clear() {
		curLength = 0;
	}

	public boolean isEmpty() {
		return curLength == 0;
	}

	public int length() {
		return curLength;
	}

	public Object get(int i) throws Exception {
		if (curLength == 0) {
			throw new Exception("当前线性表为空 --> " + curLength);
		}
		if (i < 0 || i >= curLength) {
			throw new Exception("第" + i + "个元素不存在");
		}
		return listElem[i];
	}

	public void insert(int i, Object x) throws Exception {
		if (curLength == listElem.length) {
			throw new Exception("顺序表已满");
		}
		if (i < 0 || i > curLength) {
			throw new Exception("插入位置不合法");
		}
		for (int j = curLength; j > i; j--) {
			listElem[j] = listElem[j - 1];
		}
		listElem[i] = x;
		curLength++;
	}

	public void remove(int i) throws Exception {
		if (i < 0 || i >= curLength) {
			throw new Exception("删除位置不合法");
		}
		for (int j = i; j < curLength - 1; j++) {
			listElem[j] = listElem[j + 1];
		}
		curLength--;
	}

	public int indexOf(Object x) {
		int j = 0;

		while (j < curLength && !listElem[j].equals(x)) {
			j++;
		}
		if (j < curLength) {
			return j;
		}else {
			return -1;
		}
	}

	public void display() {
		for (int j = 0; j < curLength; j++) {
			System.err.print(listElem[j] + ", ");
		}
	}

	public static void main(String[] args) {
		SqList list = new SqList(10);

		try {
			list.insert(0, 1);
			list.insert(1, 2);
			list.insert(2, 3);
			list.remove(2);
		} catch (Exception e) {
			e.printStackTrace();
		}

		list.display();
	}

}
