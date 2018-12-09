package com.packt.webstore.exception;
/**
 * The class responsible for handling exceptions related to referring to non-existent products
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
public class ProductNotFoundException extends RuntimeException{

	private static final long serialVersionUID = -694354952032299587L;
	
	private String productId;

	public ProductNotFoundException(String productId) {
		this.productId = productId;
	}
	
	public String getProductId() {
		return productId;
	}

}