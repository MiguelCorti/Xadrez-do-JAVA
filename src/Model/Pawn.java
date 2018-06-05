package Model;
import Controller.Board;

public class Pawn extends Piece{

	public Pawn(int row, int column, int color) {
		super(row, column, color);
	}

	protected void updatePossiblePositions() {
		possiblePositions.clear();
		
		Position p = new Position();
		
		if(p.set( m_pos.getColumn(), m_pos.getRow()+getColor() ))
			possiblePositions.add(p);
		
		if(m_pos.getRow() == 1 || m_pos.getRow()==2 || m_pos.getRow()==7 || m_pos.getRow()==8)
		{
			if(p.set( m_pos.getColumn(), m_pos.getRow()+2*getColor()))
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
