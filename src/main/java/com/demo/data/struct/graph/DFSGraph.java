package com.demo.data.struct.graph;

import java.util.LinkedList;
import java.util.Queue;

/**针对连通图
 * @author DELL
 *
 */
public class DFSGraph {

	 /**
     * 深度优先遍历
     * @param graph 当前节点
     * @param k 起始顶点
     * @param visited 被访问过的节点列表
     */
	public static void DFS(Graph graph, int k, boolean[] visited) {
		System.out.println(graph.adjList[k].name);
		visited[k] = true;//顶点k标记被访问过
		EdgeNode p = graph.adjList[k].firstArc;

		while (p != null) {
			if (visited[p.index] != true) {
				DFS(graph, p.index, visited);
			}
			System.out.print("(" + graph.adjList[p.index].name + ")");
			p = p.nextArc;
		}
	}

	/**广度优先遍历
	 * @param graph 当前节点
	 * @param k 起始顶点
	 * @param visited 被访问过的节点列表
	 */
	public static void BFSVGraph(Graph graph, int k, boolean[] visited){
		System.out.println(graph.adjList[k].name);
        visited[k] = true;//顶点k标记被访问过
        Queue<VertexNode> queue = new LinkedList<>();
        queue.add(graph.adjList[k]);

        while (!queue.isEmpty()) {
        	EdgeNode p = queue.poll().firstArc;
        	while(p != null){
	        	if (visited[p.index] != true) {
	        		System.out.println(graph.adjList[p.index].name);
	        		visited[p.index] = true;
	        		queue.add(graph.adjList[p.index]);
	        	}else{
	        		System.out.print("(" + graph.adjList[p.index].name + ")");
	        	}
	        	p = p.nextArc;
        	}
        }
    }

}
