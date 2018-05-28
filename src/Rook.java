
public class Rook extends Piece{
	
	public boolean checkMovementTo(int column, int row){
		if(m_column == column) {
			return true;
		}
		else if(m_row == row) {
			return true;
		}
		
		return false;
	}
}
