package sg.edu.nus.iss.gui;

import java.awt.event.WindowAdapter;
import sg.edu.nus.iss.store.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

public class TransactionWindow  extends JFrame{

	StoreApplication manager;
	CartPanel cartPanel;
	ProductPanel productPanel;
	private WindowListener windowListener = new WindowAdapter () {
        public void windowClosing (WindowEvent e) {
            manager.shutdown ();
        }
	
};
public TransactionWindow(StoreApplication manager){
	super("Transaction");
	this.manager=manager;
	productPanel = new ProductPanel(manager);
	cartPanel = new CartPanel(manager);
}
public void refresh(){
	productPanel.refresh();

}
public void refreshProducts(){
	productPanel.refresh();
}
public void refreshCart(){
	cartPanel.refresh();
}
public ArrayList<Cart> getCartItems(){
			return cartPanel.getSelectedProducts();
}
}

