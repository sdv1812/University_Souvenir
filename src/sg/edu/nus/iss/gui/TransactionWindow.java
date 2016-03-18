package iss.tranasction.pos;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

public class TransactionWindow  extends JFrame{

	storeApplication manager;
	cartPanel cartPanel;
	ProductPanel productPanel;
	private WindowListener windowListener = new WindowAdapter () {
        public void windowClosing (WindowEvent e) {
            manager.shutdown ();
        }
	
};
public TransactionWindow(storeApplication manager){
	super("Transaction");
	this.manager=manager;
	productPanel = new ProductPanel(manager);
	cartPanel = new cartPanel(manager);
}
public void refresh(){
	productPanel.refresh();

}
public void refreshProducts(){
	productPanel.refresh();
}
}

