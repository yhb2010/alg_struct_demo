package com.demo.data.struct.criticalpath;

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
	int index;
	String name;
	int enterEdgeNumber = 0; // 入度
	EdgeNode firstArc = new EdgeNode(); // 指向第一条弧

	@Override
	public String toString() {
		return "VertexNode [name=" + name + ", enterEdgeNumber=" + enterEdgeNumber + ", firstArc=" + firstArc + "]";
	}
}

public class Graph {
	VertexNode[] adjList; // 保存邻接表的头节点
	int v; // 图的顶点数
	int e; // 图的边数
	String[] edgeArr;

	public Graph(int v, int e, String[] adjListArr, String[] edgeArr, int[] weightArr) {
		this.v = v;
		this.e = e;
		this.edgeArr = edgeArr;
		adjList = new VertexNode[v]; // 顶点表数组
		for (int i = 0; i < v; i++) {
			adjList[i] = new VertexNode();
			adjList[i].index = i;
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
			EdgeNode to = new EdgeNode();
			to.index = v2; // en2的下标是v2
			if(weightArr != null){
				to.value = weightArr[i];
			}

			adjList[v2].enterEdgeNumber++;
			to.nextArc = adjList[v1].firstArc;
			adjList[v1].firstArc = to;
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