package com.packt.webstore.domain;

import java.io.Serializable;
/**
 * The class Order represents customer order
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
public class Order implements Serializable{

	private static final long serialVersionUID = -3560539622417210365L;
	
	private String orderId;
	private Cart cart;
	private Customer customer;
	private String shippingDetail;
	
	public Order() {
		this.customer = new Customer();
		this.shippingDetail = "";
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public String getShippingDetail() {
		return shippingDetail;
	}

	public void setShippingDetail(String shippingDetail) {
		this.shippingDetail = shippingDetail;
	}

//	public static long getSerialversionuid() {
//		return serialVersionUID;
//	}

	@Override
	public int hashCode() {
		final int prime = 829;
		int result = 1;
		result = prime * result + ((orderId == null) ? 0 : orderId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order other = (Order) obj;
		if (orderId == null) {
			if (other.orderId != null)
				return false;
		} else if (!orderId.equals(other.orderId))
			return false;
		return true;
	}


}