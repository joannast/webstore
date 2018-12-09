
package com.packt.webstore.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.packt.webstore.domain.Product;
import com.packt.webstore.service.ProductService;


/**
 * The product controller class for the products.jsp and product.jsp files 
 * The class supports website with available products and the details of the chosen product
 * @author Joanna Stecz / j.stecz@wp.pl
 * @author Dorota Jankowiec / dorota.jankowiec@gmail.com
 * @version 1.0
 */
@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	/**
	 * The implementation of the method for listing all products
	 * The method uses the reference to the product service in order to obtain the list of all products
	 **/
	@RequestMapping
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	/**
	 * The controller method for displaying one product details
	 * @param productId
	 **/
	@RequestMapping("/product")
	public String getProductById(@RequestParam("id") String productId, Model model) {
		//model.addAttribute("product", productService.getProductById(productId));
		model.addAttribute("product", productService.getProductByIdAll(productId));
		return "product";
	}

	/**
	 * The controller method for adding a new product using Product Form
	 **/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model) {
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";		
	}
	/**
	 * The controller method for processing the product form "addProduct" and returning view with all products
	 **/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct")Product newProduct) {
		productService.addProduct(newProduct);

		return "redirect:/products";
		
	}
	
	/**
	 * The controller method for loading the image of the product 
	 **/
	@RequestMapping(value = "/productImage", method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
            @RequestParam("code") String code) throws IOException {
        Product product = null;
        System.out.println("-------------------------------------------------");
        System.out.println(code);
        if (code != null) {
            //product = this.productDAO.findProduct(code);
            product = this.productService.getProductById(code);
            System.out.println(product.getName());
            System.out.println("-------------------------------------------------");
        }
        if (product != null && product.getImage() != null) {
            response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
            response.getOutputStream().write(product.getImage());
        }
        response.getOutputStream().close();
    }
	

}
