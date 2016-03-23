package sg.edu.nus.iss.gui;

import javax.swing.JPanel;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;

import sg.edu.nus.iss.utils.ConfirmDialog;
import sg.edu.nus.iss.store.Member;

public class MemberPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private StoreApplication manager;
	private JScrollPane scroller;
	private JTable table;
	private static final String[] COLUMN_NAMES = {"Name", "ID", "Loyalty Points"};
	private AbstractTableModel memberTableModel;
	private ArrayList<Member> members ;
	private String action_source;
	private JTextField mNameT;
	private JTextField mIdT;
	private Border raisedetched;
	private Border loweredetched; 

	public MemberPanel(StoreApplication manager) {
		this.manager = manager;
		setLayout (new BorderLayout());
		members = manager.getMembers();
		raisedetched = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
		loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED); 

		setLayout (new BorderLayout());
		add(createButtonPanel(), BorderLayout.EAST);
		add(showMemberListPanel(), BorderLayout.CENTER);
		add(createAddMemberPanel(), BorderLayout.SOUTH);

	}
	
	//*****************To create add new member Panel**********************

	private JPanel createAddMemberPanel () { 

		JPanel panel = new JPanel(new GridLayout(1, 0, 10, 0));

		JLabel label = new JLabel("New Member : ");
		label.setFont(new Font("Tahoma", Font.BOLD, 12 ));
		JLabel mName = new JLabel("Member Name: ");
		mNameT = new JTextField();
		JLabel mId = new JLabel("Student/Staff ID: ");
		mIdT = new JTextField();

		panel.add(label);
		panel.add(mName);
		panel.add(mNameT);
		panel.add(mId);
		panel.add(mIdT);

		JButton addBtn = new JButton ("Add");
		addBtn.addActionListener (new ActionListener () { 
			public void actionPerformed (ActionEvent e) {
				action_source  =((JButton)e.getSource()).getText();
				if (mNameT.getText().length()!=0 && mIdT.getText().length()!=0){
					if (!(manager.addMember(mNameT.getText(), mIdT.getText()))){
						JOptionPane.showMessageDialog(manager.getMainWindow(),
								"Member Already Exists !",
								"Duplicate Member",
								JOptionPane.INFORMATION_MESSAGE); 
					} else {
						refresh();
					}
				}else {
					
					JOptionPane.showMessageDialog(manager.getMainWindow(),
							"Fields cannot be empty !",
							"Empty Fields",
							JOptionPane.INFORMATION_MESSAGE); 
				}
			}
		});

		JButton resetBtn = new JButton("Reset");

		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				action_source  =((JButton)e.getSource()).getText();
				mIdT.setText(null);
				mNameT.setText(null);
			}
		});

		panel.add(addBtn);
		panel.add(resetBtn);

		panel.setBorder(BorderFactory.createTitledBorder(
				loweredetched, "New Member Registration Area")); 
		return panel;
	}

//*****************To Show all Buttons **********************	
	
	private JPanel createButtonPanel () {

		JPanel p = new JPanel(new GridLayout(0,1,0,10));
		JPanel panel = new JPanel(new BorderLayout());
		
		JButton backBtn = new JButton("Back");

		backBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action_source = (((JButton)e.getSource()).getText());
				refresh();
			}
		});

		JButton removeBtn = new JButton ("Remove");
		removeBtn.addActionListener (new ActionListener () {
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
					JOptionPane.showMessageDialog(manager.getMainWindow(),
		                    "Please select a row to remove!",
		                    "Select a Row",
		                    JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		p.add(backBtn);
		p.add(removeBtn);

		panel.add(p, "North");
		panel.setBorder(BorderFactory.createCompoundBorder(
				raisedetched, loweredetched)); 

		return panel;
	}
	
//*****************To Show Member List Panel**********************	
	
		private JPanel showMemberListPanel() {  
			JPanel panel = new JPanel();
			panel.setLayout(new BorderLayout());

			JLabel label = new JLabel("Members : ");
			label.setFont(new Font("Tahoma", Font.BOLD, 12));
			table = new JTable();	
			table.setModel(getTableModel());
			memberTableModel.fireTableDataChanged();
			scroller = new JScrollPane(table); //scroller automatically puts the table header at the top
			table.setFillsViewportHeight(true); // true : table uses the entire height of the container, even if the table doesn't have enough rows to use the whole vertical space. 

			panel.add(label, "North");
			panel.add(scroller, "Center");

			panel.setBorder(BorderFactory.createCompoundBorder(
					raisedetched, loweredetched)); 

			return panel;

		}

//**********************Set Table Mode******************************	

	public TableModel getTableModel() {
		if (memberTableModel != null) 
			return memberTableModel;
		else {
			memberTableModel = new AbstractTableModel() {

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

			return memberTableModel;
		}
	}

//******************Reflect the changes done on the screen by the buttons******************

	public void refresh(){   //Reflect the changes done on the screen using buttons
		if(action_source.equalsIgnoreCase("Add")){
			int rowIndex = members.size()-1;
			memberTableModel.fireTableRowsInserted(rowIndex, rowIndex);
		}
		else if(action_source.equalsIgnoreCase("Remove")){
			memberTableModel.fireTableRowsDeleted(table.getSelectedRow(), table.getSelectedRow());
		}

		else if(action_source.equalsIgnoreCase("Back")){
			removeAll();
			add("Center",manager.createMainPanel());
			revalidate();
			repaint();
		}

	}
	
// ************Show the confirm dialog on removing and performs the remove functionality*********
	
	public void showConfirmDialog(String s) { 
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
