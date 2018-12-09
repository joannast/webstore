package com.packt.webstore.service;

import com.packt.webstore.domain.Cart;
/** The interface that declares methods for handling requests from cart.jsp.
* @author Joanna Stecz / j.stecz@wp.pl
* @author Dorota Jankowiec / dorota.jankowiec@gmail.com
* @version 1.0
*/
public interface CartService {
	
	Cart create(Cart cart);
	
	Cart read(String cartId);
	
	void update(String cartId, Cart cart);
	
	void delete(String cartId);

}