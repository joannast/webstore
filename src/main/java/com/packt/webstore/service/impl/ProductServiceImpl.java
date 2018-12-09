package com.packt.webstore.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.ProductData;
import com.packt.webstore.domain.repository.ProductRepository;
import com.packt.webstore.service.ProductService;
/** The Class implementing Interface ProductService and methods for handling requests for product
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.getAllProducts();
	}

	public Product getProductById(String productID) {
		return productRepository.getProductById(productID);
	}

	public ProductData getProductByIdAll(String productID) {
		return productRepository.getProductByIdAll(productID);
	}

	@Override
	public void addProduct(Product product) {
		productRepository.addProduct(product);
		
	}

}
