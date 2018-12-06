package com.demo.data.struct.chessBoard;

public class ChessBoardTest {

	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		ChessBoard chessBoard = new ChessBoard();
		chessBoard.travelChessBoard(new Point(2, 0), 1);
		System.out.println(System.currentTimeMillis() - start);
		int[][] chess = chessBoard.getChese();
		for (int i = 0; i < chess.length; i++) {
			int[] col = chess[i];
			for (int j = 0; j < col.length; j++) {
				int tag = col[j];
				if(tag < 10)
					System.out.print(tag + "  ");
				else
					System.out.print(tag + " ");
			}
			System.out.println();
		}
    }

}
