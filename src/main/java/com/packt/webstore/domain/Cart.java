package com.packt.webstore.domain;

import java.util.HashMap;
import java.util.Map;
/**
 * The class Cart represents shopping Cart
 * the class Cart provides methods for adding and removing items from shopping Cart and updating its total value 
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
public class Cart {

	private String cartId;
	private Map<String,CartItem> cartItems;
	private float grandTotal;
	
	public Cart() {
		cartItems = new HashMap<String, CartItem>();
		grandTotal = 0;
	}
	
	public Cart(String cartId) {
		this();
		this.cartId = cartId;
	}
	
	public String getCartId() {
		return cartId;
	}

	public void setCartId(String cartId) {
		this.cartId = cartId;
	}

	public Map<String, CartItem> getCartItems() {
		return cartItems;
	}

	public void setCartItems(Map<String, CartItem> cartItems) {
		this.cartItems = cartItems;
	}

	public float getGrandTotal() {
		return grandTotal;
	}
	public void removeCartItems() {
		cartItems = new HashMap<String, CartItem>();
		grandTotal = 0;
	}
	public void addCartItem(CartItem item) {
		String productId = item.getProduct().getCode();
		
		if(cartItems.containsKey(productId)) {
			CartItem existingCartItem = cartItems.get(productId);
			existingCartItem.setQuantity(existingCartItem.getQuantity()+ item.getQuantity());
			cartItems.put(productId, existingCartItem);
		} else {
			cartItems.put(productId, item);
		}
		updateGrandTotal();
	}
	
	public void removeCartItem(CartItem item) {
		String productId = item.getProduct().getCode();
		cartItems.remove(productId);
		updateGrandTotal();
	}
	
	public float updateGrandTotal() {
		grandTotal = 0;
		for(CartItem item : cartItems.values()){
			grandTotal = grandTotal + item.getTotalPrice();
		}
		return grandTotal;
	}
	
	
}