package sg.edu.nus.iss.gui;

import java.awt.*;
import javax.swing.*;

public class ISSLogoPanel extends JPanel{

	

	/*public ISSLogoPanel() {
		try {                
			image = ImageIO.read(new File("StoreAppData/NUS_ISS-logo.jpg"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}*/

	@Override
	protected void paintComponent(Graphics g) {
		//super.paintComponent(g);
		Image image = new ImageIcon("StoreAppData/NUS_ISS-logo.jpg").getImage();
		g.drawImage(image, 0, 0, this); // see javadoc for more info on the parameters            
	}

}

