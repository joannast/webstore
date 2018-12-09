package com.packt.webstore.service;

import com.packt.webstore.domain.Order;


/** The interface that declares method for processing customer order
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
public interface OrderService {
	
	int processOrder(Order newOrder);
	
}
