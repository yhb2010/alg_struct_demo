package com.demo.data.struct.criticalpath;

import java.util.Arrays;
import java.util.Stack;

//关键路径
//事件最早发生时间etv（earliest time of vertex）:顶点Vk的最早发生时间
//事件最晚发生时间ltv（latest time of vertex）:顶点Vk的最晚发生时间，也就是每个顶点对应的事件最晚需要开始的时间，超出此时间将会延误整个工期。
//活动最早开工时间ete（earliest time of edge）:弧ak最早发生时间。
//活动最晚开工时间lte（latest time of edge）:弧ak最晚发生时间，也就是不推迟工期的最晚开工时间。
public class CriticalPath {

	private VertexNode[] mVexs; // 顶点数组
	private int edgeNum;// 边总数
	private int[] etvs; // 事件最早发生时间
	private int[] ltvs; // 事件最晚发生时间
	private int[] etes; // 活动最早开工时间
	private int[] ltes; // 活动最晚开工时间
	private Stack<VertexNode> stackTopology; // 用于存放拓扑序列

	public void setmVexs(VertexNode[] mVexs, int edgeNum) {
		this.mVexs = mVexs;
		this.edgeNum = edgeNum;
		stackTopology = new Stack<>();
		etvs = new int[mVexs.length];
		ltvs = new int[mVexs.length];
		etes = new int[edgeNum];
		ltes = new int[edgeNum];
	}

	public void topology() {
		Stack<VertexNode> stack = new Stack<>();

		for (VertexNode ver : mVexs) {
			// 首先把入度为0的所有节点入队
			if (ver.enterEdgeNumber == 0) {
				stack.push(ver);
			}
		}

		System.out.print("拓扑顺序为：");
		while (!stack.isEmpty()) {
			// 队首出列，并找到相邻节点
			VertexNode ver = stack.pop();
			stackTopology.push(ver); // 保存拓扑排序的顺序
			System.out.print(ver.name + " -> ");

			for (EdgeNode edge = ver.firstArc; edge != null; edge = edge.nextArc) {
				int index = edge.index;
                VertexNode toVer = mVexs[index];
                //此顶点的入度为零则入栈，以便于下次循环输出
                if (--toVer.enterEdgeNumber == 0) {
                	stack.push(toVer);
                }

                if(etvs[index] < edge.value + etvs[ver.index]){
                	etvs[index] = edge.value + etvs[ver.index];
                }
			}
		}

		//初始化ltv为汇点时间
		for(int i = 0; i < ltvs.length; i++){
			ltvs[i] = etvs[mVexs.length - 1];
		}
		// 从汇点倒过来逐个计算ltv
		while(!stackTopology.isEmpty()){
			VertexNode ver = stackTopology.pop();
			for (EdgeNode edge = ver.firstArc; edge != null; edge = edge.nextArc) {
				int index = edge.index;
				if(ltvs[ver.index] > ltvs[index] - edge.value){
					ltvs[ver.index] = ltvs[index] - edge.value;
                }
			}
		}

		System.out.println();
		System.out.print("事件最早发生时间：");
		Arrays.stream(etvs).forEach(x -> System.out.print(x + ", "));
		System.out.println();
		System.out.print("事件最晚发生时间：");
		Arrays.stream(ltvs).forEach(x -> System.out.print(x + ", "));

		System.out.println();
		System.out.print("关键路径：");
		int i = 0;
		for(int j=0; j<mVexs.length; j++){
			VertexNode ver = mVexs[j];
			for (EdgeNode edge = ver.firstArc; edge != null; edge = edge.nextArc) {
				int index = edge.index;
				etes[i] = etvs[j];
				ltes[i] = ltvs[index] - edge.value;
				if(etes[i] == ltes[i]){
					System.out.print(ver.name + "->" + mVexs[index].name + ", ");
				}
				i++;
			}
		}
		System.out.println();
		System.out.print("活动最早发生时间：");
		Arrays.stream(etes).forEach(x -> System.out.print(x + ", "));
		System.out.println();
		System.out.print("活动最晚发生时间：");
		Arrays.stream(ltes).forEach(x -> System.out.print(x + ", "));
	}

}
