
public class Rook extends Piece{
	
	public Rook(int row, int column, int color) {
		super(row, column, color);
	}

	protected void updatePossiblePositions()
	{
		int currentRow = m_pos.getRow();
		int currentColumn = m_pos.getColumn();
		Position newPos;
		
		possiblePositions.clear();

		for(int i = 1; i < 9; i++) {
			newPos = new Position(currentRow, i);
			if(i != currentColumn && Board.getBoard().sqrState(newPos) == 0)
				possiblePositions.add(newPos);
			
			newPos = new Position(i, currentColumn);
			if(i != currentRow && Board.getBoard().sqrState(newPos) == 0)
				possiblePositions.add(newPos);
		}
	}
}
