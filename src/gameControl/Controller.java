package gameControl;

import java.util.Observer;

public class Controller {
	
	private static Controller ctrl = null;
	
	private int turn = 1;
	
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
	*     
	*  Return values:
	*     1, all right
	*     0, unforbidden move
	*     -1, changed the piece
	*/
	public int mouseClicked(Position clickPos, Position selectedPiece, int clickType)
	{
		if(clickType == 1)
		{
			if(Board.getBoard().sqrState(clickPos) != turn )
				if(Board.getBoard().click(clickPos, selectedPiece))
				{
					turn = turn * (-1);
					return 1;
				}
				else
					return 0;
			return -1;
		}
		else
		{
			if(Board.getBoard().sqrState(selectedPiece) == turn )
			{
				Board.getBoard().selectedPiece(selectedPiece);
				return 1;		
			}
			
			return 0;			
		}
	}
	
	public int getTurn()
	{
		return turn;
	}
}
