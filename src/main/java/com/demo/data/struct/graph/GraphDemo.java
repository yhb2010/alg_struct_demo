package com.demo.data.struct.graph;

//JAVA实现图的邻接表以及DFS(深度优先遍历)
public class GraphDemo {

    public static void main(String[] args) {
    	CreateGraph.createGraph(new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i"},
    			new String[]{"a b", "a f", "b c", "b i", "b g", "c i", "c d", "d i", "d g", "d h", "d e", "e h", "e f", "f g", "g h"},
    			null);
    	CreateGraph.outputGraph();
    	System.out.println("DFS图的过程如下：");
    	DFSGraph.DFS(CreateGraph.getGraph() , 0, new boolean[100]);
    	System.out.println();
        System.out.println("BFS图的过程如下：");
        DFSGraph.BFSVGraph(CreateGraph.getGraph() , 0, new boolean[100]);
    	System.out.println();
    	System.out.println("================================");

        CreateGraph.createGraph(new String[]{"v0", "v1", "v2", "v3", "v4", "v5", "v6", "v7"},
        		new String[]{"v0 v1", "v0 v2", "v1 v3", "v1 v4", "v2 v5", "v2 v6", "v5 v6", "v3 v7", "v4 v7"},
        		null);
        CreateGraph.outputGraph();
        System.out.println("DFS图的过程如下：");
        DFSGraph.DFS(CreateGraph.getGraph() , 0, new boolean[100]);
        System.out.println();
        System.out.println("BFS图的过程如下：");
        DFSGraph.BFSVGraph(CreateGraph.getGraph() , 0, new boolean[100]);
        System.out.println();
        System.out.println("================================");

        CreateGraph.createGraph(new String[]{"a", "b", "c", "d"}, new String[]{"a b", "a c", "a d", "b c", "b d"},
        		null);
        CreateGraph.outputGraph();
        System.out.println("DFS图的过程如下：");
        DFSGraph.DFS(CreateGraph.getGraph() , 0, new boolean[100]);
        System.out.println();
        System.out.println("BFS图的过程如下：");
        DFSGraph.BFSVGraph(CreateGraph.getGraph() , 0, new boolean[100]);
    }

}
