package sg.edu.nus.iss.store;

import iss.tranasction.pos.Product;


import java.util.ArrayList;

public class Cart {
				private Product product;
				private String memberId;
				private int quantity;
				private ArrayList<Cart> cart;
	
				public String getMemberId() {
					return memberId;
				}
				public void setMemberId(String memberId) {
					this.memberId = memberId;
				}
				public int getQuantity() {
					return quantity;
				}
				public void setQuantity(int quantity) {
					this.quantity = quantity;
				}
				public void setProduct(Product product) {
					this.product = product;
				}
				public Cart() {
				}
				
				public Cart(Product product ,String memberID,int quantity){
							
						this.product=product;
						this.memberId=memberID;
						this.quantity=quantity;
				}	
				
				public Product getProduct(){
							return product;
				}
				public ArrayList<Cart> getCart() {
					return cart;
				}
				public void setCart(ArrayList<Cart> cart) {
					this.cart = cart;
				}
				public Cart addCart(Product product,int quantity,String memberId){
						Cart c = new Cart(product,memberId,quantity);
						cart.add(c);
						return c;
				}
}
