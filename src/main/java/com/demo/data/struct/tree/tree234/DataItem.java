package com.demo.data.struct.tree.tree234;

//表示节点中存储的数据项的数据类型
public class DataItem {
	public long dData; // 存储的数据类型，可以为其他复杂的对象或自定义对象

	public DataItem(long dd) // 构造函数
	{
		dData = dd;
	}

	public void displayItem() // 显示数据
	{
		System.out.print("/" + dData);
	}

	@Override
	public String toString() {
		return "DataItem [dData=" + dData + "]";
	}

}