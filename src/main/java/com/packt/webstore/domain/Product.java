package com.packt.webstore.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;

import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.codehaus.jackson.annotate.JsonIgnore;
/**
 * The class Product represents basic information about product, like code, name, price
 * It is an Entity class. It means that each reference is connected with the column in the table PRODUCTS in the database
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
@XmlRootElement //wskazuje obiektowi MarshallingView obiekt domenowy Product jako główny wezeł dokumentu XML
@Entity
@Table(name = "PRODUCTS")
public class Product {

	@Id
	@Column(name = "CODE")
	private String code;

	@JsonIgnore //by nie dołączac obrazka w widoku JSON, bo jest to format tekstowy
	@Column(name = "IMAGE")
	private byte[] image; 
		
	@Column(name = "NAME")
	private String name;   
		
	@Column(name = "PRICE")
	private float price;  

	@Column(name = "CREATE_DATE")
	private Date createDate;

	private String fileName;   

	
	public Product() {}
			   
	public Product(String prodCode, byte[] prodImage, String prodName, float prodPrice, Date prodCreate) {
		   this.code = prodCode;
		   this.image = prodImage;
		   this.name = prodName;
		   this.price = prodPrice;
		   this.createDate = prodCreate;	  
	}
	public Product(String prodCode,  String prodName, float prodPrice) {
		   this.code = prodCode;
		   this.name = prodName;
		   this.price = prodPrice;
		  	  
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	@XmlTransient //by nie dołączą obrazka w idoku XML, bo jest to format tekstowy
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
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

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	
//	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "categories")
//	public Set<Orders> getOrders() {
//		return this.orders;
//	}
//
//	public void setStocks(Set<Orders> orders) {
//		this.orders = orders;
//	}
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Product: " + this.getCode() + " " + this.getName(); 
	}
	
}