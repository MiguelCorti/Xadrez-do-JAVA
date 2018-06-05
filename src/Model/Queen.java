package Model;
import Controller.Board;

public class Queen extends Piece{	
	
	public Queen(int row, int column, int color) {
		super(row, column, color);
	}

	protected void updatePossiblePositions()
	{
		int currentRow = m_pos.getRow();
		int currentColumn = m_pos.getColumn();
		int i;
		Position newPos = new Position();
		
		possiblePositions.clear();

		// Adding the possible straight positions
		i = 1;
		while(Board.getBoard().sqrState(newPos) == 0 && newPos.set(currentRow, i)) {
			if(i != currentColumn)
				possiblePositions.add(newPos);
			
			i++;
		}
		
		i = 1;
		while(Board.getBoard().sqrState(newPos) == 0 && newPos.set(i, currentColumn)) {
			
			if(i != currentRow && Board.getBoard().sqrState(newPos) == 0)
				possiblePositions.add(newPos);
			
			i++;
		}
		
		
		// Now adding the possible diagonal positions
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
