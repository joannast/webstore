package com.packt.webstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.packt.webstore.domain.Cart;
import com.packt.webstore.domain.CartItem;
import com.packt.webstore.domain.Product;
import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.service.CartService;
import com.packt.webstore.service.ProductService;
/**
 * The REST controller class is using rest services
 * The class provides methods for creating, reading and  updating the Cart object
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
@Controller
@RequestMapping(value = "rest/cart")
public class CartRestController {
	/**
	 * Return cartService object from CartService class
	 * @param cartService the object of CartService class
	 */
	@Autowired
	private CartService cartService;
	/**
	 * Return productService object from ProductService class
	 * @param productService the object of ProductService class
	 */
	@Autowired
	private ProductService productService;
	//adnotacje @ResponseBody oraz @RequestBody sprawiają, że w tym kontrolerze zwracany jest obiekt klasy
	//zamiast umieszczać obiekt w modelu, jest on zwracany wprost ponieważ jego stan ma być przekazany w formacie JSON
	//restowe usługi powinny zwracać dane w formacie XML lub JSON
	//adnotacja @ResponseBody jest odpowiedzialna za transformację obiektu Javy do formatu XML lub JSON
	//i przesłanie go jako zawartość odpowiedzi HTTP.
	//przy przesłaniu żadania w formacie XML lub JSON adnotacja @RequestBody transformuje je 
	// do odpowiednich obiekt Javy
	/**
	 * The new cart is created
	 * @param cart The cart parameter
	 * @return new cart object
	 */
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Cart create(@RequestBody Cart cart) {
		return  cartService.create(cart);
	}
	/**
	 * The method for reading cart
	 * @param cartId The cart ID parameter
	 */
	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public @ResponseBody Cart read(@PathVariable(value = "cartId") String cartId) {
		return cartService.read(cartId);
	}
	/**
	 * The cart is updated
	 * @param cartId The cart ID parameter
	 * @param cart The cart object
	 */
	@RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void update(@PathVariable(value = "cartId") String cartId,	@RequestBody Cart cart) {
		cartService.update(cartId, cart);
	}
	/**
	 * The cart is deleted
	 * @param cartId The cart ID parameter
	 */
	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable(value = "cartId") String cartId) {
		cartService.delete(cartId);
	}
	/**
	 * The new product id added to Cart
	 * @exception IllegalArgumentException IllegalArgumentException
	 * @param code The code parameter
	 * @param request the object of HttpServletRequest Class
	 */
	@RequestMapping(value = "/add/{code}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void addItem(@PathVariable String code, HttpServletRequest request) {
		System.out.println("---------------> addItem(@PathVariable String code, HttpServletRequest request)");

		String sessionId = request.getSession(true).getId();
		Cart cart = cartService.read(sessionId);
		if(cart== null) {
			cart = cartService.create(new Cart(sessionId));
		}
		
		Product product = productService.getProductById(code);
		if(product == null) {
			throw new IllegalArgumentException(new ProductNotFoundException(code));
		}
		
		cart.addCartItem(new CartItem(product));
		
		cartService.update(sessionId, cart);
	}
	/**
	 * The product is removed from Cart
	 * @exception IllegalArgumentException IllegalArgumentException
	 * @param productId ID parameter of product
	 * @param request the object of HttpServletRequest Class
	 */
	@RequestMapping(value = "/remove/{productId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable String productId, HttpServletRequest request) {
		
		String sessionId = request.getSession(true).getId();
		Cart cart = cartService.read(sessionId);
		if(cart== null) {
			cart = cartService.create(new Cart(sessionId));
		}
		
		Product product = productService.getProductById(productId);
		if(product == null) {
			throw new IllegalArgumentException(new ProductNotFoundException(productId));
		}
		
		cart.removeCartItem(new CartItem(product));
		
		cartService.update(sessionId, cart);
	}
	/**
	 * Supports the exceptions generated by the @RequestMapping methods of request
	 * handling in the same controller caused by incorrect request
	 * @param ex Exception in case of incorrect request
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST,  reason="Niepoprawne żądanie, zweryfikuj przesyłane dane.")
	public void handleClientErrors(Exception ex) { }
	/**
	 * Supports the exceptions generated by the @RequestMapping methods of request
	 * handling in the same controller caused by an internal server error
	 * @param ex Exception in case of internal server error
	 */
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Wewnętrzny błąd serwera")
	public void handleServerErrors(Exception ex) {	}
}