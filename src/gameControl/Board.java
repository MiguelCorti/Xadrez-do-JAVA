package gameControl;

import java.util.Observable;

public class Board extends Observable {
	
	private Piece[][] boardMatrix = new Piece[9][9];
	
	private static Board board = null;

	
	private Board()
	{
		// Filling Black Pieces at start position
		boardMatrix[1][1] = new Rook(1, 1, -1);
		boardMatrix[1][2] = new Knight(1, 2, -1);
		boardMatrix[1][3] = new Bishop(1, 3, -1);
		boardMatrix[1][4] = new Queen(1, 4, -1);
		boardMatrix[1][5] = new King(1, 5, -1);
		boardMatrix[1][6] = new Bishop(1, 6, -1);
		boardMatrix[1][7] = new Knight(1, 7, -1);
		boardMatrix[1][8] = new Rook(1, 8, -1);
		
		boardMatrix[2][1] = new Pawn(2, 1, -1);
		boardMatrix[2][2] = new Pawn(2, 2, -1);
		boardMatrix[2][3] = new Pawn(2, 3, -1);
		boardMatrix[2][4] = new Pawn(2, 4, -1);
		boardMatrix[2][5] = new Pawn(2, 5, -1);
		boardMatrix[2][6] = new Pawn(2, 6, -1);
		boardMatrix[2][7] = new Pawn(2, 7, -1);
		boardMatrix[2][8] = new Pawn(2, 8, -1);
		
		// Filling White Pieces at start position
		boardMatrix[8][1] = new Rook(8, 1, 1);
		boardMatrix[8][2] = new Knight(8, 2, 1);
		boardMatrix[8][3] = new Bishop(8, 3, 1);
		boardMatrix[8][4] = new Queen(8, 4, 1);
		boardMatrix[8][5] = new King(8, 5, 1);
		boardMatrix[8][6] = new Bishop(8, 6, 1);
		boardMatrix[8][7] = new Knight(8, 7, 1);
		boardMatrix[8][8] = new Rook(8, 8, 1);
		
		boardMatrix[7][1] = new Pawn(7, 1, 1);
		boardMatrix[7][2] = new Pawn(7, 2, 1);
		boardMatrix[7][3] = new Pawn(7, 3, 1);
		boardMatrix[7][4] = new Pawn(7, 4, 1);
		boardMatrix[7][5] = new Pawn(7, 5, 1);
		boardMatrix[7][6] = new Pawn(7, 6, 1);
		boardMatrix[7][7] = new Pawn(7, 7, 1);
		boardMatrix[7][8] = new Pawn(7, 8, 1);
		
		// Fill empty squares with null
		for(int i = 3; i < 7; i++) {
			for(int j = 1; j < 9; j++) {
				boardMatrix[i][j] = null;
			}
		}
	}
	
	public static Board getBoard() {
		if(board == null)
			board = new Board();
		return board;
	}
	
	/* Checks the state of the passed square
	 * Returns 0 : the sqrPos is empty
	 * Returns 1 : the sqrPos has a white piece
	 * Returns -1: the sqrPos has a black piece
	 */
	public int sqrState(Position sqrPos)
	{
		if(boardMatrix[sqrPos.getRow()][sqrPos.getColumn()] == null)
		{
			return 0;
		}
		return boardMatrix[sqrPos.getRow()][sqrPos.getColumn()].getColor();
	}
	
	
	// Aux function for printing the board on the console
	private void printBMatrix()
    {

        for(int i =1; i<9; i++)
        {
            for(int j = 1; j<9; j++)
            {
                if(boardMatrix[i][j]==null)
                    System.out.print(" 0 ");
                else
                {
                    if(boardMatrix[i][j] instanceof Rook)
                        System.out.print(" t ");
                    else if(boardMatrix[i][j] instanceof Bishop)
                        System.out.print(" b ");
                    else if(boardMatrix[i][j] instanceof King)
                        System.out.print(" r ");
                    else if(boardMatrix[i][j] instanceof Knight)
                        System.out.print(" c ");
                    else if(boardMatrix[i][j] instanceof Pawn)
                        System.out.print(" p ");
                    else if(boardMatrix[i][j] instanceof Queen)
                        System.out.print(" d ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
    }
}