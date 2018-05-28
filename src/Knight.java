
public class Knight extends Piece{

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
	}


}
