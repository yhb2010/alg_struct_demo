package com.demo.data.struct.chessBoard;

/**
 * 马踏棋盘问题：将马随机放在国际象棋的8×8棋盘chese[0～7][0～7]的某个方格中，马按走棋规则进行移动。要求每个方格只进入一次，
 * 走遍棋盘上全部64个方格。 编制程序，求出马的行走路线，并按求出的行走路线，将数字1，2，…，64依次填入一个8×8的方阵，输出之。
 *
 * @author DELL
 *
 */
public class ChessBoard {

	private final int X = 8;
	private final int Y = 8;
	private int[][] chese = new int[X][Y]; // 棋盘

	/**
	 * 找到下一点
	 *
	 * @param p
	 * @param count 选择0-7
	 * @return
	 */
	private Point getNext(Point p, int count) {
		int x = p.x;
		int y = p.y;

		switch (count) {
		case 0:
			if (x + 2 < X && y - 1 >= 0 && chese[x + 2][y - 1] == 0) {
				return new Point(x + 2, y - 1);
			}
			break;
		case 1:
			if (x + 2 < X && y + 1 < Y && chese[x + 2][y + 1] == 0) {
				return new Point(x + 2, y + 1);
			}
			break;
		case 2:
			if (x + 1 < X && y - 2 >= 0 && chese[x + 1][y - 2] == 0) {
				return new Point(x + 1, y - 2);
			}
			break;
		case 3:
			if (x + 1 < X && y + 2 < Y && chese[x + 1][y + 2] == 0) {
				return new Point(x + 1, y + 2);
			}
			break;
		case 4:
			if (x - 2 >= 0 && y - 1 >= 0 && chese[x - 2][y - 1] == 0) {
				return new Point(x - 2, y - 1);
			}
			break;
		case 5:
			if (x - 2 >= 0 && y + 1 < Y && chese[x - 2][y + 1] == 0) {
				return new Point(x - 2, y + 1);
			}
			break;
		case 6:
			if (x - 1 >= 0 && y - 2 >= 0 && chese[x - 1][y - 2] == 0) {
				return new Point(x - 1, y - 2);
			}
			break;
		case 7:
			if (x - 1 >= 0 && y + 2 < Y && chese[x - 1][y + 2] == 0) {
				return new Point(x - 1, y + 2);
			}
			break;
		default:
			break;
		}
		return null;
	}

	/**每次寻找下一点时，都是从1处开始，如果1处符合条件，把1处标记为遍历过，把1点处当作当前点，再寻找它的下一点。我们每次寻找下一点从1点处开始，如果1处不符合条件，即已经遍历过，或者越出棋盘边界，则检查2处是否合格，如果八个点都没有出路，则回溯到上一点处。
	 * @param start
	 * @param tag 记录步数，从1-64
	 * @return
	 */
	public int travelChessBoard(Point start, int tag) {
		int count = 0;
		int x = start.x;
		int y = start.y;

		Point next;

		chese[x][y] = tag;
		//System.out.println("x=" + x + ", y=" + y + ", tag=" + chese[x][y]);

		if (tag == X * Y) {
			return 1;
		}

		// 找到下一个点next
		next = getNext(start, count);
		while (next == null && count <= 7) {
			count++;
			next = getNext(start, count);
		}

		while (next != null) {
			if (travelChessBoard(next, tag + 1) == 1) {
				return 1;
			}

			count++;
			next = getNext(start, count);
			while (next == null && count <= 7) {
				count++;
				next = getNext(start, count);
			}
		}

		//如果没有合适的下一步，需要还原棋盘当前位置，因为在方法一进入时就已经chese[x][y] = tag;
		if (next == null) {
			chese[x][y] = 0;
		}
		return 0;
	}

	public int[][] getChese() {
		return chese;
	}

}
