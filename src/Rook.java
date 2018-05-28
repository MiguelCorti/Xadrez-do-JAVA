
public class Rook extends Piece{
	
	public Rook(int row, int column, int color) {
		super(row, column, color);
	}

	protected void updatePossiblePositions()
	{
		int currentRow = m_pos.getRow();
		int currentColumn = m_pos.getColumn();
		int i;
		Position newPos;
		
		possiblePositions.clear();

		i = 1;
		newPos = new Position(currentRow, i);
		while(Board.getBoard().sqrState(newPos) == 0 && i < 9) {
			if(i != currentColumn)
				possiblePositions.add(newPos);
			
			i++;
			newPos = new Position(currentRow, i);
		}
		
		i = 1;
		newPos = new Position(i, currentColumn);
		while(Board.getBoard().sqrState(newPos) == 0 && i < 9) {
			
			if(i != currentRow && Board.getBoard().sqrState(newPos) == 0)
				possiblePositions.add(newPos);
			
			i++;
			newPos = new Position(i, currentColumn);
		}
	}
}
