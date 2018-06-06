package Model;
import Controller.Board;

public class Knight extends Piece{

	public Knight(int row, int column, int color) {
		super(row, column, color);
		setInicialPossiblePositions();
	}
	
	private void setInicialPossiblePositions()
	{
		
	}

	protected void updatePossiblePositions() {
		possiblePositions.clear();
		
		Position p = new Position();

		if(p.set(m_pos.getRow()+2, m_pos.getColumn()+1 ))
			possiblePositions.add(p);

		p = new Position();
		if(p.set(m_pos.getRow()-2, m_pos.getColumn()+1))
			possiblePositions.add(p);

		p = new Position();
		if(p.set(m_pos.getRow()+2, m_pos.getColumn()-1))
			possiblePositions.add(p);

		p = new Position();
		if(p.set(m_pos.getRow()-2, m_pos.getColumn()-1))
			possiblePositions.add(p);

		p = new Position();
		if(p.set(m_pos.getRow()+1, m_pos.getColumn()+2))
			possiblePositions.add(p);

		p = new Position();
		if(p.set(m_pos.getRow()-1, m_pos.getColumn()+2))
			possiblePositions.add(p);

		p = new Position();
		if(p.set(m_pos.getRow()+1, m_pos.getColumn()-2))
			possiblePositions.add(p);
		
		p = new Position();
		if(p.set(m_pos.getRow()-1, m_pos.getColumn()-2))
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
