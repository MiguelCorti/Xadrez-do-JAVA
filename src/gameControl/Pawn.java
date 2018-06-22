package gameControl;

public class Pawn extends Piece{

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
		
		if(p.set( m_pos.getRow()-getColor(), m_pos.getColumn() ))
			possiblePositions.add(p);
		
		if(m_pos.getRow() == 1 || m_pos.getRow()==2 || m_pos.getRow()==7 || m_pos.getRow()==8)
		{
			p = new Position();
			if(p.set( m_pos.getRow()-2*getColor(), m_pos.getColumn() ))
				possiblePositions.add(p);
		}
		
		for(int i = 0; i<possiblePositions.size(); i++)
		{
			if(Board.getBoard().sqrState(possiblePositions.get(i)) != 0)
			{
				possiblePositions.remove(i);
			}
		}
	}
}