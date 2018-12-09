package com.packt.webstore.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.packt.webstore.domain.CartItem;
import com.packt.webstore.service.OrderService;
import com.packt.webstore.service.CartService;
import com.packt.webstore.domain.Order;

/**
 * The controller class for the addOrder.jsp and thanksCustomer.jsp file.
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
@Controller
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private CartService cartService;

	

	@RequestMapping(value = "/checkout", method = RequestMethod.GET)
	public String setCheckout(@RequestParam("cartId") String cartId, Model model) {
		Order newOrder = new Order();
		newOrder.setCart(cartService.read(cartId));
		System.out.println("tst: setCheckout ---> cartId = " + newOrder.getCart().getCartId());
		System.out.println("tst: setCheckout ---> kwota  = " + newOrder.getCart().getGrandTotal());
		System.out.println("tst: setCheckout ---> " + cartService.read(cartId).getCartItems().toString());

		for (Map.Entry<String, CartItem> entry : newOrder.getCart().getCartItems().entrySet())
		{
			System.out.println("tst: setCheckout ---> " + entry.getKey());
		}
		
		model.addAttribute("newOrder", newOrder);

		return "addOrder";		
	}

	@RequestMapping(value = "/checkout", method = RequestMethod.POST)
	public String processAddNewOrderForm(@ModelAttribute("newOrder")Order newOrder, Model model) {
		System.out.println("tst: processAddNewOrderForm ---> cartId = cartId  = " + newOrder.getCart().getCartId());
		System.out.println("tst: processAddNewOrderForm ---> " + cartService.read(newOrder.getCart().getCartId()).getCartItems().toString());

		for (Map.Entry<String, CartItem> entry : newOrder.getCart().getCartItems().entrySet())
		{
			System.out.println("tst: processAddNewOrderForm (products codes) ---> " + entry.getKey());
		}
		
		// zapisy do bazy
		orderService.processOrder(newOrder);
		System.out.println("tst: processAddNewOrderForm ---> nazwa   = " + newOrder.getCustomer().getName());
		System.out.println("tst: processAddNewOrderForm ---> telefon = " + newOrder.getCustomer().getPhoneNumber());
		System.out.println("tst: processAddNewOrderForm ---> ulica = " + newOrder.getCustomer().getBillingAddress().getStreetName());
		
		model.addAttribute("order", newOrder);
		if(newOrder.getOrderId()==null) {
			return "sorry";
		}
		else
		return "thanksCustomer";
		
	}
	
}
