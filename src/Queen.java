
public class Queen extends Piece{
	public int m_horizontalSquare;
	public int m_verticalSquare;
	
	
	public boolean checkMovementTo(int horizontalSquare, int verticalSquare){
		if(m_horizontalSquare == horizontalSquare || m_verticalSquare == verticalSquare) {
			return true;
		}
		
		return false;
	}
}
