package com.demo.data.struct.recursion;

import java.util.Scanner;

//实现汉诺塔问题求解
public class TowersOfHanoi {

	static int m =0;//标记移动次数
    //实现移动的函数
    public static void move(int disks, char N, char M)
    {
        System.out.println("第" + (++m) +" 次移动 : " +" 把 "+ disks+" 号圆盘从 " + N +" ->移到->  " + M);
    }
    //递归实现汉诺塔的函数
    public static void hanoi(int n, char X, char Y, char Z) {
        if(n == 1)//圆盘只有一个时，只需将其从X塔移到Z塔
            move(n, X, Z);//将编号为1的圆盘从X移到Z
        else {//否则
            hanoi(n - 1, X, Z, Y);//递归，把X塔上编号1~n-1的圆盘移到Y上，以Z为辅助塔
            move(n, X, Z);//把X塔上编号为n的圆盘移到Z上
            hanoi(n - 1, Y, X, Z);//递归，把Y塔上编号1~n-1的圆盘移到Z上，以X为辅助塔
        }
    }
    public static void main(String[] args) {
        Scanner imput = new Scanner(System.in);
        char X = 'X';
        char Y = 'Y';
        char Z = 'Z';
        System.out.println("******************************************************************************************");
        System.out.println("这是汉诺塔问题（把A塔上编号从小号到大号的圆盘从A塔通过B辅助塔移动到C塔上去");
        System.out.println("******************************************************************************************");
        System.out.print("请输入圆盘的个数：");
        int disks = imput.nextInt();
        TowersOfHanoi.hanoi(disks, X, Y, Z);
        System.out.println(">>移动了" + m + "次，把A上的圆盘都移动到了C上");
        imput.close();
    }

}
