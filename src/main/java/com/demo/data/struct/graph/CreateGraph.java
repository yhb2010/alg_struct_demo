package com.demo.data.struct.graph;

public class CreateGraph {
	private static Graph graph;

	public static Graph getGraph() {
		return graph;
	}

	public static void createGraph(String[] adjListArr, String[] edgeArr, int[] weightArr) {
		int v = adjListArr.length;
		int e = edgeArr.length;
		graph = new Graph(v, e, adjListArr, edgeArr, weightArr);
	}

	public static void outputGraph() { // 不知道为何空指针异常
		try {
			System.out.println("输出邻接表存储情况：");
			EdgeNode en = new EdgeNode();
			for (int i = 0; i < graph.v; i++) {
				System.out.print(graph.adjList[i].name);
				en = graph.adjList[i].firstArc;
				while (en != null) {
					System.out.print("->" + graph.adjList[en.index].name + "(" + en.value + ")");
					en = en.nextArc;
				}
				System.out.println();
			}
		} catch (NullPointerException e) {

		}

	}

}