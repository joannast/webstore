package com.packt.webstore.domain.repository.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;
import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.repository.CartRepository;

/** The @repository class implementing the interface CartRepository and the methods for serving customer cart
* @author Joanna Stecz / j.stecz@wp.pl
* @author Dorota Jankowiec / dorota.jankowiec@gmail.com
* @version 1.0
*/
@Repository
public class InMemoryCartRepository implements CartRepository{
	
	private Map<String, Cart> listOfCarts;
	

	public InMemoryCartRepository() {
		listOfCarts = new HashMap<String,Cart>();
	}
	
	public Cart create(Cart cart) {
		if(listOfCarts.keySet().contains(cart.getCartId())) {
			throw new IllegalArgumentException(String.format("Nie można utworzyć koszyka. Koszyk o wskazanym  id (%) już istnieje.",cart.getCartId()));
		}
		System.out.println("---------------> Cart create(Cart cart)");
		listOfCarts.put(cart.getCartId(), cart);
		return cart;
	}
	
	
	public Cart read(String cartId) {
		return listOfCarts.get(cartId);
	}

	public void update(String cartId, Cart cart) {
		if(!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("Nie można zaktualizować koszyka. Koszyk o wskazanym id (%) nie istnieje.",cartId));
		}

		listOfCarts.put(cartId, cart);
	}

	
	public void delete(String cartId) {
		if(!listOfCarts.keySet().contains(cartId)) {
			throw new IllegalArgumentException(String.format("Nie można usunąć koszyka. Koszyk o wskazanym id (%) nie istnieje.",cartId));
		}

		listOfCarts.remove(cartId);
	}

}