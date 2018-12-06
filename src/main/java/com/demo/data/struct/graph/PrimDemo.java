package com.demo.data.struct.graph;

//普里姆算法
public class PrimDemo {

    public static void main(String[] args) {
        CreateGraph.createGraph(new String[]{"v1", "v2", "v3", "v4", "v5", "v6"},
        		new String[]{"v1 v2", "v1 v3", "v1 v4", "v2 v3", "v2 v5", "v5 v3", "v5 v6", "v6 v3", "v6 v4", "v4 v3"},
        		new int[]{6, 1, 5, 5, 3, 6, 6, 4, 2, 5});
        CreateGraph.outputGraph();
        Prim prim = new Prim();
        prim.setmVexs(CreateGraph.getGraph().adjList);
        prim.prim(0);
        System.out.println("================================");

        CreateGraph.createGraph(new String[]{"A", "B", "C", "D", "E", "F", "G"},
        		new String[]{"A B", "A F", "A G", "B F", "B C", "C D", "C E", "C F", "D E", "E F", "E G", "F G"},
        		new int[]{12, 16, 14, 7, 10, 3, 5, 6, 4, 2, 8, 9});
        CreateGraph.outputGraph();
        prim = new Prim();
        prim.setmVexs(CreateGraph.getGraph().adjList);
        prim.prim(0);
        System.out.println("================================");
    }

}
