package sg.edu.nus.iss.store;

import java.util.ArrayList;

/**
 * @author Koushik Radhakrishnan - Cart - Holds Cart item details
 *
 */
public class Cart {
	private Product product;
	private Member member;
	private int quantity;
	private ArrayList<Cart> cart;

	public Member getMember() {
		return member;
	}

//	public void setMember(String memberId) {
//		this.member = member;
//	}

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
		cart = new ArrayList<Cart>();
	}

	public Cart(Product product, Member member, int quantity) {

		this.product = product;
		this.member = member;
		this.quantity = quantity;
	}

	public Product getProduct() {
		return product;
	}

	public ArrayList<Cart> getCart() {
		return cart;
	}

	public void setCart(ArrayList<Cart> cart) {
		this.cart = cart;
	}

	public Cart addCart(Product product, int quantity, Member member) {

		Cart c = new Cart(product, member, quantity);
		cart.add(c);
		return c;
	}

	@Override
	public String toString() {
		return " product=" + product.getName() + ", quantity=" + quantity;
	}
}
