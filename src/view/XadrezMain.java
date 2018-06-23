package view;

import javax.swing.JFrame;

public class XadrezMain {

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.setSize(615, 640);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new GameView(600));
		frame.setVisible(true);
	}

}
