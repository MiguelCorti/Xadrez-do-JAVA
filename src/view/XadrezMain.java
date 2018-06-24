package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.filechooser.FileSystemView;

public class XadrezMain {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(615, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel gv = new GameView(600);
		
		
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
					//arquivo escolhido
				}
				System.out.println(jfc.getSelectedFile());
			}
		});
		
		
		gv.setVisible(false);
		frame.getContentPane().add(gv);
		
		
		
		//frame.setLayout(new CardLayout()); 
		frame.setVisible(true);
	}

}
