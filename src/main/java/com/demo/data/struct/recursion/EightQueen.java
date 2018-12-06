package com.demo.data.struct.recursion;

import java.util.Date;

/**
八皇后问题，是一个古老而著名的问题，是回溯算法的典型案例。该问题是国际西洋棋棋手马克斯·贝瑟尔于1848年提出：在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。

 从第一行第一列开始逐行摆放皇后
依题意每行只能有一个皇后，遂逐行摆放，每行一个皇后即可
摆放后立即调用一个验证函数（传递整个棋盘的数据），验证合理性，安全则摆放下一个，不安全则尝试摆放这一行的下一个位置，直至摆到棋盘边界
当这一行所有位置都无法保证皇后安全时，需要回退到上一行，清除上一行的摆放记录，并且在上一行尝试摆放下一位置的皇后（回溯算法的核心）
当摆放到最后一行，并且调用验证函数确定安全后，累积数自增1，表示有一个解成功算出
验证函数中，需要扫描当前摆放皇后的左上，中上，右上方向是否有其他皇后，有的话存在危险，没有则表示安全，并不需要考虑当前位置棋盘下方的安全性，因为下面的皇后还没有摆放
 * @author DELL
 *
 */
public class EightQueen {

	private static final short N = 8; // 使用常量来定义，方便之后解N皇后问题
	private static int count = 0; // 结果计数器

	public static void main(String[] args) {
		Date begin = new Date();
		// 初始化棋盘，全部置0
		short chess[][] = init();

		putQueenAtRow(chess, 0);
		Date end = new Date();
		System.out.println("解决 " + N + " 皇后问题，用时：" + String.valueOf(end.getTime() - begin.getTime()) + "毫秒，计算结果：" + count);
	}

	private static void putQueenAtRow(short[][] chess, int row) {
		/**
		 * 递归终止判断：如果row==N，则说明已经成功摆放了8个皇后 输出结果，终止递归
		 */
		if (row == N) {
			count++;
			display(chess);
			return;
		}

		short[][] chessTemp = chess.clone();

		/**
		 * 向这一行的每一个位置尝试排放皇后 然后检测状态，如果安全则继续执行递归函数摆放下一行皇后
		 */
		for (int col = 0; col < N; col++) {
			// 摆放这一行的皇后，之前要清掉所有这一行摆放的记录，防止污染棋盘
			for (int j = 0; j < N; j++)
				chessTemp[row][j] = 0;

			if (isSafety(chessTemp, row, col)) {
				chessTemp[row][col] = 1;
				putQueenAtRow(chessTemp, row + 1);
			}
		}
	}

	private static boolean isSafety(short[][] chess, int row, int col) {
		// 判断中上、左上、右上是否安全
		int step = 1;
		while (row - step >= 0) {
			if (chess[row - step][col] == 1) // 正上方
				return false;
			if (col - step >= 0 && chess[row - step][col - step] == 1) // 左上方
				return false;
			if (col + step < N && chess[row - step][col + step] == 1) // 右上方
				return false;

			step++;
		}
		return true;
	}

	/**初始化
	 * @return
	 */
	private static short[][] init(){
		short chess[][] = new short[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				chess[i][j] = 0;
			}
		}
		return chess;
	}

	/**输出结果
	 * @param chess
	 */
	private static void display(short chess[][]){
		System.out.println("第 " + count + " 种解：");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(chess[i][j] + "  ");
			}
			System.out.println();
		}
	}

}
