package com.demo.data.struct.recursion;

import java.io.IOException;

//字符串反向输出
public class ReverseString {

	public static void print(char[] carr, int index){
		char c = carr[index++];
		if(c != '#'){
			print(carr, index);
			System.out.print(c);
		}
	}

	public static void main(String[] args) throws IOException {
		String s = "abcdefg#";
		print(s.toCharArray(), 0);
	}

}
