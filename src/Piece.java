import java.util.ArrayList;

public abstract class Piece {
	public Position m_pos;
	
	ArrayList<Position> possiblePositions = new ArrayList<Position>();

	protected abstract void updatePossiblePositions();
	
	public boolean checkMovementTo(Position pos) {
		for(Position p : possiblePositions) {
			if(p.isEqual(pos))
				return true;
		}
		
		return false;
	}
	
	
	public void moveTo(Position pos){
		if(checkMovementTo(pos)) {
			m_pos = pos;
			
			updatePossiblePositions();
		}
		else{
			//Colocou em uma casa não válida
		}
			
	}
}
