package gameControl;

public class King extends Piece{

	public King(King other) {
		super(other);
	}
	
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
		
		
		//Castle case
		if(!this.getHasMoved())
		{
			
			//Short castle
			
			int turn = Controller.getInstance().getTurn();
			
			p = new Position();
			p.set(m_pos.getRow(), m_pos.getColumn()+2);
			
			Position temp = new Position();
			temp.set(m_pos.getRow(), m_pos.getColumn()+1);
			
			Position temp2 = new Position();
			temp2.set(m_pos.getRow(), m_pos.getColumn()+3);
			
			if(Board.getBoard().sqrState(p) == 0 && !Board.getBoard().isPositionAtCheck(p, -turn))
			{
				if(Board.getBoard().sqrState(temp) == 0 && !Board.getBoard().isPositionAtCheck(temp, -turn))
				{
					if(Board.getBoard().rookHasNotMoved(temp2))
					{
						possiblePositions.add(p);
					}
				}
			}
			
			//Long castle
			p = new Position();
			p.set(m_pos.getRow(), m_pos.getColumn()-2);
			temp.set(m_pos.getRow(), m_pos.getColumn()-1);
			temp2.set(m_pos.getRow(), m_pos.getColumn()-3);
			
			Position temp3 = new Position();
			temp3.set(m_pos.getRow(), m_pos.getColumn()-4);
			
			if(Board.getBoard().sqrState(p) == 0 && !Board.getBoard().isPositionAtCheck(p, -turn))
			{
				if(Board.getBoard().sqrState(temp) == 0 && !Board.getBoard().isPositionAtCheck(temp, -turn))
				{
					if(Board.getBoard().sqrState(temp2) == 0 && !Board.getBoard().isPositionAtCheck(temp2, -turn))
					{
						if(Board.getBoard().rookHasNotMoved(temp3))
						{
							possiblePositions.add(p);
						}
					}
				}
			}
		}
		
	}
	
}
