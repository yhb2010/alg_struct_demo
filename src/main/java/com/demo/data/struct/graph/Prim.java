package com.demo.data.struct.graph;

import java.util.Arrays;

/**最短路径
 * @author DELL
 *
 */
class MinNodeLine {
	String startName;
	String endName;
	int value; // 权值
	public MinNodeLine(int value, String startName, String endName) {
		super();
		this.value = value;
		this.startName = startName;
		this.endName = endName;
	}
	@Override
	public String toString() {
		return "MinNodeLine [startName=" + startName + ", endName=" + endName + ", value=" + value + "]";
	}
}

/**
 * 普里姆算法生成最小生成树(邻接表)
 *
 * @author DELL
 *
 */
public class Prim {

	private static int INF = Integer.MAX_VALUE;
	private VertexNode[] mVexs; // 顶点数组

	public void setmVexs(VertexNode[] mVexs) {
		this.mVexs = mVexs;
	}

	/*
	 * prim最小生成树
	 *
	 * 参数说明： start -- 从图中的第start个元素开始，生成最小树
	 */
	public void prim(int start) {
		int min, i, j, k, tmp, sum = 0; // 计算最小生成树的权值
		int num = mVexs.length;
		int index = 0; // prim最小树的索引，即prims数组的索引
		String[] prims = new String[num]; // prim最小树的顶点结果数组
		MinNodeLine[] weights = new MinNodeLine[num]; // 顶点间边的权值，边的起点、终点

		// prim最小生成树中第一个数是"图中第start个顶点"，因为是从start开始的。
		prims[index++] = mVexs[start].name;

		// 初始化"顶点的权值数组"，
		// 将每个顶点的权值初始化为"第start个顶点"到"该顶点"的权值。
		for (i = 0; i < num; i++)
			weights[i] = getWeight(start, i);

		for (i = 0; i < num; i++) {
			// 由于从start开始的，因此不需要再对第start个顶点进行处理。
			if (start == i)
				continue;

			j = 0;
			k = 0;
			min = INF;
			// 在未被加入到最小生成树的顶点中，找出权值最小的顶点。
			while (j < num) {
				// 若weights[j]=0，意味着"第j个节点已经被排序过"(或者说已经加入了最小生成树中)。
				if (weights[j].value != 0 && weights[j].value < min) {
					min = weights[j].value;
					k = j;
				}
				j++;
			}

			sum += min;

			// 经过上面的处理后，在未被加入到最小生成树的顶点中，权值最小的顶点是第k个顶点。
			// 将第k个顶点加入到最小生成树的结果数组中
			prims[index++] = mVexs[k].name;
			// 将"第k个顶点的权值"标记为0，意味着第k个顶点已经排序过了(或者说已经加入了最小树结果中)。
			weights[k].value = 0;
			// 当第k个顶点被加入到最小生成树的结果数组中之后，更新其它顶点的权值。
			for (j = 0; j < num; j++) {
				// 获取第k个顶点到第j个顶点的权值
				tmp = getWeight(k, j).value;
				// 当第j个节点没有被处理，并且需要更新时才被更新。
				if (weights[j].value != 0 && tmp < weights[j].value){
					weights[j].value = tmp;
					weights[j].startName = mVexs[k].name;
					weights[j].endName = mVexs[j].name;
				}
			}
		}

		// 打印最小生成树
		System.out.printf("PRIM(%s)=%d: ", mVexs[start].name, sum);
		for (i = 0; i < index; i++)
			System.out.printf("%s ", prims[i]);
		System.out.printf("\n");
		Arrays.stream(weights).filter(node -> !node.startName.equals(node.endName)).forEach(node ->
			System.out.println(node.startName + " -> " + node.endName)
		);
	}

	/*
	 * 获取边<start, end>的权值；若start和end不是连通的，则返回无穷大。
	 */
	private MinNodeLine getWeight(int start, int end) {

		if (start == end)
			return new MinNodeLine(0, mVexs[start].name, mVexs[end].name);

		EdgeNode node = mVexs[start].firstArc;
		while (node != null) {
			if (end == node.index)
				return new MinNodeLine(node.value, mVexs[start].name, mVexs[end].name);
			node = node.nextArc;
		}

		return new MinNodeLine(INF, mVexs[start].name, mVexs[end].name);
	}

}
