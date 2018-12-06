package com.demo.data.struct.tree.tree234;

import java.io.*;

public class Tree234Test {

	public static void main(String[] args) throws IOException {
		Tree234 theTree = new Tree234();

		theTree.insert(50);
		theTree.insert(40);
		theTree.insert(60);
		theTree.insert(30);
		theTree.insert(70);
		theTree.insert(20);
		theTree.insert(10);
		theTree.insert(5);
		theTree.insert(1);
		theTree.insert(55);
		theTree.insert(65);
		theTree.insert(80);
		theTree.insert(90);
		theTree.insert(100);
		//-----------------------------
		theTree.insert(77);
		theTree.insert(89);
		theTree.insert(62);
		theTree.insert(12);
		theTree.insert(16);
		theTree.insert(8);
		theTree.insert(25);
		theTree.insert(98);

		theTree.displayTree();
	}

}
