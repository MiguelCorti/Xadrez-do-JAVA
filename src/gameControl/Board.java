package gameControl;

import java.util.ArrayList;
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
	
	public boolean selectedPiece(Position piece) {
		Piece m_piece = boardMatrix[piece.getRow()][piece.getColumn()];
		Piece friendlyKing = null;
		
		for(int i = 1; i < 9; i++) {
            for(int j = 1; j < 9; j++) {
            	Piece tempKing = boardMatrix[i][j];
            	if(tempKing != null && tempKing instanceof King) {
            		if(tempKing.getColor() == m_piece.getColor())
            			friendlyKing = tempKing;
            	}
            }
		}
		
		if(m_piece.getColor() == Controller.getInstance().getCheck()) {
			if(!m_piece.equals(friendlyKing)) {
				return false;
			}
		}
		
		String descriptor = 'r' + Integer.toString(piece.getRow()) + Integer.toString(piece.getColumn());
		
		for(Position p : m_piece.possiblePositions) {
			descriptor += 'g' + Integer.toString(p.getRow()) + Integer.toString(p.getColumn()) ;
		}
		
		descriptor += '\0';
		
		this.setChanged();
		this.notifyObservers(descriptor);
		this.clearChanged();
		
		return true;
	}
	
	public boolean click(Position click, Position piece) {
		Piece myPiece = boardMatrix[piece.getRow()][piece.getColumn()];
		Piece enemyKing = null;
		Piece friendlyKing = null;
		boolean pieceMoved = myPiece.moveTo(click);
		
		for(int i = 1; i < 9; i++) {
            for(int j = 1; j < 9; j++) {
            	Piece tempKing = boardMatrix[i][j];
            	if(tempKing != null && tempKing instanceof King) {
            		if(tempKing.getColor() == myPiece.getColor())
            			friendlyKing = tempKing;
            		else
            			enemyKing = tempKing;
            	}
            }
		}
		
		
		// Wont let the player move other pieces than his king when at check
		if(myPiece.getColor() == Controller.getInstance().getCheck()) {
			System.out.println("EU N SOU REI");
			if(!myPiece.equals(friendlyKing))
				return false;
			else if (pieceMoved)
				Controller.getInstance().setCheck(0);
		}
		
		// If the movement is possible and the game hasn't ended (checkmate) the movement will occur
		if(pieceMoved && Controller.getInstance().getCheckMate() == 0) {
			int row = myPiece.getM_pos().getRow();
			int col = myPiece.getM_pos().getColumn();
			String descriptor = 'p' + Integer.toString(piece.getRow()) + Integer.toString(piece.getColumn()) 
			                        + Integer.toString(row) + Integer.toString(col);
			
			if((row == 8 && myPiece.getColor() == -1) || (row == 1 && myPiece.getColor() == 1))
			{
				if(myPiece instanceof Pawn)
				{
					descriptor += 'P' + Integer.toString(row) + Integer.toString(col);
				}
			}
			
			
			
			boardMatrix[piece.getRow()][piece.getColumn()] = null;
			boardMatrix[row][col] = myPiece;
			
			for(int i = 1; i < 9; i++) {
	            for(int j = 1; j < 9; j++) {
	            	if(boardMatrix[i][j] != null)
	            		boardMatrix[i][j].updatePossiblePositions();
	            }
			}
			
			
			if(checkKingPossiblePos(enemyKing)){
				descriptor += 'y' + Integer.toString(enemyKing.getM_pos().getRow()) 
				                  + Integer.toString(enemyKing.getM_pos().getColumn());
				
				Controller.getInstance().setCheck(enemyKing.getColor());
				
				if(enemyKing.possiblePositions.isEmpty())
				{
					System.out.println("XEQUE MATE " + friendlyKing.getColor() + " GANHOU");
				}
			}
			
			descriptor += '\0';
			this.setChanged();
			this.notifyObservers(descriptor);
			this.clearChanged();
			
			return true;
		}
		
		//System.out.printf("Nao foi possivel mover a peça de (%d, %d) para (%d, %d)\n", click.getRow(), click.getColumn(), piece.getRow(), piece.getColumn());
		
		return false;
	}
	
	
	private boolean checkKingPossiblePos(Piece king) {
		boolean kingIsAtCheck = false;
		ArrayList<Position> otherPositions = null;
		Piece otherPiece = null;
		
		for(int i = 1; i < 9; i++) {
			for(int j = 1; j < 9; j++) {
				otherPiece = boardMatrix[i][j];
				
				if(otherPiece != null && otherPiece.getColor() == -1*king.getColor()) {
					if(otherPiece instanceof Pawn)
						otherPositions = ((Pawn) otherPiece).attackPositions;
					else
						otherPositions = otherPiece.possiblePositions;
					
					for (Position otherPos : otherPositions) {
						for(int k = 0; k < king.possiblePositions.size(); k++) {
							if(king.possiblePositions.get(k).isEqual(otherPos)){
								king.possiblePositions.remove(k);
							}
						}
						
						if(king.getM_pos().isEqual(otherPos)) {
							kingIsAtCheck = true;
						}
					}
				}
			}
		}
		
		return kingIsAtCheck;
	}
	
	private void checkForCheck(Piece king){
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
	
	
	public void promotion(String promoteTo, int row, int col)
	{
		
		switch(promoteTo)
		{
			case "Cavalo":
				boardMatrix[row][col] = new Knight(row, col, -Controller.getInstance().getTurn());
				break;
			case "Bispo":
				boardMatrix[row][col] = new Bishop(row, col, -Controller.getInstance().getTurn());
				break;
			case "Torre":
				boardMatrix[row][col] = new Rook(row, col, -Controller.getInstance().getTurn());
				break;
			case "Dama":
				boardMatrix[row][col] = new Queen(row, col, -Controller.getInstance().getTurn());
				break;
		}
		
	}
	
	
	// Aux function for printing the board on the console
	private void printBoardMatrix()
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