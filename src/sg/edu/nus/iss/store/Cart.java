package iss.tranasction.pos;

public class Cart {
				private Product product;
				private String memberId;
				private int quantity;
	
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
				public Cart(Product product ,String memberID,int quantity){
							
						this.product=product;
						this.memberId=memberID;
						this.quantity=quantity;
				}	
				public Product getProduct(){
							return product;
				}
}
