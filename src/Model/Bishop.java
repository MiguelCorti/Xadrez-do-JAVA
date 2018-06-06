package Model;
import Controller.Board;

public class Bishop extends Piece{
	
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
		while(newPos.set(currentRow + i, currentColumn + i) && Board.getBoard().sqrState(newPos) == 0)
		{
			newPos = new Position();
			possiblePositions.add(newPos);
			i++;
		}
		
		i = -1;
		while(newPos.set(currentRow + i, currentColumn + i) && Board.getBoard().sqrState(newPos) == 0)
		{
			newPos = new Position();
			possiblePositions.add(newPos);
			i--;
		}
		
		i = 1;
		while(newPos.set(currentRow + i, currentColumn - i) && Board.getBoard().sqrState(newPos) == 0)
		{
			newPos = new Position();
			possiblePositions.add(newPos);
			i++;
		}
		
		i = 1;
		while(newPos.set(currentRow - i, currentColumn + i) && Board.getBoard().sqrState(newPos) == 0)
		{
			newPos = new Position();
			possiblePositions.add(newPos);
			i++;
		}
	}
}
