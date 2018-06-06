package Controller;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import Model.Bishop;
import Model.King;
import Model.Knight;
import Model.Pawn;
import Model.Piece;
import Model.Position;
import Model.Queen;
import Model.Rook;

public class Board extends JPanel {
	private final int SIZE=800;
	private final int SQUARESIDE = SIZE/8;
	
	private Piece[][] bMatrix = new Piece[9][9];
	
	private static Board board = null;
	
	private Position mapCordToMatrix(float x, float y)
	{
		Position p = new Position((int) x/SQUARESIDE +1, (int) y/SQUARESIDE +1);
		return p;
	}
	
	private Board()
	{
		// Black Pieces
		bMatrix[1][1] = new Rook(1, 1, -1);
		bMatrix[1][2] = new Knight(1, 2, -1);
		bMatrix[1][3] = new Bishop(1, 3, -1);
		bMatrix[1][4] = new Queen(1, 4, -1);
		bMatrix[1][5] = new King(1, 5, -1);
		bMatrix[1][6] = new Bishop(1, 6, -1);
		bMatrix[1][7] = new Knight(1, 7, -1);
		bMatrix[1][8] = new Rook(1, 8, -1);
		
		bMatrix[2][1] = new Pawn(2, 1, -1);
		bMatrix[2][2] = new Pawn(2, 2, -1);
		bMatrix[2][3] = new Pawn(2, 3, -1);
		bMatrix[2][4] = new Pawn(2, 4, -1);
		bMatrix[2][5] = new Pawn(2, 5, -1);
		bMatrix[2][6] = new Pawn(2, 6, -1);
		bMatrix[2][7] = new Pawn(2, 7, -1);
		bMatrix[2][8] = new Pawn(2, 8, -1);
		
		// White Pieces
		bMatrix[8][1] = new Rook(8, 1, 1);
		bMatrix[8][2] = new Knight(8, 2, 1);
		bMatrix[8][3] = new Bishop(8, 3, 1);
		bMatrix[8][4] = new Queen(8, 4, 1);
		bMatrix[8][5] = new King(8, 5, 1);
		bMatrix[8][6] = new Bishop(8, 6, 1);
		bMatrix[8][7] = new Knight(8, 7, 1);
		bMatrix[8][8] = new Rook(8, 8, 1);
		
		bMatrix[7][1] = new Pawn(7, 1, 1);
		bMatrix[7][2] = new Pawn(7, 2, 1);
		bMatrix[7][3] = new Pawn(7, 3, 1);
		bMatrix[7][4] = new Pawn(7, 4, 1);
		bMatrix[7][5] = new Pawn(7, 5, 1);
		bMatrix[7][6] = new Pawn(7, 6, 1);
		bMatrix[7][7] = new Pawn(7, 7, 1);
		bMatrix[7][8] = new Pawn(7, 8, 1);
		
		for(int i = 3; i < 7; i++) {
			for(int j = 1; j < 9; j++) {
				bMatrix[i][j] = null;
			}
		}
		
		addMouseListener(new MouseAdapter() {
			boolean pieceSelected = false;
			Position posSelected;
			
            public void mouseClicked(MouseEvent e) {
               
            	Position p = mapCordToMatrix(e.getY(), e.getX());
            	p.print();
            	if(pieceSelected)
            	{
   
            		Piece myPiece = bMatrix[posSelected.getRow()][posSelected.getColumn()];
            		myPiece.moveTo(p);
            		int row = myPiece.getM_pos().getRow();
            		int col = myPiece.getM_pos().getColumn();
            		System.out.println();
            		bMatrix[posSelected.getRow()][posSelected.getColumn()] = null;
            		bMatrix[row][col] = myPiece;
            		pieceSelected = false;
            		
            		for(int i = 1; i < 9; i++) {
                        for(int j = 1; j < 9; j++) {
                        	if(bMatrix[i][j] != null)
                        		bMatrix[i][j].updatePossiblePositions();
                        }
            		}
            		
            		repaint();
            	}
            	else
            	{
            		if(sqrState(p)==1)
            		{
            			pieceSelected = true;
            			posSelected = p;
            		}
            	}
            }
        });

	}
	
	public static Board getBoard() {
		if(board == null)
			board = new Board();
		return board;
	}
	
	public int sqrState(Position sqrPos)
	{
		if(bMatrix[sqrPos.getRow()][sqrPos.getColumn()] == null)
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	private void printBMatrix()
    {

        for(int i =1; i<9; i++)
        {
            for(int j = 1; j<9; j++)
            {
                if(bMatrix[i][j]==null)
                    System.out.print(" 0 ");
                else
                {
                    if(bMatrix[i][j] instanceof Rook)
                        System.out.print(" t ");
                    else if(bMatrix[i][j] instanceof Bishop)
                        System.out.print(" b ");
                    else if(bMatrix[i][j] instanceof King)
                        System.out.print(" r ");
                    else if(bMatrix[i][j] instanceof Knight)
                        System.out.print(" c ");
                    else if(bMatrix[i][j] instanceof Pawn)
                        System.out.print(" p ");
                    else if(bMatrix[i][j] instanceof Queen)
                        System.out.print(" d ");
                }
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
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
				
		for(i = 1; i < 9; i++) {
            for(j = 1; j < 9; j++) {
				if(bMatrix[i][j] != null){
					Image img = null;
					String imgName;
					String imgType = "";
					String imgColor;
					
					// Guardando a letra do arquivo que referencia a cor
					if(bMatrix[i][j].getColor() == 1)
						imgColor = "b";
					else
						imgColor = "p";
					
					// Quardando os nomes referentes ao arquivo de cada peça
					if(bMatrix[i][j] instanceof Rook)
						imgType = "torre";
					else if(bMatrix[i][j] instanceof Bishop)
						imgType = "bispo";
					else if(bMatrix[i][j] instanceof King)
						imgType = "rei";
					else if(bMatrix[i][j] instanceof Knight)
						imgType = "cavalo";
					else if(bMatrix[i][j] instanceof Pawn)
						imgType = "peao";
					else if(bMatrix[i][j] instanceof Queen)
						imgType = "dama";
					
					
					imgName = "assets\\"  + imgColor + '_' + imgType + ".gif";
					try {
						img = ImageIO.read(new File(imgName));
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					Position piecePos = bMatrix[i][j].getM_pos();

					if(g2d.drawImage(img, (piecePos.getColumn() - 1) * SQUARESIDE, (piecePos.getRow() - 1) * SQUARESIDE, 
							SQUARESIDE, SQUARESIDE, null) == false) {
						System.out.println("Erro ao desenhar a seguinte imagem: " + imgName);
					}
				}
			}
		}
		
		//g2d.dispose();
		
	}
}