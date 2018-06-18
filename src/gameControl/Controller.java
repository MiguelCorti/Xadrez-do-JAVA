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
	
	public void mouseClicked(Position clickPos)
	{
		//Board.getBoard().click();
	}
}
