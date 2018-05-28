import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;

public class Board extends JPanel {
	private final int SIZE=800;
	private final int SQUARESIDE = SIZE/8;
	
	private static Board board = null;
	
	private Board()
	{
		
	}
	
	public static Board getBoard() {
		if(board == null)
			board = new Board();
		return board;
	}
	
	public int sqrState(Position sqrPos)
	{
		return 0;
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		
		int i, j;
		
		for(i = 0; i < 8; i++) {
			for(j = 0; j < 8; j++) {
				Rectangle2D rt = new Rectangle2D.Double(i*SQUARESIDE,j*SQUARESIDE,SQUARESIDE,SQUARESIDE);
				
				g2d.setPaint(Color.WHITE);
				if(i%2==0){
					if(j%2!=0) {
						g2d.setPaint(Color.BLACK);
					}
				}
				else{
					if(j%2==0) {
						g2d.setPaint(Color.BLACK);
					}
				}
				
				g2d.fill(rt);
			}
		}
		
	}
}