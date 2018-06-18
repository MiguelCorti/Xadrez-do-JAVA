package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import gameControl.Controller;
import gameControl.Position;

public class GameView extends JPanel implements Observer {
	private int SIZE;
	private int SQUARESIDE;
	
	private Image[][] imagesBoard = new Image[9][9]; //Será que isso funciona???
	
	public GameView(int screenSize)
	{
		
		Controller.getInstance().register(this);
		SIZE = screenSize;
		SQUARESIDE = SIZE/8;
		
		addMouseListener(new MouseAdapter() {
			boolean pieceSelected = false;
			Position posSelected;
			
            public void mouseClicked(MouseEvent e) {
               
            	Position p = mapCoordToMatrix(e.getY(), e.getX());
            	p.print();
            	if(pieceSelected)
            	{
   
            		/*Piece myPiece = bMatrix[posSelected.getRow()][posSelected.getColumn()];
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
            		
            		repaint();*/
            	}
            	else
            	{
            		/*if(sqrState(p)==1)
            		{
            			pieceSelected = true;
            			posSelected = p;
            		}*/
            	}
            }
        });
	}
	
	// The function used by the Observable (board) to notify this view that changes occurred
	@Override
	public void update(Observable arg0, Object arg1) {
		repaint();
	}
	
	// Auxiliary function for mapping the clicked screen position to an actual integer position
	private Position mapCoordToMatrix(float x, float y)
	{
		Position p = new Position((int) x/SQUARESIDE +1, (int) y/SQUARESIDE +1);
		return p;
	}
	

	// Graphics function for painting the panel
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
		
		/*for(i = 1; i < 9; i++) {
            for(j = 1; j < 9; j++) {
				if(imagesBoard[i][j] != null){
					Image img = null;
					String imgName;
					String imgType = "";
					String imgColor;
					
					// Guardando a letra do arquivo que referencia a cor
					if(imagesBoard[i][j].getColor() == 1)
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
		
		//g2d.dispose();*/
	}

}
