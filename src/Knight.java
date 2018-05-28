
public class Knight extends Piece{

	public Knight(int row, int column, int color) {
		super(row, column, color);
	}

	protected void updatePossiblePositions() {
		possiblePositions.clear();
		
		Position p = new Position();
		
		if(p.set(m_pos.getColumn()+1, m_pos.getRow()+2))
			possiblePositions.add(p);
		
		if(p.set(m_pos.getColumn()+1, m_pos.getRow()-2))
			possiblePositions.add(p);
		
		if(p.set(m_pos.getColumn()-1, m_pos.getRow()+2))
			possiblePositions.add(p);
		
		if(p.set(m_pos.getColumn()-1, m_pos.getRow()-2))
			possiblePositions.add(p);
		
		if(p.set(m_pos.getColumn()+2, m_pos.getRow()+1))
			possiblePositions.add(p);
		
		if(p.set(m_pos.getColumn()+2, m_pos.getRow()-1))
			possiblePositions.add(p);
		
		if(p.set(m_pos.getColumn()-2, m_pos.getRow()+1))
			possiblePositions.add(p);
		
		if(p.set(m_pos.getColumn()-2, m_pos.getRow()-1))
			possiblePositions.add(p);
		
		for(int i = 0; i<possiblePositions.size(); i++)
		{
			if(Board.getBoard().sqrState(possiblePositions.get(i)) != 0)
			{
				possiblePositions.remove(i);
			}
		}
	}
}
