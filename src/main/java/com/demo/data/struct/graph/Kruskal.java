package com.demo.data.struct.graph;

//克鲁斯卡尔（Kruskal）算法
public class Kruskal {

	private VertexNode[] mVexs; // 顶点数组
	private int mEdgNum; // 边的数量

	public void setmVexs(VertexNode[] mVexs) {
		this.mVexs = mVexs;
	}

	public void setmEdgNum(int mEdgNum) {
		this.mEdgNum = mEdgNum;
	}

	// 克鲁斯卡尔算法
	public void kruskal() {
		int index = 0; // rets数组的索引
		//parent数组用于构造MST判断是否存在环路
	    /*判断的思想:
	        1.初始化的时候，数组为[0,0,...,0]
	        2.第一次循环进来的时候比如(begin=0,end=1,weight=5),由于数组全为0,故返回0和1,
	              若begin和end的返回值不相等则设置parent[begin]=end,即设置了0的双亲节点是1,即把0节点和1节点加入MST
	              当数组数据为[1,5,8,7,7,8,0,0,6]时
	              表示[节点0连着1,1连着5,5连着8,8连着6,2连着8]以及[3连着7,4连着7]
	              若在加入<5,6时>，通过数组找到相同的顶点的话就说明构成了一个环
	        3.begin和end的返回值相等，则表示构成了环 */
        int[] parent = new int[mEdgNum]; // 定义一个数组用来判断边与边是否形成环路
		EData[] rets = new EData[mEdgNum]; // 结果数组，保存kruskal最小生成树的边

		EData[] edges = getEdges(); // 获取"图中所有的边"
		sortEdges(edges); // 将边按照"权"的大小进行排序(从小到大)

		for (int i = 0; i < edges.length; i++) {
			EData edge= edges[i];
			int m = find(parent, edge.start); // 获取"edge起点"在"已有的最小生成树"中的终点
			int n = find(parent, edge.end); // 获取"edge终点"在"已有的最小生成树"中的终点
			// 如果m!=n，意味着"边i"与"已经添加到最小生成树中的顶点"没有形成环路
			// 说明形成了环路或者两个结点都在一棵树上
            // 注：书上没有讲解为什么这种机制可以保证形成环路，思考了半天，百度了也没有什么好的答案，研究的时间不多，就暂时就放一放
			if (m != n) {
				parent[m] = n; // 设置m在"已有的最小生成树"中的终点为n
				rets[index++] = edges[i]; // 保存结果
			}
		}

		// 统计并打印"kruskal最小生成树"的信息
		int length = 0;
		for (int i = 0; i < index; i++)
			length += rets[i].weight;
		System.out.printf("Kruskal=%d: ", length);
		for (int i = 0; i < index; i++)
			System.out.printf("(%s,%s) ", rets[i].startName, rets[i].endName);
		System.out.printf("\n");
	}

	/*
	 * 获取图中的边
	 */
	private EData[] getEdges() {
		int index = 0;
		EData[] edges;

		edges = new EData[mEdgNum];
		for (int i = 0; i < mVexs.length; i++) {
			EdgeNode node = mVexs[i].firstArc;
			while (node != null) {
				if (node.index > i) {
					edges[index++] = new EData(i, mVexs[i].name, node.index, mVexs[node.index].name, node.value);
				}
				node = node.nextArc;
			}
		}

		return edges;
	}

	/*
	 * 对边按照权值大小进行排序(由小到大)
	 */
	private void sortEdges(EData[] edges) {
		for (int i = 0; i < mEdgNum; i++) {
			for (int j = i + 1; j < mEdgNum; j++) {
				if (edges[i].weight > edges[j].weight) {
					// 交换"边i"和"边j"
					EData tmp = edges[i];
					edges[i] = edges[j];
					edges[j] = tmp;
				}
			}
		}
	}

	public int find(int[] parent, int index) {
        while (parent[index] > 0) {
            index = parent[index];
        }
        return index;
    }

	// 边的结构体
	private static class EData {
		int start; // 边的起点
		String startName;
		int end; // 边的终点
		String endName;
		int weight; // 边的权重

		public EData(int start, String startName, int end, String endName, int weight) {
			this.start = start;
			this.startName = startName;
			this.end = end;
			this.endName = endName;
			this.weight = weight;
		}
	};

}
