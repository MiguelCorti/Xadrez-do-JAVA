import java.util.ArrayList;

public abstract class Piece {
	public Position m_pos;
	
	ArrayList<Position> possiblePositions = new ArrayList();
	
	protected abstract void updatePossiblePositions();
	
	public boolean checkMovementTo(int column, int row) {
		
	}
	
	public void moveTo(){
		
	}
	
}
