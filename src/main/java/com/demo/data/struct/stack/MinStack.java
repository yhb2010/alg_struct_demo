package com.demo.data.struct.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

//实现一个stack，同时可以快速获取最小值，时间复杂度和空间复杂度整体最优
public class MinStack {

	List<Integer> stack = new ArrayList<>();
	//保存的最小值对应的索引
	List<Integer> mins = new ArrayList<Integer>();

	public void push(Integer num) {
		stack.add(num);
		if(mins.size() == 0) {
            // 初始化mins
            mins.add(0);
        } else {
            // 辅助栈mins push最小值的索引
        	Integer min = getMin();
            if (num < min) {
                mins.add(stack.size() - 1);
            }
        }
	}

	public Integer pop() {
		if(stack.isEmpty()){
			return null;
		}
		// 获取mins栈顶元素，它是最小值索引
		Integer index = mins.get(mins.size() - 1);
		// 如果pop出去的索引就是最小值索引，mins才出栈
		if(index == stack.size() - 1){
			mins.remove(mins.size() - 1);
		}
		return stack.remove(stack.size() - 1);
	}

	public Integer getMin(){
		if(stack.isEmpty()){
			return null;
		}
		Integer index = mins.get(mins.size() - 1);
		if(index == null){
			return null;
		}
		// 获取mins栈顶元素，它是最小值索引
		return stack.get(index);
	}

	public static void main(String[] args) {
		MinStack s = new MinStack();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(-4);
		s.push(-3);
		s.push(-5);
		System.out.println(s.getMin());
		System.out.println(s.pop());
		System.out.println(s.getMin());
		System.out.println(s.pop());
		System.out.println(s.getMin());
		System.out.println(s.pop());
		System.out.println(s.getMin());
		System.out.println(s.pop());
		System.out.println(s.getMin());
		System.out.println(s.pop());
		System.out.println(s.getMin());
		System.out.println(s.pop());
	}

}
