import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

public class Board extends JPanel {
	private final int SIZE=800;
	private final int SQUARESIDE = SIZE/8;
	
	private Piece[][] bMatrix = new Piece[8][8];
	
	private static Board board = null;
	
	private Board()
	{
		// White Pieces
		bMatrix[0][0] = new Rook(1, 1, 1);
		bMatrix[0][1] = new Knight(1, 2, 1);
		bMatrix[0][2] = new Bishop(1, 3, 1);
		bMatrix[0][3] = new Queen(1, 4, 1);
		bMatrix[0][4] = new King(1, 5, 1);
		bMatrix[0][5] = new Bishop(1, 6, 1);
		bMatrix[0][6] = new Knight(1, 7, 1);
		bMatrix[0][7] = new Rook(1, 8, 1);
		
		bMatrix[1][0] = new Pawn(2, 1, 1);
		bMatrix[1][1] = new Pawn(2, 2, 1);
		bMatrix[1][2] = new Pawn(2, 3, 1);
		bMatrix[1][3] = new Pawn(2, 4, 1);
		bMatrix[1][4] = new Pawn(2, 5, 1);
		bMatrix[1][5] = new Pawn(2, 6, 1);
		bMatrix[1][6] = new Pawn(2, 7, 1);
		bMatrix[1][7] = new Pawn(2, 8, 1);
		
		// Black Pieces
		bMatrix[7][0] = new Rook(8, 1, -1);
		bMatrix[7][1] = new Knight(8, 2, -1);
		bMatrix[7][2] = new Bishop(8, 3, -1);
		bMatrix[7][3] = new Queen(8, 4, -1);
		bMatrix[7][4] = new King(8, 5, -1);
		bMatrix[7][5] = new Bishop(8, 6, -1);
		bMatrix[7][6] = new Knight(8, 7, -1);
		bMatrix[7][7] = new Rook(8, 8, -1);
		
		bMatrix[6][0] = new Pawn(7, 1, -1);
		bMatrix[6][1] = new Pawn(7, 2, -1);
		bMatrix[6][2] = new Pawn(7, 3, -1);
		bMatrix[6][3] = new Pawn(7, 4, -1);
		bMatrix[6][4] = new Pawn(7, 5, -1);
		bMatrix[6][5] = new Pawn(7, 6, -1);
		bMatrix[6][6] = new Pawn(7, 7, -1);
		bMatrix[6][7] = new Pawn(7, 8, -1);
		
		for(int i = 2; i < 6; i++) {
			for(int j = 0; j < 8; j++) {
				bMatrix[i][j] = null;
			}
		}
		
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
		Graphics2D g2d = (Graphics2D) g;
		
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