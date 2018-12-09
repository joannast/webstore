package com.packt.webstore.service.impl;


import java.util.Map;
import java.util.Random;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.domain.CartItem;
import com.packt.webstore.domain.Order;
import com.packt.webstore.domain.repository.impl.InMemoryCartRepository;
import com.packt.webstore.service.OrderService;
import com.packt.webstore.service.CartService;

/** The class implementing Interface OrderService and method for processing customer order
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
	@Service
	public class OrderServiceImpl implements OrderService{

	@Autowired
	private CartService cartService;
	
	@Autowired
	private InMemoryCartRepository cartRepository;
	
	// uruchomienie obsługi sesji w postaci "fabryki obsługi sesji"
	public static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				   .applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration
					.buildSessionFactory(builder.build());
		return sessionFactory;
	}
	
	public int processOrder(Order newOrder) {
		int state = 1;  // 1 - oznacza poprawnie zakonczona transakcje
		Transaction tx = null;
		Session session = null;
		//String UpdateProductStock = "UPDATE ProductStock SET prodcount = prodcount - :count WHERE prodId = :pid";
		String UpdateProductStock = "UPDATE PRODUCT_STOCK SET PRODCOUNT = PRODCOUNT - :count WHERE PROD_ID = :pid";
		
		newOrder.setCart(cartService.read(newOrder.getCart().getCartId()));
		System.out.println("tst: processOrder ---> OrderId           = " + newOrder.getOrderId());
		System.out.println("tst: processOrder ---> cartId            = " + newOrder.getCart().getCartId());
		System.out.println("tst: processOrder ---> kwota zamówienia  = " + newOrder.getCart().getGrandTotal());
		System.out.println("tst: processOrder ---> nazwisko   = " + newOrder.getCustomer().getName());
		System.out.println("tst: processOrder ---> email = " + newOrder.getCustomer().getEmail());
		System.out.println("tst: processOrder ---> telefon = " + newOrder.getCustomer().getPhoneNumber());
		System.out.println("tst: processOrder ---> ulica   = " + newOrder.getCustomer().getBillingAddress().getStreetName());
		System.out.println("tst: processOrder ---> miasto  = " + newOrder.getCustomer().getBillingAddress().getAreaName());
		System.out.println("tst: processOrder ---> " + cartService.read(newOrder.getCart().getCartId()).getCartItems().toString());
		
		try {
			session = getSessionFactory().openSession();
			tx = session.beginTransaction();

			// iterujemy po produktach zamawianych i zapisujemy do bazy w dwoch krokach
			Map<String,CartItem> cartItems = cartService.read(newOrder.getCart().getCartId()).getCartItems();
			
			System.out.println("tst: processOrder: odczyt cartItems");
			
			for (Map.Entry<String, CartItem> entry : cartItems.entrySet())
			{
				System.out.println("tst: processOrder: klucz z mapy ---> " + entry.getKey());
			    CartItem cartItem = entry.getValue();
			    
			    // krok 1: zmniejszamy stany magazynowe o ilosci zamawiane
			    SQLQuery sqlQuery = session.createSQLQuery(UpdateProductStock);
			    sqlQuery.setParameter("count", cartItem.getQuantity());
			    sqlQuery.setParameter("pid", cartItem.getProduct().getCode());
				System.out.println("tst: produkt zakupiony ---> " + cartItem.getProduct().getCode());
			    int result = sqlQuery.executeUpdate();
				System.out.println("tst: update wykonany");
			    							
			}

		    // krok 2: zapisujemy do bazy informacje o zamowieniu i odbiorcy towaru 
			//za pomocą dynamicznego sql do odpowiednich kolumn są pzryporzakowane odpowiednie wartości
			String InsertOrder = "insert into ORDERS (ORDER_NUM, AMOUNT, CUSTOMER_NAME, CUSTOMER_STREET, CUSTOMER_NO, CUSTOMER_CITY,"
					+ "CUSTOMER_ZIP_CODE, CUSTOMER_COUNTRY, CUSTOMER_STATE, CUSTOMER_EMAIL,CUSTOMER_PHONE, ORDER_DATE) "
					+ "values (:ord, :amount, :name, :street, :no, :city, :zip, :country, :state, :email,:phone, sysdate())";
		    SQLQuery insertQuery = session.createSQLQuery(InsertOrder);
			//newOrder.setOrderId("ORD/"+newOrder.getCustomer().getName().substring(2)+"/"+newOrder.getCart().getCartId().substring(5));
		    Random generator = new Random();
		    newOrder.setOrderId("ORD/"+generator.nextInt(1000)+"/"+generator.nextInt(100));
		    insertQuery.setParameter("ord", newOrder.getOrderId());
		    
		    insertQuery.setParameter("amount", newOrder.getCart().getGrandTotal());
		    insertQuery.setParameter("name", newOrder.getCustomer().getName());
		    insertQuery.setParameter("street", newOrder.getCustomer().getBillingAddress().getStreetName());
		    insertQuery.setParameter("no", newOrder.getCustomer().getBillingAddress().getDoorNo());
		    insertQuery.setParameter("city", newOrder.getCustomer().getBillingAddress().getAreaName());
		    insertQuery.setParameter("zip", newOrder.getCustomer().getBillingAddress().getZipCode());
		    insertQuery.setParameter("country", newOrder.getCustomer().getBillingAddress().getCountry());
		    insertQuery.setParameter("state", newOrder.getCustomer().getBillingAddress().getState());
		    insertQuery.setParameter("email", newOrder.getCustomer().getEmail());	
		    insertQuery.setParameter("phone", newOrder.getCustomer().getPhoneNumber());	    
		    int result = insertQuery.executeUpdate();
			
		    // krok 3: zapisujemy do bazy informacje o poszczegolnych pozycjach zamówienia
			String InsertDetail = "insert into ORDER_DETAILS (AMOUNT, PRICE, ORDER_ID, PRODUCT_ID) "
					+ "values (:amount, :price, :ord, :prod)";
		    SQLQuery sqlQuery = session.createSQLQuery(InsertDetail);
			for (Map.Entry<String, CartItem> entry : cartItems.entrySet())
			{
				System.out.println("tst: processOrder: wstawianie pozycji zamówienia ---> " + entry.getKey());
			    CartItem cartItem = entry.getValue();
			    
			    sqlQuery.setParameter("amount", cartItem.getQuantity());
			    sqlQuery.setParameter("price", cartItem.getProduct().getPrice());
			    sqlQuery.setParameter("ord", newOrder.getOrderId());
			    sqlQuery.setParameter("prod", cartItem.getProduct().getCode());
			    System.out.println("tst: produkt zakupiony ---> " + cartItem.getProduct().getCode());
			    result = sqlQuery.executeUpdate();
				System.out.println("tst: pozycja dodana");
			    							
			}
			
			tx.commit();
			cartRepository.delete(newOrder.getCart().getCartId());

			System.out.println("tst: processOrder: dodanie zamówienia wykonane");
			System.out.println(state);

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			state=0;
			e.printStackTrace();
		} finally {
			session.close();
			
		}
//		if(newOrder.getOrderId() == null){
//			throw new IllegalArgumentException("Brak wybranego produktu na stanie");
//		}
		
		return state;
	
	
	}

}
