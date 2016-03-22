package sg.edu.nus.iss.gui;

import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTable;

import sg.edu.nus.iss.utils.ConfirmDialog;
import sg.edu.nus.iss.store.Member;

public class MemberPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private StoreApplication manager;
	private JScrollPane scroller;
	private JTable table;
	private static final String[] COLUMN_NAMES = {"Name", "ID", "Loyalty Points"};
	private AbstractTableModel productTableModel;
	private ArrayList<Member> members ;
	private MemberPanel mp;
	private String action_source;

	public MemberPanel(StoreApplication manager) {
		this.manager = manager;
		mp = this;
		setLayout (new BorderLayout());
		members = manager.getMembers();
		add(createButtonPanel(), BorderLayout.EAST);
		add (new JLabel ("Products"), BorderLayout.NORTH);
		table = new JTable();	
		table.setModel(getTableModel());
		productTableModel.fireTableDataChanged();
		scroller = new JScrollPane(table); //scroller automatically puts the table header at the top
		table.setFillsViewportHeight(true); // true : table uses the entire height of the container, even if the table doesn't have enough rows to use the whole vertical space. 
		add(scroller, BorderLayout.CENTER);

	}
	private JPanel createButtonPanel () {

		JPanel p = new JPanel (new GridLayout (0, 1));

		JButton b = new JButton ("Add");
		b.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				action_source  =((JButton)e.getSource()).getText();
				AddMemberDialog d = new AddMemberDialog (manager, mp);
				d.setModal(true);
				d.setLocationRelativeTo(manager.getMainWindow());
				d.pack();
				d.setVisible (true);
			}
		});
		p.add (b);

		b = new JButton ("Remove");
		b.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				String s =null;
				action_source  =(((JButton)e.getSource()).getText());
				if(table.getSelectedRow()!=-1){ //If no row is selected

					for(int i = 0 ; i<table.getColumnCount() ; i++)
					{
						if(table.getColumnName(i).equalsIgnoreCase("ID")){
							s = (String)table.getValueAt(table.getSelectedRow(), i);
							showConfirmDialog(s);
						}
					}
				} else {
					JOptionPane.showMessageDialog(mp,
		                    "Please select a row to remove!",
		                    "Select a Row",
		                    JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		p.add (b);

		JButton backBtn = new JButton("Back");
		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action_source = (((JButton)e.getSource()).getText());
				refresh();
			}
		});
		JPanel bp = new JPanel ();
		bp.setLayout (new BorderLayout());
		bp.add ("North", p);
		bp.add("South", backBtn);


		return bp;
	}

	public TableModel getTableModel() {
		if (productTableModel != null) 
			return productTableModel;
		else {
			productTableModel = new AbstractTableModel() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;

				@Override
				public String getColumnName(int column) {
					return COLUMN_NAMES[column];
				}

				@Override
				public int getRowCount() {
					return members.size();
				}

				@Override
				public int getColumnCount() {
					return COLUMN_NAMES.length;
				}

				@Override
				public Object getValueAt(int rowIndex, int columnIndex) {
					Member member = members.get(rowIndex);
					switch (columnIndex) {
					case 0: return member.getCustomerName();
					case 1: return member.getMemberID();
					case 2: return member.getLoyaltyPoints();
					default: return null;
					}
				}
			};

			return productTableModel;
		}
	}
	public void refresh(){   //Reflect the changes done on the screen using buttons
		if(action_source.equalsIgnoreCase("Add")){
			int rowIndex = members.size()-1;
			productTableModel.fireTableRowsInserted(rowIndex, rowIndex);
		}
		else if(action_source.equalsIgnoreCase("Remove")){
			productTableModel.fireTableRowsDeleted(table.getSelectedRow(), table.getSelectedRow());
		}

		else if(action_source.equalsIgnoreCase("Back")){
			removeAll();
			add("Center",manager.createMainPanel());
			revalidate();
			repaint();
		}

	}
	
	public void showConfirmDialog(String s) { // Show the confirm dialog on removing and performs the remove functionality
        String title = "Remove Member";
        String msg = "Do you really want to remove member " + s + " ?";
        ConfirmDialog d = new ConfirmDialog (manager.getMainWindow(), title, msg) {

			private static final long serialVersionUID = 1L;

			protected boolean performOkAction () {
				manager.removeMember(s);
				refresh();
                return true;
            }
        };
        d.setModal(true);
        d.setLocationRelativeTo(this);
        d.pack();
        d.setVisible (true);
	}

}
