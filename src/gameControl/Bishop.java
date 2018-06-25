package gameControl;

public class Bishop extends Piece{
	
	public Bishop(Bishop other) {
		super(other);
	}
	
	public Bishop(int row, int column, int color) {
		super(row, column, color);
	}

	public void updatePossiblePositions()
	{
		int currentRow = m_pos.getRow();
		int currentColumn = m_pos.getColumn();
		int i;
		Position newPos = new Position();
		
		possiblePositions.clear();
		
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
