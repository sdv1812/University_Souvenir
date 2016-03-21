package sg.edu.nus.iss.gui;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class Transaction {
	int tranasctionId;
	String productId;
	int memberId;
	int Qtypurchased;
	Date dateOfPurchase;
	public int getTranasctionId() {
		return tranasctionId;
	}
	public void setTranasctionId(int tranasctionId) {
		this.tranasctionId = tranasctionId;
	}
	public String getProductId() {
		return productId;
	}
	public void setProductId(String productId) {
		this.productId = productId;
	}
	public int getMemberId() {
		return memberId;
	}
	public void setMemberId(int memberId) {
		this.memberId = memberId;
	}
	public int getQtypurchased() {
		return Qtypurchased;
	}
	public void setQtypurchased(int qtypurchased) {
		Qtypurchased = qtypurchased;
	}
	public Date getDateOfPurchase() {
		return dateOfPurchase;
	}
	public void setDateOfPurchase(Date dateOfPurchase) {
		this.dateOfPurchase = dateOfPurchase;
	}
	public String  addProductsToCart(HashMap productQuantity){
					
		return "";//Setting Transaction hashmap object 
	}
	public double calculateTransactionTotal(HashMap productSubtotal){
				return 1.0;
	}
	public double getBalance(double totalAmount,double amountReceived){
				return 1.0;
	}
	public ArrayList<Transaction> getAllTransactions(){
		/* connect to text file */
				ArrayList<Transaction> transaction = new ArrayList<>();//convert each row to transaction and add it to list
				return  transaction;
	}
	public String makePayment(HashMap currentTransaction){
		return "";
	}
}
