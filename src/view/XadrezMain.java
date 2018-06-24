package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

import gameControl.Board;

public class XadrezMain {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(615, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GameView gv = new GameView(600);
		
		
		JButton newGame = new JButton("Novo jogo");
		newGame.setBounds(150, 150, 300, 70);
		frame.add(newGame);
		
		JButton continueGame = new JButton("Continuar Partida");
		continueGame.setBounds(150, 350, 300, 70);
		frame.add(continueGame);
		
		
		newGame.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				frame.remove(newGame);
				frame.remove(continueGame);
				gv.setVisible(true);
				frame.repaint();
			}
		});
		
		
		continueGame.addActionListener(new ActionListener(){  
			public void actionPerformed(ActionEvent e){
				
				JFileChooser jfc = new JFileChooser();
				int returnValue = jfc.showOpenDialog(null);
				if(returnValue == JFileChooser.APPROVE_OPTION)
				{
					
					String filePath = jfc.getSelectedFile().getPath();
					int size = jfc.getSelectedFile().getPath().length();
					if( jfc.getSelectedFile().getPath().substring(size-3).equals("txt") )
					{
						
				        // This will reference one line at a time
				        String line = null;
				        ArrayList<String> gamePieces = new ArrayList<String>();

				        try {
				            // FileReader reads text files in the default encoding.
				            FileReader fileReader = new FileReader(filePath);

				            // Always wrap FileReader in BufferedReader.
				            BufferedReader bufferedReader = new BufferedReader(fileReader);

				            while((line = bufferedReader.readLine()) != null) {
				                gamePieces.add(line);
				            }

				            bufferedReader.close();
				            
				            Board.getBoard().continueGame(gamePieces);
				            gv.continueGame(gamePieces);
				            
				            frame.remove(newGame);
							frame.remove(continueGame);
							gv.setVisible(true);
							frame.repaint();
				        }
				        catch(FileNotFoundException ex) {
				            System.out.println(
				                "Unable to open file '" + 
				                filePath + "'");                
				        }
				        catch(IOException ex) {
				            System.out.println(
				                "Error reading file '" 
				                + filePath + "'"); 
				            // ex.printStackTrace();
				        }
				        
					}
				}
				
			}
		});
		
		
		gv.setVisible(false);
		frame.getContentPane().add(gv);
		
		
		
		//frame.setLayout(new CardLayout()); 
		frame.setVisible(true);
	}

}
