package com.packt.webstore.domain;

/**
 * The class CartItem represents separate element of Cart and includes the information about the product, the price and quantity
 * the class CartItem provides method for updating total price for the cart item  
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
public class CartItem {

	private Product product;
	private int quantity;
	private float totalPrice;
	
	public CartItem() {
		this.quantity = 1;
	}
	
	public CartItem(Product product) {
		super();
		this.product = product;
		this.quantity = 1;
		this.totalPrice = product.getPrice();
	}
	
	public Product getProduct() {
		return product;
	}
	
	public void setProduct(Product product) {
		this.product = product;
		this.updateTotalPrice();
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
		this.updateTotalPrice();
	}
	
	public float getTotalPrice() {
		return totalPrice;
	}

	public void updateTotalPrice() {
		totalPrice = this.product.getPrice() * (float)this.quantity;
	}
	

}