package gameControl;

import java.util.ArrayList;

public class Pawn extends Piece{
	
	ArrayList<Position> attackPositions = new ArrayList<Position>();
	
	public Pawn(Pawn other) {
		super(other);
		attackPositions = other.attackPositions;
	}
	public Pawn(int row, int column, int color) {
		super(row, column, color);
		setInitialPossiblePositions();
	}
	
	private void setInitialPossiblePositions()
	{
		Position newPos = null;
		
		newPos = new Position(m_pos.getRow() - (1*this.getColor()), m_pos.getColumn());
		possiblePositions.add(newPos);
			
		newPos = new Position(m_pos.getRow() - (2*this.getColor()), m_pos.getColumn());
		possiblePositions.add(newPos);

	}
	
	public void updatePossiblePositions() {
		possiblePositions.clear();
		
		Position p = new Position();
		
		if(p.set( m_pos.getRow()-getColor(), m_pos.getColumn() ) && Board.getBoard().sqrState(p) == 0 )
			possiblePositions.add(p);
		
		if(m_pos.getRow()==2 || m_pos.getRow()==7)
		{
			if(!possiblePositions.isEmpty())
			{				
				p = new Position();
				if(p.set( m_pos.getRow()-2*getColor(), m_pos.getColumn() ) && Board.getBoard().sqrState(p) == 0 )
					possiblePositions.add(p);
			}
		}
		
		
		p = new Position();
		if(p.set(m_pos.getRow() - getColor(), m_pos.getColumn() + 1))
		{
			attackPositions.add(p);
			if(Board.getBoard().sqrState(p) == (getColor()*(-1)))
			{
				possiblePositions.add(p);
			}
		}
		
		p = new Position();
		if(p.set(m_pos.getRow() - getColor(), m_pos.getColumn() - 1))
		{
			attackPositions.add(p);
			if(Board.getBoard().sqrState(p) == (getColor()*(-1)))
			{
				possiblePositions.add(p);
			}
		}
			
			
			
	}
}
