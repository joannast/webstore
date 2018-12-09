package com.packt.webstore.domain.repository.impl;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.packt.webstore.domain.Product;
import com.packt.webstore.domain.ProductData;
import com.packt.webstore.domain.repository.ProductRepository;
/** The @repository class implementing Interface ProductRepository and  methods for serving product information
* @author Joanna Stecz / j.stecz@wp.pl
* @author Dorota Jankowiec / dorota.jankowiec@gmail.com
* @version 1.0
*/
@Repository
public class InMemoryProductRepository implements ProductRepository{
	
	private List<Product> listOfProducts = new ArrayList<Product>();
	// uruchomienie obsługi sesji w postaci "fabryki obsługi sesji"
	   public static SessionFactory getSessionFactory() {
		   Configuration configuration = new Configuration().configure();
		   StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				   .applySettings(configuration.getProperties());
		   SessionFactory sessionFactory = configuration
					.buildSessionFactory(builder.build());
		   return sessionFactory;
	   }
	   /**method to read all the products from database*/
	public void listProducts() {
		Session session = getSessionFactory().openSession();
		Transaction tx = null;
		try {
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<Product> prodList = session.createQuery("FROM Product").list(); // po FROM nazwa klasy nie tabeli
			listOfProducts = prodList;

			tx.commit();
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
	}
	public InMemoryProductRepository() {
		listProducts();

		}

	public List<Product> getAllProducts() {
		return listOfProducts;
	}
	/**method for reading basic information about products from database*/
	public ProductData getProductByIdAll(String productID) {		
		Transaction tx = null;
		Session session = null;
		ProductData productById = null;
		
		try {
			session = getSessionFactory().openSession();
			tx = session.beginTransaction();
			@SuppressWarnings("unchecked")
			List<ProductData> prodList = session.createQuery("FROM ProductData").list(); // po FROM nazwa klasy nie tabeli
			tx.commit();

			for(ProductData product : prodList) {
				if(product!=null && product.getCode()!=null && product.getCode().equals(productID)){
					productById = product;
					break;
				}
			}
		
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		
		if(productById == null){
			throw new IllegalArgumentException("Brak produktu o wskazanym id: "+ productID);
		}
		
		return productById;
		
	}
	/**
	 * Method for reading product information for a product with given Id number
	 * @param productId
	 * @author Joanna Stecz / j.stecz@wp.pl
	 */
	public Product getProductById(String productId) {
		Product productById = null;
		
		for(Product product : listOfProducts) {
			if(product!=null && product.getCode()!=null && product.getCode().equals(productId)){
				productById = product;
				break;
			}
		}
		
		if(productById == null){
			throw new IllegalArgumentException("Brak produktu o wskazanym id: "+ productId);
		}
		
		return productById;
	}
	/**
	 * Method for adding the new product to database
	 * @param product
	 * @author Joanna Stecz / j.stecz@wp.pl
	 */
	@Override
	public void addProduct(Product product) {
		System.out.println("***********************");
		
		Transaction tx = null;
		Session session = null;
		
		//listOfProducts.add(product);
		
		String link = product.getFileName();
		System.out.println("obrazek   : " + link);
		File file = new File(link);
		byte[] bFile = new byte[(int) file.length()];
		try {
		    FileInputStream fileInputStream = new FileInputStream(file);
		    fileInputStream.read(bFile);
		    fileInputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		   
		try {
			session = getSessionFactory().openSession();
			tx = session.beginTransaction();

		    Calendar calendar = Calendar.getInstance();
		    Date prodCreateDate = new Date((calendar.getTime()).getTime());
		    product.setCreateDate(prodCreateDate);
		    product.setImage(bFile);

			System.out.println("kod   : " + product.getCode());
			System.out.println("nazwa : " + product.getName());
			System.out.println("cena  : " + product.getPrice());
			System.out.println("data  : " + product.getCreateDate());
			System.out.println("***********************");

			session.save(product);
			
			tx.commit();

		
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
				
	}
}
