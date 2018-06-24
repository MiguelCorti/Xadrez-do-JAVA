package gameControl;

public class Rook extends Piece{
	
	public Rook(int row, int column, int color) {
		super(row, column, color);
	}
	
	public void updatePossiblePositions()
	{
		int currentRow = m_pos.getRow();
		int currentColumn = m_pos.getColumn();
		int i;
		Position newPos = new Position();
		
		possiblePositions.clear();

		
		// Adding all squares north of the piece
		for(i = currentRow + 1; i < 9; i++)
		{
			newPos = new Position();
			if(newPos.set(i, currentColumn)) {
				if(Board.getBoard().sqrState(newPos) == this.getColor())
					break;
				
				possiblePositions.add(newPos);
				
				if(Board.getBoard().sqrState(newPos) == -1*this.getColor())
					break;
			}
		}
		
		// Adding all squares south of the piece
		for(i = currentRow - 1; i > 0; i--)
		{
			newPos = new Position();
			if(newPos.set(i, currentColumn)) {
				if(Board.getBoard().sqrState(newPos) == this.getColor())
					break;
				
				possiblePositions.add(newPos);
				
				if(Board.getBoard().sqrState(newPos) == -1*this.getColor())
					break;
			}
		}
		
		// Adding all squares east of the piece
		for(i = currentColumn + 1; i < 9; i++)
		{
			newPos = new Position();
			if(newPos.set(currentRow, i)) {
				if(Board.getBoard().sqrState(newPos) == this.getColor())
					break;
				
				possiblePositions.add(newPos);
				
				if(Board.getBoard().sqrState(newPos) == -1*this.getColor())
					break;
			}
		}
		
		// Adding all squares west of the piece
		for(i = currentColumn - 1; i > 0; i--)
		{
			newPos = new Position();
			if(newPos.set(currentRow, i)) {
				if(Board.getBoard().sqrState(newPos) == this.getColor())
					break;
				
				possiblePositions.add(newPos);
				
				if(Board.getBoard().sqrState(newPos) == -1*this.getColor())
					break;
			}
		}

	}
}
