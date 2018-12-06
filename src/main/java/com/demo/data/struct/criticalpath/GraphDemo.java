package com.demo.data.struct.criticalpath;

//JAVA实现关键路径查找
public class GraphDemo {

    public static void main(String[] args) {
    	CreateGraph.createGraph(new String[]{"v0", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8"},
    			new String[]{"v0 v1", "v0 v2", "v0 v3", "v1 v4", "v2 v4", "v4 v6", "v4 v7", "v6 v8", "v7 v8", "v3 v5", "v5 v7"},
    			new int[]{6, 4, 5, 1, 1, 9, 7, 2, 4, 2, 4});
    	CreateGraph.outputGraph();

    	CriticalPath path = new CriticalPath();
    	path.setmVexs(CreateGraph.getGraph().adjList, CreateGraph.getGraph().e);
    	path.topology();
    	System.out.println();

    	System.out.println("=================================");
    	CreateGraph.createGraph(new String[]{"v0", "v1", "v2", "v3", "v4", "v5", "v6", "v7", "v8", "v9"},
    			new String[]{"v0 v1", "v0 v2", "v1 v3", "v2 v3", "v1 v4", "v3 v4", "v2 v5", "v4 v7", "v5 v7", "v4 v6", "v7 v8", "v6 v9", "v8 v9"},
    			new int[]{3, 4, 5, 8, 6, 3, 7, 4, 6, 9, 5, 2, 3});
    	CreateGraph.outputGraph();

    	CriticalPath path2 = new CriticalPath();
    	path2.setmVexs(CreateGraph.getGraph().adjList, CreateGraph.getGraph().e);
    	path2.topology();
    	System.out.println();
    }

}
