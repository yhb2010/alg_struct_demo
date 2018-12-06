package com.demo.data.struct.graph;

//弗洛伊德算法
public class Floyd {

	private static int INF = Integer.MAX_VALUE;
	private VertexNode[] mVexs; // 顶点数组

	public void setmVexs(VertexNode[] mVexs) {
		this.mVexs = mVexs;
	}

	/*
     * floyd最短路径。
     * 即，统计图中各个顶点间的最短路径。
     *
     * 参数说明：
     *     path -- 路径。path[i][j]=k表示，"顶点i"到"顶点j"的最短路径会经过顶点k。
     *     dist -- 长度数组。即，dist[i][j]=sum表示，"顶点i"到"顶点j"的最短路径的长度是sum。
     */
    public void floyd() {

    	// 初始化
    	int[][] path = new int[mVexs.length][mVexs.length];
        int[][] dist = new int[mVexs.length][mVexs.length];

        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++) {
                dist[i][j] = getWeight(i, j);  // "顶点i"到"顶点j"的路径长度为"i到j的权值"。
                path[i][j] = j;                // "顶点i"到"顶点j"的最短路径是经过顶点j。
            }
        }

        // 计算最短路径
        for (int k = 0; k < mVexs.length; k++) {
            for (int i = 0; i < mVexs.length; i++) {
                for (int j = 0; j < mVexs.length; j++) {
                    // 如果经过下标为k顶点路径比原两点间路径更短，则更新dist[i][j]和path[i][j]
                    int tmp = (dist[i][k]==INF || dist[k][j]==INF) ? INF : (dist[i][k] + dist[k][j]);
                    if (dist[i][j] > tmp) {
                        // "i到j最短路径"对应的值设，为更小的一个(即经过k)
                        dist[i][j] = tmp;
                        // "i到j最短路径"对应的路径，经过k
                        path[i][j] = path[i][k];
                    }
                }
            }
        }

        // 打印floyd最短路径的结果
        System.out.printf("floyd: \n");
        for (int i = 0; i < mVexs.length; i++) {
            for (int j = 0; j < mVexs.length; j++)
                System.out.printf("%2d  ", dist[i][j]);
            System.out.printf("\n");
        }
    }

    /*
	 * 获取边<start, end>的权值；若start和end不是连通的，则返回无穷大。
	 */
	private int getWeight(int start, int end) {

		if (start == end)
			return 0;

		EdgeNode node = mVexs[start].firstArc;
		while (node != null) {
			if (end == node.index)
				return node.value;
			node = node.nextArc;
		}

		return INF;
	}

}
