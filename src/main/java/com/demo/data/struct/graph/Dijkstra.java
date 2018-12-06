package com.demo.data.struct.graph;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

//迪杰斯特拉算法
public class Dijkstra {

	private static int INF = Integer.MAX_VALUE;
	private VertexNode[] mVexs; // 顶点数组
	public void setmVexs(VertexNode[] mVexs) {
		this.mVexs = mVexs;
	}

	/**
     * Dijkstra最短路径。
     * 即图中"节点vs"到其它各个节点的最短路径。
     * @param vs 起始节点
     * @param Graph 图
     */
    public void dijkstra(int vs) {
        int NUM = mVexs.length;
        int[] prenode = new int[NUM]; // 到达该顶点的最短路径的前驱顶点数组
        int[] mindist = new int[NUM]; // 从vs到每个顶点的最短距离数组
        boolean[] find = new boolean[NUM]; // 该顶点是否已经找到最短路径

        int vnear = 0;

        for (int i = 0; i < mindist.length; i++) {
            prenode[i] = 0;
            find[i] = false;
            mindist[i] = INF;
        }
        EdgeNode node = mVexs[vs].firstArc;
        mindist[vs] = 0;
        while (node != null) {
        	mindist[node.index] = node.value;
			node = node.nextArc;
		}

        find[vs] = true;

        for (int v = 1; v < NUM; v++) {

            // 每次循环求得距离vs最近的节点vnear和最短距离min
            int min = INF;
            for (int j = 0; j < NUM; j++) {
                if (!find[j] && mindist[j] < min) {
                    min = mindist[j];
                    vnear = j;
                }
            }
            find[vnear] = true;

            // 根据vnear修正vs到其他所有节点的前驱节点及距离
            for (int k = 0; k < NUM; k++) {
            	if (!find[k]){
	            	int tmp = getWeight(vnear, k);
	            	tmp = (tmp == INF ? INF : (min + tmp)); // 防止溢出
	                if (tmp < mindist[k]) {
	                    prenode[k] = vnear;
	                    mindist[k] = tmp;
	                }
            	}
            }
        }

        for (int i = 0; i < NUM; i++) {
        	BlockingDeque<String> deque = new LinkedBlockingDeque<String>();
        	if(i != vs){
        		System.out.print(mVexs[vs].name + "-" + mVexs[i].name + "(" + mindist[i] + ")" + ": ");
        		int p = i;
        		do{
        			deque.addFirst(mVexs[prenode[p]].name + "->" + mVexs[p].name);
        			p = prenode[p];
        		}while(p > 0);
        	}
        	int z = 0;
        	while(!deque.isEmpty())
				try {
					if(z == 0)
						System.out.print(deque.takeFirst());
					else{
						String path = deque.takeFirst();
						System.out.print(path.substring(path.indexOf("->")));
					}
					z++;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
        	System.out.println();
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
