package com.pact.webstore.domain;


import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;


import com.packt.webstore.domain.CartItem;
import com.packt.webstore.domain.Product;

public class CartItemTest {
	private CartItem cartItem;

	@Before
	public void setup() {
		cartItem = new CartItem();
	}
	
	@Test
	public void cartItem_total_price_should_be_eaual_to_product_unit_price_in_case_of_single_quantity() {
		//Ustaw
		Product misio = new Product("11","Misiaczek", 45);
		cartItem.setProduct(misio);
		
		//Wykonaj
		float totalPrice = cartItem.getTotalPrice();
		
		//Porównaj
	assertEquals(misio.getPrice(),totalPrice, 0);

	}
}

