package gameControl;
import java.util.ArrayList;

public abstract class Piece {
	protected Position m_pos;
	private int m_color;
	private boolean hasMoved;
	
	ArrayList<Position> possiblePositions = new ArrayList<Position>();
	
	public Piece(int row, int column, int color)
	{
		m_pos = new Position();
		
		m_pos.setRow(row);
		m_pos.setColumn(column);
		m_color = color;
		hasMoved = false;
	}

	public abstract void updatePossiblePositions();
	
	public boolean checkMovementTo(Position pos) {
		for(Position p : possiblePositions) {
			if(p.isEqual(pos))
				return true;
		}
		
		return false;
	}
	
	
	public boolean moveTo(Position pos){
		/*for(Position p : possiblePositions)
			p.print();*/
		if(checkMovementTo(pos)) {
			m_pos = pos;
			
			updatePossiblePositions();
			
			return true;
		}
		
		System.out.println("Casa invalida");
		return false;
	}

	public int getColor() {
		return m_color;
	}

	public void setColor(int color) {
		m_color = color;
	}
	
	public Position getM_pos() {
		return m_pos;
	}

	public void setM_pos(Position pos) {
		m_pos = pos;
	}
	
	public void hasMoved()
	{
		hasMoved = true;
	}
	
	public boolean getHasMoved()
	{
		return hasMoved;
	}
	
}
