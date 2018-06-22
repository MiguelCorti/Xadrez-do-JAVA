package gameControl;

import java.util.Observer;

public class Controller {
	
	private static Controller ctrl = null;
	
	public static Controller getInstance()
	{
		if(ctrl == null)
			ctrl = new Controller();
		return ctrl;
	}
	
	public void register(Observer o) {
		Board.getBoard().addObserver(o);
	}
	
	/* Notifies the board controller that a click has happened
	*  Params:
	*     1) clickPos: The destination position of the selected piece;
	*     2) selectedPiece: The piece selected for movimentation;
	*     3) clickType: If it's the first click (0) or the second (1).
	*/
	public boolean mouseClicked(Position clickPos, Position selectedPiece, int clickType)
	{
		if(clickType == 1)
			return Board.getBoard().click(clickPos, selectedPiece);
		
		Board.getBoard().selectedPiece(selectedPiece);
		return true;
	}
}
