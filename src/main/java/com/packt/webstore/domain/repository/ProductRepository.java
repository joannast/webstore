package com.packt.webstore.domain.repository;

import java.util.List;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.ProductData;

/** The interface that declares methods of @Repository layer for serving product
* @author Joanna Stecz / j.stecz@wp.pl
* @author Dorota Jankowiec / dorota.jankowiec@gmail.com
* @version 1.0
*/
public interface ProductRepository {

	List <Product> getAllProducts();
	
	Product getProductById(String productID);
	
	ProductData getProductByIdAll(String productID);// pobranie do klasy ProductData z widoku PROUCT_DATA z bazy danych
	
	void addProduct(Product product);
}
