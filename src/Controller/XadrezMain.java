package Controller;

import javax.swing.JFrame;

public class XadrezMain {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(800, 800);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(Board.getBoard());
	}

}
