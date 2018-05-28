
public class Bishop extends Piece{
	
	public Bishop(int row, int column, int color) {
		super(row, column, color);
	}

	protected void updatePossiblePositions()
	{
		int currentRow = m_pos.getRow();
		int currentColumn = m_pos.getColumn();
		int i;
		Position newPos = new Position();
		
		possiblePositions.clear();
		
		i = 1;
		while(newPos.set(currentRow + i, currentColumn + i) && Board.getBoard().sqrState(newPos) == 0)
		{
			possiblePositions.add(newPos);
			i++;
		}
		
		i = -1;
		while(newPos.set(currentRow + i, currentColumn + i) && Board.getBoard().sqrState(newPos) == 0)
		{
			possiblePositions.add(newPos);
			i--;
		}
		
		i = 1;
		while(newPos.set(currentRow + i, currentColumn - i) && Board.getBoard().sqrState(newPos) == 0)
		{
			possiblePositions.add(newPos);
			i++;
		}
		
		i = 1;
		while(newPos.set(currentRow - i, currentColumn + i) && Board.getBoard().sqrState(newPos) == 0)
		{
			possiblePositions.add(newPos);
			i++;
		}
	}
}
