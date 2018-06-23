package gameControl;

public class King extends Piece{

	public King(int row, int column, int color) {
		super(row, column, color);
	}

	public void updatePossiblePositions() {
		possiblePositions.clear();
		
		Position p = new Position();
		if(p.set(m_pos.getRow()+0, m_pos.getColumn()+1))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);
		
		p = new Position();
		if(p.set(m_pos.getRow()+0, m_pos.getColumn()-1))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);
		
		p = new Position();
		if(p.set(m_pos.getRow()+1, m_pos.getColumn()+0))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);
		
		p = new Position();
		if(p.set(m_pos.getRow()-1, m_pos.getColumn()+0))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);
		
		p = new Position();
		if(p.set(m_pos.getRow()+1, m_pos.getColumn()+1))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);
		
		p = new Position();
		if(p.set(m_pos.getRow()-1, m_pos.getColumn()+1))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);
		
		p = new Position();
		if(p.set(m_pos.getRow()+1, m_pos.getColumn()-1))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);
		
		p = new Position();
		if(p.set(m_pos.getRow()-1, m_pos.getColumn()-1))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);
		
	}
}
