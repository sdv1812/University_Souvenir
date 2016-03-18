package iss.tranasction.pos;

import java.awt.BorderLayout;
import java.awt.Panel;

import javax.swing.JTextField;

public class TransactionMemberPanel extends Panel {
	private JTextField memberId;
	private JTextField quantity;
	
	public TransactionMemberPanel(){
        setLayout (new BorderLayout());
        memberId = new JTextField (12); 
        quantity = new JTextField (12);
	}

}
