package com.demo.data.struct.graph;

//普里姆算法
public class DijkstraDemo {

    public static void main(String[] args) {
    	CreateGraph.createGraph(new String[]{"A", "B", "C", "D"},
    			new String[]{"A B", "B C", "A C", "A D", "D C"},
    			new int[]{2, 2, 5, 3, 1});
    	CreateGraph.outputGraph();
    	Dijkstra dijkstra = new Dijkstra();
    	dijkstra.setmVexs(CreateGraph.getGraph().adjList);
    	dijkstra.dijkstra(0);
    	System.out.println("================================");

    	CreateGraph.createGraph(new String[]{"A", "B", "C", "D"},
    			new String[]{"A B", "B C", "A C", "B D", "D C"},
    			new int[]{1, 2, 2, 1, 2});
    	CreateGraph.outputGraph();
    	Dijkstra dijkstra3 = new Dijkstra();
    	dijkstra3.setmVexs(CreateGraph.getGraph().adjList);
    	dijkstra3.dijkstra(0);
    	System.out.println("================================");

    	CreateGraph.createGraph(new String[]{"A", "B", "C", "D", "E"},
    			new String[]{"A B", "B D", "A C", "D E", "C E"},
    			new int[]{2, 1, 3, 1, 6});
    	CreateGraph.outputGraph();
    	Dijkstra dijkstra4 = new Dijkstra();
    	dijkstra4.setmVexs(CreateGraph.getGraph().adjList);
    	dijkstra4.dijkstra(0);
    	System.out.println("================================");

        CreateGraph.createGraph(new String[]{"v0", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8"},
        		new String[]{"v0 v1", "v0 v2", "v1 v2", "v1 v3", "v1 v4", "v2 v4", "v2 v5", "v3 v4", "v3 v6", "v4 v6", "v4 v7", "v4 v5", "v5 v7", "v6 v7", "v6 v8", "v7 v8"},
        		new int[]{1, 5, 3, 7, 5, 1, 7, 2, 3, 6, 9, 3, 5, 2, 7, 4});
        CreateGraph.outputGraph();
        Dijkstra dijkstra2 = new Dijkstra();
        dijkstra2.setmVexs(CreateGraph.getGraph().adjList);
        dijkstra2.dijkstra(0);
        System.out.println("================================");
    }

}
