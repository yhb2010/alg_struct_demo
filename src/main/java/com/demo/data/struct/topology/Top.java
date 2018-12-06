package com.demo.data.struct.topology;

import java.util.Stack;

//拓扑序列
public class Top {

	private VertexNode[] mVexs; // 顶点数组

	public void setmVexs(VertexNode[] mVexs) {
		this.mVexs = mVexs;
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
			System.out.print(ver.name + " -> ");
			for (EdgeNode edge = ver.firstArc; edge != null; edge = edge.nextArc) {
				int index = edge.index;
                VertexNode toVer = mVexs[index];
                //此顶点的入度为零则入栈，以便于下次循环输出
                if (--toVer.enterEdgeNumber == 0) {
                	stack.push(toVer);
                }
			}

		}
	}

}
