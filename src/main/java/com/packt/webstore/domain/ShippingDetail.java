package com.packt.webstore.domain;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;
/**
 * The class  ShippingDetail represents basic customer information which is necessary for shipping the ordered products
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
public class ShippingDetail implements Serializable {

private static final long serialVersionUID = 6350930334140807514L;
	
	private String name;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date shippingDate;
	private Address shippingAddress;
	
	
		public ShippingDetail() {
		this.shippingAddress = new Address();
	}


		public String getName() {
			return name;
		}


		public void setName(String name) {
			this.name = name;
		}


		public Date getShippingDate() {
			return shippingDate;
		}


		public void setShippingDate(Date shippingDate) {
			this.shippingDate = shippingDate;
		}


		public Address getShippingAddress() {
			return shippingAddress;
		}


		public void setShippingAddress(Address shippingAddress) {
			this.shippingAddress = shippingAddress;
		}


		public static long getSerialversionuid() {
			return serialVersionUID;
		}
}

