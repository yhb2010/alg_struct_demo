package com.demo.data.struct.graph;

//邻接表实现图的建立

//储存边
class EdgeNode {
	int index; // 习惯了用index，其实标准写法是(adjVertex)
	int value; // 权值
	EdgeNode nextArc; // 指向下一条弧

	@Override
	public String toString() {
		return "EdgeNode [index=" + index + ", value=" + value + ", nextArc=" + nextArc + "]";
	}
}

// 邻接表节点的类型
class VertexNode {
	String name;
	EdgeNode firstArc = new EdgeNode(); // 指向第一条弧

	@Override
	public String toString() {
		return "VertexNode [name=" + name + ", firstArc=" + firstArc + "]";
	}
}

public class Graph {
	VertexNode[] adjList; // 保存邻接表的头节点
	int v; // 图的顶点数
	int e; // 图的边数

	public Graph(int v, int e, String[] adjListArr, String[] edgeArr, int[] weightArr) {
		this.v = v;
		this.e = e;
		adjList = new VertexNode[v]; // 顶点表数组
		for (int i = 0; i < v; i++) {
			adjList[i] = new VertexNode();
			adjList[i].name = adjListArr[i];
			adjList[i].firstArc = null; // 不可或缺
		}
		for (int i = 0; i < e; i++) {
			String[] arr = edgeArr[i].split(" ");
			// 保证e1,e2都是合法输入
			String e1 = arr[0];
			String e2 = arr[1];
			int v1 = check(e1);
			int v2 = check(e2);
			EdgeNode en1 = new EdgeNode();
			en1.index = v1; // en1的下标是v1
			if(weightArr != null){
				en1.value = weightArr[i];
			}
			EdgeNode en2 = new EdgeNode();
			en2.index = v2; // en2的下标是v2
			if(weightArr != null){
				en2.value = weightArr[i];
			}

			en2.nextArc = adjList[v1].firstArc;
			adjList[v1].firstArc = en2;

			en1.nextArc = adjList[v2].firstArc;
			adjList[v2].firstArc = en1;
		}
	}

	private int check(String e1) {
		for (int i = 0; i < v; i++) {
			if (adjList[i].name.equals(e1)) {
				return i;
			}
		}
		return -1;
	}

}