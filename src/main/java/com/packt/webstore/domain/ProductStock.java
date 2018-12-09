package com.packt.webstore.domain;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
//import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;
/**
 * The class ProductStock is an Entity class connected with the table PRODUCT_STOCK in the database  
 * the ProductStock reference is used in the repository layer for retrieving information from the database about the product stock
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
@XmlTransient
@Entity
@Table(name = "PRODUCT_STOCK")
public class ProductStock {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "ID")
		private int id;
	
		@Column(name = "PRODCOUNT")
		private int prodcount;   
			
		@Column(name = "VALID_FROM")
		private Date validFrom;

		@Column(name = "VALID_TO")
		private Date validTo;

		@Column(name = "PROD_ID")
		private String prodId;  
		
		public ProductStock() {}

		public ProductStock(int idS, int pCount, Date vFrom, Date vTo, String pId) {
			this.id = idS;
			this.prodcount = pCount;
			this.validFrom = vFrom;
			this.validTo = vTo;
			this.prodId = pId;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getProdcount() {
			return prodcount;
		}

		public void setProdcount(int prodcount) {
			this.prodcount = prodcount;
		}

		public Date getValidFrom() {
			return validFrom;
		}

		public void setValidFrom(Date validFrom) {
			this.validFrom = validFrom;
		}

		public Date getValidTo() {
			return validTo;
		}

		public void setValidTo(Date validTo) {
			this.validTo = validTo;
		}

		public String getProdId() {
			return prodId;
		}

		public void setProdId(String prodId) {
			this.prodId = prodId;
		}


}