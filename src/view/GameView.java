package view;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

import gameControl.Bishop;
import gameControl.Controller;
import gameControl.King;
import gameControl.Knight;
import gameControl.Pawn;
import gameControl.Position;
import gameControl.Queen;
import gameControl.Rook;

public class GameView extends JPanel implements Observer {
	private int SIZE;
	private int SQUARESIDE;
	
	private Image[][] imagesBoard = new Image[9][9];
	private Color[][] squareColor = new Color[9][9];
	
	public GameView(int screenSize)
	{
		Controller.getInstance().register(this);
		SIZE = screenSize;
		SQUARESIDE = SIZE/8;
		
		initializeImages();
	
		nullifySquareColor();
		
		addMouseListener(new MouseAdapter() {
			boolean pieceIsSelected = false;
			int i, j;
			Position selectedPiecePos;
			
            public void mouseClicked(MouseEvent e) {
               
            	Position clickedPos = mapCoordToMatrix(e.getY(), e.getX());
            	clickedPos.print();
            	if(pieceIsSelected)
            	{
            		int returnValue = Controller.getInstance().mouseClicked(clickedPos, selectedPiecePos, 1);
            		if( returnValue != 1)
            		{
            			nullifySquareColor();
            			repaint();
            		}
            		if(returnValue == -1)
            		{
            			i = clickedPos.getRow();
                		j = clickedPos.getColumn();
                		
                		if(imagesBoard[i][j] != null)
                		{
                			pieceIsSelected = true;
                			selectedPiecePos = clickedPos;
                			Controller.getInstance().mouseClicked(null, selectedPiecePos, 0);
                		}
            		}
            		else
            			pieceIsSelected = false;
            	}
            	else
            	{
            		i = clickedPos.getRow();
            		j = clickedPos.getColumn();
            		
            		if(imagesBoard[i][j] != null)
            		{
            			
            			selectedPiecePos = clickedPos;
            			if(Controller.getInstance().mouseClicked(null, selectedPiecePos, 0) == 1)
            				pieceIsSelected = true;
            			else
            				pieceIsSelected = false;
            		}
            	}
            }
        });
	}
	
	// The function used by the Observable (board) to notify this view that changes occurred
	@Override
	public void update(Observable arg0, Object arg1) {
		String descriptorTemp = (String) arg1;
		char[] descriptor = descriptorTemp.toCharArray();
		Position square;
		int i = 0;
		
		while(descriptor[i] != '\0')
		{
			switch(descriptor[i]) {
				case 'p':
					Position original = new Position(Character.getNumericValue(descriptor[i+1]), Character.getNumericValue(descriptor[i+2]));
					Position destination = new Position(Character.getNumericValue(descriptor[i+3]), Character.getNumericValue(descriptor[i+4]));
					
					imagesBoard[destination.getRow()][ destination.getColumn()] = imagesBoard[original.getRow()][original.getColumn()];
					imagesBoard[original.getRow()][original.getColumn()] = null;
					
					nullifySquareColor();
					
					break;
				case 'g':
					square = new Position(Character.getNumericValue(descriptor[i+1]), Character.getNumericValue(descriptor[i+2]));
					
					squareColor[square.getRow()][square.getColumn()] = Color.GREEN;
					
					break;
				case 'r':
					square = new Position(Character.getNumericValue(descriptor[i+1]), Character.getNumericValue(descriptor[i+2]));
					
					squareColor[square.getRow()][square.getColumn()] = Color.RED;
					break;
				case 'P':
					JPopupMenu promotionPopup;
					promotionPopup = new JPopupMenu();
					System.out.println("PROMACAO");
					break;
			}
			
			i++;
		}
		
		repaint();
	}

	private void nullifySquareColor(){
		for(int i = 1; i < 9; i++)
			for(int j = 1; j < 9; j++) 			
				squareColor[i][j] = null;
	}
	private void initializeImages(){
		
		try {
			// Filling Black Pieces at start position
			imagesBoard[1][1] = ImageIO.read(new File("assets\\p_torre.gif"));
			imagesBoard[1][2] = ImageIO.read(new File("assets\\p_cavalo.gif"));
			imagesBoard[1][3] = ImageIO.read(new File("assets\\p_bispo.gif"));
			imagesBoard[1][4] = ImageIO.read(new File("assets\\p_dama.gif"));
			imagesBoard[1][5] = ImageIO.read(new File("assets\\p_rei.gif"));
			imagesBoard[1][6] = ImageIO.read(new File("assets\\p_bispo.gif"));
			imagesBoard[1][7] = ImageIO.read(new File("assets\\p_cavalo.gif"));
			imagesBoard[1][8] = ImageIO.read(new File("assets\\p_torre.gif"));
			
			imagesBoard[2][1] = ImageIO.read(new File("assets\\p_peao.gif"));
			imagesBoard[2][2] = ImageIO.read(new File("assets\\p_peao.gif"));
			imagesBoard[2][3] = ImageIO.read(new File("assets\\p_peao.gif"));
			imagesBoard[2][4] = ImageIO.read(new File("assets\\p_peao.gif"));
			imagesBoard[2][5] = ImageIO.read(new File("assets\\p_peao.gif"));
			imagesBoard[2][6] = ImageIO.read(new File("assets\\p_peao.gif"));
			imagesBoard[2][7] = ImageIO.read(new File("assets\\p_peao.gif"));
			imagesBoard[2][8] = ImageIO.read(new File("assets\\p_peao.gif"));
			
			// Filling White Pieces at start position
			imagesBoard[8][1] = ImageIO.read(new File("assets\\b_torre.gif"));
			imagesBoard[8][2] = ImageIO.read(new File("assets\\b_cavalo.gif"));
			imagesBoard[8][3] = ImageIO.read(new File("assets\\b_bispo.gif"));
			imagesBoard[8][4] = ImageIO.read(new File("assets\\b_dama.gif"));
			imagesBoard[8][5] = ImageIO.read(new File("assets\\b_rei.gif"));
			imagesBoard[8][6] = ImageIO.read(new File("assets\\b_bispo.gif"));
			imagesBoard[8][7] = ImageIO.read(new File("assets\\b_cavalo.gif"));
			imagesBoard[8][8] = ImageIO.read(new File("assets\\b_torre.gif"));
			
			imagesBoard[7][1] = ImageIO.read(new File("assets\\b_peao.gif"));
			imagesBoard[7][2] = ImageIO.read(new File("assets\\b_peao.gif"));
			imagesBoard[7][3] = ImageIO.read(new File("assets\\b_peao.gif"));
			imagesBoard[7][4] = ImageIO.read(new File("assets\\b_peao.gif"));
			imagesBoard[7][5] = ImageIO.read(new File("assets\\b_peao.gif"));
			imagesBoard[7][6] = ImageIO.read(new File("assets\\b_peao.gif"));
			imagesBoard[7][7] = ImageIO.read(new File("assets\\b_peao.gif"));
			imagesBoard[7][8] = ImageIO.read(new File("assets\\b_peao.gif"));
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		
		
		// Fill empty squares with null
		for(int i = 3; i < 7; i++) {
			for(int j = 1; j < 9; j++) {
				imagesBoard[i][j] = null;
			}
		}
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

		for(i = 1; i < 9; i++) {
			for(j = 1; j < 9; j++) {
				Rectangle2D rt = new Rectangle2D.Double((j-1)*SQUARESIDE,(i-1)*SQUARESIDE,SQUARESIDE,SQUARESIDE);
				
				if(squareColor[i][j] == null) {
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
				}
				else {
					g2d.setPaint(squareColor[i][j]);
				}
				g2d.fill(rt);
			}
		}
		
		for(i = 1; i < 9; i++) {
            for(j = 1; j < 9; j++) {
				if(imagesBoard[i][j] != null){
					if(g2d.drawImage(imagesBoard[i][j], (j - 1) * SQUARESIDE, (i - 1) * SQUARESIDE, 
					   SQUARESIDE, SQUARESIDE, null) == false) {
						System.out.println("Erro ao desenhar a seguinte imagem de posicao (" + i + ", " + j + ").");
					}
				}
			}
		}
		
		//g2d.dispose();
	}

}
