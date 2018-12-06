package com.demo.data.struct.topology;

//JAVA实现图的拓扑序列
public class GraphDemo {

    public static void main(String[] args) {
    	CreateGraph.createGraph(new String[]{"A", "B", "C", "D", "E", "F", "G"},
    			new String[]{"A B", "B C", "B F", "C E", "B E", "E D", "F G"},
    			null);
    	CreateGraph.outputGraph();

    	Top top = new Top();
    	top.setmVexs(CreateGraph.getGraph().adjList);
    	top.topology();
    	System.out.println();

    	System.out.println("================================");
    	CreateGraph.createGraph(new String[]{"A", "B", "C", "D", "E", "F"},
    			new String[]{"A B", "B C", "B D", "D C", "C F", "E C"},
    			null);
    	CreateGraph.outputGraph();

    	Top top2 = new Top();
    	top2.setmVexs(CreateGraph.getGraph().adjList);
    	top2.topology();
        System.out.println();
    }

}
