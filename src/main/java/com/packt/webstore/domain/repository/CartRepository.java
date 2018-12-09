package com.packt.webstore.domain.repository;

import com.packt.webstore.domain.Cart;

/** The interface that declares methods of @Repository layer for serving customer cart
* @author Joanna Stecz / j.stecz@wp.pl
* @author Dorota Jankowiec / dorota.jankowiec@gmail.com
* @version 1.0
*/
public interface CartRepository {

	Cart create(Cart cart);
	
	Cart read(String cartId);
	
	void update(String cartId, Cart cart);
	
	void delete(String cartId);

}