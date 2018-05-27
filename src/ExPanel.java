import javax.swing.JPanel;
import java.awt.*;
import java.awt.geom.*;

public class ExPanel extends JPanel {
	public static final int TXT_X=120;
	public static final int TXT_Y=140;
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d=(Graphics2D) g;
		
		int i, j;
		
		for(i = 0; i < 8; i++) {
			for(j = 0; j < 8; j++) {
				Rectangle2D rt = new Rectangle2D.Double(i*100,j*100,100,100);
				
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