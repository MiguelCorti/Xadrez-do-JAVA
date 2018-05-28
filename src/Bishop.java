
public class Bishop extends Piece{
	
	public Bishop(int row, int column, int color) {
		super(row, column, color);
	}

	protected void updatePossiblePositions()
	{
		int currentRow = m_pos.getRow();
		int currentColumn = m_pos.getColumn();
		
		possiblePositions.clear();

		for(int i = 1; i < 9; i++) {
			
		}
	}
}
