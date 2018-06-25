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
		int originalRow = piece.getRow();
		int originalColumn = piece.getColumn();
		int newRow = originalRow, newColumn = originalColumn;
		int oldRow = originalRow, oldColumn = originalColumn;
		
		Piece destinationPiece = null;
		Piece m_piece = boardMatrix[originalRow][originalColumn];
		m_piece.updatePossiblePositions();
		ArrayList<Integer> invalidPositions = new ArrayList<Integer>();
		
		Piece friendlyKing = null;
		boolean friendlyKingChecked = false;
		boolean tempBool;
		
		for(int i = 1; i < 9; i++) {
            for(int j = 1; j < 9; j++) {
            	Piece tempKing = boardMatrix[i][j];
            	if(tempKing != null && tempKing instanceof King) {
            		if(tempKing.getColor() == m_piece.getColor())
            			friendlyKing = tempKing;
            	}
            }
		}
		
		friendlyKingChecked = isPositionAtCheck(friendlyKing.getM_pos(), -1*friendlyKing.getColor());
		
		ArrayList<Position> tempPossiblePos = new ArrayList<Position>(m_piece.possiblePositions);
		
		for(int i = 0; i < tempPossiblePos.size(); i++) {
			Position p = tempPossiblePos.get(i);
			
			m_piece.setM_pos(p);
	
			newRow = m_piece.getM_pos().getRow();
			newColumn = m_piece.getM_pos().getColumn();		
			
			boardMatrix[oldRow][oldColumn] = destinationPiece;
			// Placing the piece in the new possible position
			destinationPiece = makeCopyOf(boardMatrix[newRow][newColumn]);
			
			boardMatrix[newRow][newColumn] = m_piece;
			
			oldRow = newRow;
			oldColumn = newColumn;
			
			updateAllPossiblePositions();
			
			tempBool = isPositionAtCheck(friendlyKing.getM_pos(), -1*friendlyKing.getColor());
			
			if(m_piece instanceof Knight) {
				System.out.print("Possible pos: ");
				p.print();
				System.out.println(" makes the king checked: " + tempBool);
			}

			 
			/* When (friendlyKingChecked == false && tempBool == true) means this movement places the king at check, so it should be invalid (will remove the pos)
			 * When (friendlyKingChecked == true && tempBool == true) this means this movement changes nothing about the check state, thus shouldn't be valid (will remove)
			 * When (friendlyKingChecked == true && tempBool == false) means this movement will make the king unchecked, so it should be available (will do nothing)
			 * When (friendlyKingChecked == false && tempBool == false) means there's no check and wont make the king checked, so it's the usual case and should be available (will do nothing)
			 */
			if((friendlyKingChecked == false && tempBool == true) || (friendlyKingChecked == true && tempBool == true)) { // this means this movement places the king at check, so it should be invalid
				invalidPositions.add(i);
			}
		}
		
		m_piece.setM_pos(new Position(originalRow, originalColumn));
		
		boardMatrix[newRow][newColumn] = destinationPiece;
		boardMatrix[originalRow][originalColumn] = m_piece;
		
		updateAllPossiblePositions();
		
		int invPos;
		for(int i = invalidPositions.size() - 1; i >= 0 ; i--)
		{
			invPos = invalidPositions.get(i);
			m_piece.possiblePositions.remove(invPos);
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
		
		
		// If the movement is possible and the game hasn't ended (checkmate) the movement will occur
		if(pieceMoved && Controller.getInstance().getCheckMate() == 0) {
			myPiece.hasMoved();

			int row = myPiece.getM_pos().getRow();
			int col = myPiece.getM_pos().getColumn();
			String descriptor = "";
			
			//Castle case
			if(myPiece instanceof King)
			{
				if(Math.abs( piece.getColumn() - col ) > 1)
				{
					
					Piece myRook;
					Position p = new Position();
					if(col == 7)
					{
						myRook = boardMatrix[piece.getRow()][8];
						boardMatrix[piece.getRow()][8] = null;
						p.set(piece.getRow(), 6);
						descriptor += 'p' + Integer.toString(piece.getRow()) + '8';
					}
					else //col==3
					{
						myRook = boardMatrix[piece.getRow()][1];
						boardMatrix[piece.getRow()][1] = null;
						p.set(piece.getRow(), 4);
						descriptor += 'p' + Integer.toString(piece.getRow()) + '1';
					}
					myRook.setM_pos(p);
					
					boardMatrix[p.getRow()][p.getColumn()] = myRook;
					descriptor += Integer.toString(myRook.getM_pos().getRow()) + Integer.toString(myRook.getM_pos().getColumn());
				}
			}
			
			//setting descriptor
			
			descriptor += 'p' + Integer.toString(piece.getRow()) + Integer.toString(piece.getColumn()) 
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
			
			updateAllPossiblePositions();
			
			if(checkForDraw()) {
				descriptor += 'D';
				Controller.getInstance().setCheckMate(friendlyKing.getColor());
			}
			else if(isPositionAtCheck(enemyKing.getM_pos(), -1*enemyKing.getColor())){
				descriptor += 'y' + Integer.toString(enemyKing.getM_pos().getRow()) 
				                  + Integer.toString(enemyKing.getM_pos().getColumn());
				
				Controller.getInstance().setCheck(enemyKing.getColor());
				
				if(enemyKing.possiblePositions.isEmpty())
				{
					Controller.getInstance().setCheckMate(friendlyKing.getColor());
					descriptor += 'X' + Integer.toString(friendlyKing.getColor());
				}
			}
			else
				Controller.getInstance().setCheck(0);
			
			descriptor += '\0';
			this.setChanged();
			this.notifyObservers(descriptor);
			this.clearChanged();
			
			Controller.getInstance().changeTurn();
			return true;
		}
		
		return false;
	}
	
	private Piece makeCopyOf(Piece copied) {
		Piece copy = null;
		
		if(copied instanceof Pawn)
			copy = new Pawn((Pawn)copied);
		else if(copied instanceof King)
			copy = new King((King)copied);
		else if(copied instanceof Queen)
			copy = new Queen((Queen)copied);
		else if(copied instanceof Knight)
			copy = new Knight((Knight)copied);
		else if(copied instanceof Rook)
			copy = new Rook((Rook)copied);
		else if(copied instanceof Bishop)
			copy = new Bishop((Bishop)copied);
		
		return copy;
	}
	
	private void updateAllPossiblePositions() {
		for(int i = 1; i < 9; i++) {
            for(int j = 1; j < 9; j++) {
            	if(boardMatrix[i][j] != null)
            		boardMatrix[i][j].updatePossiblePositions();
            }
		}
	}
	

	private boolean checkForDraw() {
		for(int i = 1; i < 9; i++) {
            for(int j = 1; j < 9; j++) {
            	if(boardMatrix[i][j] != null && boardMatrix[i][j].getColor() == Controller.getInstance().getTurn())
            		if(!boardMatrix[i][j].possiblePositions.isEmpty())
            			return false;
            }
		}
		
		return true;
	}

	public boolean isPositionAtCheck(Position pos, int enemyColor) {
		ArrayList<Position> otherPositions = null;
		Piece otherPiece = null;
		
		for(int i = 1; i < 9; i++) {
			for(int j = 1; j < 9; j++) {
				otherPiece = boardMatrix[i][j];
				
				if(otherPiece != null && otherPiece.getColor() == enemyColor) {
					if(otherPiece instanceof Pawn)
						otherPositions = ((Pawn) otherPiece).attackPositions;
					else
						otherPositions = otherPiece.possiblePositions;
					
					for (Position otherPos : otherPositions) {
						if(pos.isEqual(otherPos))
							return true;
					}
				}
			}
		}
		
		return false;
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
				boardMatrix[row][col] = new Knight(row, col, Controller.getInstance().getTurn());
				break;
			case "Bispo":
				boardMatrix[row][col] = new Bishop(row, col, Controller.getInstance().getTurn());
				break;
			case "Torre":
				boardMatrix[row][col] = new Rook(row, col, Controller.getInstance().getTurn());
				break;
			case "Dama":
				boardMatrix[row][col] = new Queen(row, col, Controller.getInstance().getTurn());
				break;
		}
		
	}
	
	
	public boolean rookHasNotMoved(Position pos)
	{
		Piece temp = boardMatrix[pos.getRow()][pos.getColumn()];
		if(temp instanceof Rook)
		{
			if(!temp.getHasMoved())
			{
				
				return true;
			}
		}
		return false;
	}
	
	public void continueGame(ArrayList<String> gamePieces)
	{
		//Clearing the boardMatrix
		for(int i = 1; i < 9; i++) {
			for(int j = 1; j < 9; j++) {
				boardMatrix[i][j] = null;
			}
		}
		
		int rTurn = 1;
		if(gamePieces.get(0).equals("1"))
		{
			rTurn = 1;
		}
		else if(gamePieces.get(0).equals("0"))
		{
			rTurn = -1;
		}
		
		
		Controller.getInstance().setTurn(rTurn);
		
		for(int i=1; i<gamePieces.size(); i++)
		{
			int row = Integer.parseInt(gamePieces.get(i).substring(1, 2));
			int col = Integer.parseInt(gamePieces.get(i).substring(2, 3));
			int color = Integer.parseInt(gamePieces.get(i).substring(3, 4));
			if(color==0)
				color = -1;
			int hasMoved = Integer.parseInt(gamePieces.get(i).substring(4, 5));
			
			switch(gamePieces.get(i).substring(0, 1))
			{
				case "k":
					boardMatrix[row][col] = new King(row, col, color);
					if(hasMoved == 1)
						boardMatrix[row][col].hasMoved();
					break;
				case "q":
					boardMatrix[row][col] = new Queen(row, col, color);
					if(hasMoved == 1)
						boardMatrix[row][col].hasMoved();
					break;
				case "r":
					boardMatrix[row][col] = new Rook(row, col, color);
					if(hasMoved == 1)
						boardMatrix[row][col].hasMoved();
					break;
				case "b":
					boardMatrix[row][col] = new Bishop(row, col, color);
					if(hasMoved == 1)
						boardMatrix[row][col].hasMoved();
					break;
				case "n":
					boardMatrix[row][col] = new Knight(row, col, color);
					if(hasMoved == 1)
						boardMatrix[row][col].hasMoved();
					break;
				case "p":
					boardMatrix[row][col] = new Pawn(row, col, color);
					if(hasMoved == 1)
						boardMatrix[row][col].hasMoved();
					break;
			}
			Controller.getInstance().setTurn(-rTurn);
			updateAllPossiblePositions();
			Controller.getInstance().setTurn(rTurn);
		}
	}
	
	
	public String getGameString()
	{
		String tempString = "";
		String gameString = "";
		if(Controller.getInstance().getTurn() == 1)
			gameString += "1";
		else
			gameString += "0";
		
		gameString += System.lineSeparator();
		
		for(int i=1; i<9; i++)
		{
			for(int j=1; j<9; j++)
			{
				if(boardMatrix[i][j] != null)
				{
					if(boardMatrix[i][j] instanceof Rook)
						tempString += "r";
	                else if(boardMatrix[i][j] instanceof Bishop)
	                	tempString += "b";
	                else if(boardMatrix[i][j] instanceof King)
	                	tempString += "k";
	                else if(boardMatrix[i][j] instanceof Knight)
	                	tempString += "n";
	                else if(boardMatrix[i][j] instanceof Pawn)
	                	tempString += "p";
	                else if(boardMatrix[i][j] instanceof Queen)
	                	tempString += "q";
					
					tempString += Integer.toString(i) + Integer.toString(j);
					
					if(boardMatrix[i][j].getColor() == 1)
						tempString += "1";					
					else
						tempString += "0";
					
					if(boardMatrix[i][j].getHasMoved())
						tempString += "1";
					else
						tempString += "0";
					
					tempString += System.lineSeparator();
					gameString += tempString;
					tempString = "";
				}
			}
		}
		return gameString;
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