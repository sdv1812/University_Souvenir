package sg.edu.nus.iss.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.BorderLayout;
import javax.swing.JTable;

public class ProductPanel extends JPanel {
	private StoreApplication manager;
	private JScrollPane scroller;
	private JTable table;
	

	/**
	 * Create the panel.
	 */
	public ProductPanel(StoreApplication manager) {
		this.manager = manager;
		setLayout (new BorderLayout());
		
		add(createButtonPanel(), BorderLayout.EAST);
		add (new JLabel ("Products"), BorderLayout.NORTH);
		
		String[] columnNames = {"First Name",
                "Last Name",
                "Sport",
                "# of Years",
                "Vegetarian"};
		Object[][] data = {
			    {"Kathy", "Smith",
			     "Snowboarding", new Integer(5), new Boolean(false)},
			    {"John", "Doe",
			     "Rowing", new Integer(3), new Boolean(true)},
			    {"Sue", "Black",
			     "Knitting", new Integer(2), new Boolean(false)},
			    {"Jane", "White",
			     "Speed reading", new Integer(20), new Boolean(true)},
			    {"Joe", "Brown",
			     "Pool", new Integer(10), new Boolean(false)}
			};
		table = new JTable(data, columnNames);
		
		scroller = new JScrollPane(table); //scroller automatically puts the table header at the top
		table.setFillsViewportHeight(true); // true : table uses the entire height of the container, even if the table doesn't have enough rows to use the whole vertical space. 

		add(scroller, BorderLayout.CENTER);
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
        
        JButton backBtn = new JButton("Back");
        backBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		removeAll();
        		//manager.removeLoginPanel();
        		add("Center",manager.createMainPanel());
        		//manager.refreshLoginPanel();
        		revalidate();
        		repaint();
        	}
        });
        JPanel bp = new JPanel ();
        bp.setLayout (new BorderLayout());
        bp.add ("North", p);
        bp.add("South", backBtn);
        
        
        return bp;
    }

}
