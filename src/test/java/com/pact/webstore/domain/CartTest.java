package com.pact.webstore.domain;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.CartItem;
import com.packt.webstore.domain.Product;

public class CartTest {
	private Cart cart;
	private CartItem item;
	
	@Before
	public void setup() {
		cart = new Cart();
	}
	
	@Test
	public void cartGranTotal__should_be_eaqual_to_product_unit_price_in_case_of_single_quantity() {
		//Ustaw
	
		
		Product misio = new Product("11","Misiaczek", 45);
		CartItem item = new CartItem(misio);
		
		//Wykonaj
		float totalPrice=item.getTotalPrice();
		cart.addCartItem(item);
		System.out.println(totalPrice);
		float grandTotal = cart.updateGrandTotal();
			System.out.println(grandTotal);
		//Porównaj
	assertEquals(totalPrice,grandTotal, 0);

	}

}
