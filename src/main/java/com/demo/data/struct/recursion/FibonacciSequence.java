package com.demo.data.struct.recursion;

public class FibonacciSequence {

	/*
	    斐波那契数列: 0、1、1、2、3、5、8
	    可以这样理解 f0 = 0; f1 = 1; fn = f(n-1) + f(n - 2) （n >= 2）

	*/
	public static void main(String[] args) {
	    System.out.println(f(5));
	}

    public static int f(int n) {
        if (n == 1 || n == 2) {
            return 1;
        } else {
            return f(n - 1) + f(n - 2);
        }
    }

}
