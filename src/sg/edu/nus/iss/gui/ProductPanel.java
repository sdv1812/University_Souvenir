package sg.edu.nus.iss.gui;

import javax.swing.JPanel;

import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.BorderLayout;

public class ProductPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ProductPanel() {
		setLayout(new BorderLayout(0, 0));
		JButton addProductBtn = new JButton("Add Product");
		JPanel panel = new JPanel();
		add(panel, BorderLayout.EAST);
		panel.setLayout(new GridLayout(0, 1, 0, 50));
		panel.add(addProductBtn);
		
		JButton btnRemoveProduct = new JButton("Remove Product");
		panel.add(btnRemoveProduct);
		
		JList list = new JList();
		add(list, BorderLayout.CENTER);
	}
	private JPanel createButtonPanel () {

        JPanel p = new JPanel (new GridLayout (0, 1));

        JButton b = new JButton ("Add");
        b.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
               //AddMemberDialog d = new AddMemberDialog (manager);
                //d.pack();
                //d.setVisible (true);
            }
        });
        p.add (b);

        b = new JButton ("Remove");
        b.addActionListener (new ActionListener () {
            public void actionPerformed (ActionEvent e) {
                //manager.removeSelectedMember();
            }
        });
        p.add (b);

        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North", p);
        return bp;
    }

}
