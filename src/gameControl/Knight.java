package gameControl;

public class Knight extends Piece{

	public Knight(int row, int column, int color) {
		super(row, column, color);
		setInitialPossiblePositions();
	}
	
	private void setInitialPossiblePositions(){
		Position myPos = this.getM_pos();
		Position p = new Position(myPos.getRow()-2*this.getColor(),  myPos.getColumn()+1);
		possiblePositions.add(p);
		
		p = new Position(myPos.getRow()-2*this.getColor(),  myPos.getColumn()-1);
		possiblePositions.add(p);
	}
	
	public void updatePossiblePositions() {
		possiblePositions.clear();
		
		Position p = new Position();

		if(p.set(m_pos.getRow()+2, m_pos.getColumn()+1 ))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);

		p = new Position();
		if(p.set(m_pos.getRow()-2, m_pos.getColumn()+1))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);

		p = new Position();
		if(p.set(m_pos.getRow()+2, m_pos.getColumn()-1))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);

		p = new Position();
		if(p.set(m_pos.getRow()-2, m_pos.getColumn()-1))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);

		p = new Position();
		if(p.set(m_pos.getRow()+1, m_pos.getColumn()+2))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);

		p = new Position();
		if(p.set(m_pos.getRow()-1, m_pos.getColumn()+2))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);

		p = new Position();
		if(p.set(m_pos.getRow()+1, m_pos.getColumn()-2))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);
		
		p = new Position();
		if(p.set(m_pos.getRow()-1, m_pos.getColumn()-2))
			if(Board.getBoard().sqrState(p) != getColor())
				possiblePositions.add(p);
	
		
	}
	

	
	
}
