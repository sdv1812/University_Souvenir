package sg.edu.nus.iss.gui;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Koushik Radhakrishnan - TransactionProduct panel - Processes Product details
 *
 */
public class TransactionProductPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	StoreApplication manager;
	private JTextField memberId;
	private JTextField quantity;
	private JTextField productId;

	public TransactionProductPanel(StoreApplication manager) {
		this.manager = manager;
		productId = new JTextField(10);
		quantity = new JTextField(10);
		memberId = new JTextField(10);
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout(FlowLayout.LEFT));
		p.add(new JLabel("ProductId"));
		p.add(productId);
		p.add(new JLabel("QTY"));
		p.add(quantity);
		p.add(new JLabel("Member id "));
		p.add(memberId);
		add("North", p);
		add("East", createButtonPanel());

	}

	private JPanel createButtonPanel() {
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(0, 1));
		JButton b;
		ActionListener l;

		b = new JButton("Add Products");
		l = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String productIdentity = "";
				int Quantity = 0;
				String memberIdentity = "";
				try {
					productIdentity = productId.getText();
					Quantity = Integer.parseInt(quantity.getText());
					memberIdentity = memberId.getText();
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "InvalidDetails", "Enter valid format",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				if (productIdentity.length() == 0 || Quantity <= 0) {
					JOptionPane.showMessageDialog(null, "InvalidDetails", "InvalidDetails", JOptionPane.ERROR_MESSAGE);
					return;
				}
				boolean addProductStatus = manager.addProductsToCart(manager.getProductByID(productIdentity), Quantity,
						manager.getMember(memberIdentity));
				if (!addProductStatus) {
					JOptionPane.showMessageDialog(null, "Invalid Product detail", "No Product found",
							JOptionPane.ERROR_MESSAGE);
				}
				productId.setText("");
				quantity.setText("");
			}
		};
		b.addActionListener(l);
		p.add(b);
		return p;

	}
}
