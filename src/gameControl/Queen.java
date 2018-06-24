package gameControl;

public class Queen extends Piece{	
	
	public Queen(int row, int column, int color) {
		super(row, column, color);
	}

	public void updatePossiblePositions()
	{
		possiblePositions.clear();

		// As the queen is basically a mix between a bishop and a rook, we'll just reuse their codes
		addAllRookPositions();
		
		addAllBishopPositions();
		
	}
	
	
	// Same as the Rook code
	private void addAllRookPositions(){
		int currentRow = m_pos.getRow();
		int currentColumn = m_pos.getColumn();
		int i;
		Position newPos = new Position();

		
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
	
	// Same as the bishop code
	private void addAllBishopPositions(){
		int currentRow = m_pos.getRow();
		int currentColumn = m_pos.getColumn();
		int i;
		Position newPos = new Position();
		
		i = 1;
		while(newPos.set(currentRow + i, currentColumn + i) && Board.getBoard().sqrState(newPos) != this.getColor())
		{
			possiblePositions.add(newPos);

			if(Board.getBoard().sqrState(newPos) == -1*this.getColor())
				break;
			
			newPos = new Position();
			
			i++;
		}
		
		i = 1;
		newPos = new Position();
		while(newPos.set(currentRow - i, currentColumn - i) && Board.getBoard().sqrState(newPos) != this.getColor())
		{
			possiblePositions.add(newPos);

			if(Board.getBoard().sqrState(newPos) == -1*this.getColor())
				break;
			
			newPos = new Position();
			
			i++;
		}
		
		i = 1;
		newPos = new Position();
		while(newPos.set(currentRow + i, currentColumn - i) && Board.getBoard().sqrState(newPos) != this.getColor())
		{
			possiblePositions.add(newPos);

			if(Board.getBoard().sqrState(newPos) == -1*this.getColor())
				break;
			
			newPos = new Position();
			i++;
		}
		
		i = 1;
		newPos = new Position();
		while(newPos.set(currentRow - i, currentColumn + i) && Board.getBoard().sqrState(newPos) != this.getColor())
		{
			possiblePositions.add(newPos);

			if(Board.getBoard().sqrState(newPos) == -1*this.getColor())
				break;
			
			newPos = new Position();
			
			i++;
		}
	}
	
}
