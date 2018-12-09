package com.packt.webstore.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * The class ProductData is an Entity class connected with the view PRODUCT_DATA in the database  
 * the ProductData reference is used in the repository layer by the method serving for displaying the details of the product
 * with the information recorded in the database
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
@Entity
@Table(name = "PRODUCT_DATA")
public class ProductData {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CODE")
	private String code;

	@Column(name = "NAME")
	private String name;   
		
	@Column(name = "PRICE")
	private float price;  

	@Column(name = "PRODCOUNT")
	private int prodcount;
	
	//private Set<Orders> orders = new HashSet<Orders>();
	
	public ProductData() {}
			   
	public ProductData(String prodCode, String prodName, float prodPrice, int prodcount) {
		   this.code = prodCode;
		   this.name = prodName;
		   this.price = prodPrice;
		   this.prodcount = prodcount;	   
	}
	

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getprodcount() {
		return prodcount;
	}

	public void setprodcount(int prodcount) {
		this.prodcount = prodcount;
	}
	
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
//	public Set<Orders> getOrders() {
//		return this.orders;
//	}
//
//	public void setStocks(Set<Orders> orders) {
//		this.orders = orders;
//	}
	
}