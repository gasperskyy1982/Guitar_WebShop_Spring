package servlets;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import models.Product;
import mySQL.MySQLProductDAO;

@Controller
@RequestMapping("/cart")
public class CartController {
	@RequestMapping(method = RequestMethod.GET)
	public String cartGet() {
		return "CartView";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String cartPost(@RequestParam(required = false, name = "productToBuy") String productToBuy,
			@RequestParam(required = false, name = "productToRemove") String productToRemove,
			@RequestParam(required = false, name = "recountProduct") String recountProduct,
			@RequestParam(required = false, name = "qnt") String qnt,
			@RequestParam(required = false, name = "ClearCart") String clearCart, HttpSession session, ModelMap model) {

		System.out.println("Clear Cart: " + clearCart);
		System.out.println("Product to Remove:  " + productToRemove);
		System.out.println("Product to Recount: " + recountProduct);
		System.out.println("Product to Buy: " + productToBuy);
		System.out.println("Quantity: " + qnt);

		session = mySession();

		long productId;
		int numberGeneralProducts;
		Product product;

		if (productToRemove != null) {

			productId = Integer.parseInt(productToRemove);
			product = new MySQLProductDAO().getProductById(productId);
			Map<Product, Integer> cartMap;
			cartMap = (Map<Product, Integer>) session.getAttribute("cart");
			long numberCurrentProduct = 0;
			numberGeneralProducts = (int) (session.getAttribute("numberGeneralProducts"));

			if (cartMap.keySet().contains(product)) {
				numberCurrentProduct = cartMap.get(product);
				numberGeneralProducts -= numberCurrentProduct;
				session.setAttribute("numberGeneralProducts", numberGeneralProducts);
				cartMap.remove(product);
				System.out.println("Removing product: " + productId);
				session.setAttribute("cart", cartMap);
				}
		}

		if (recountProduct != null) {

			productId = Integer.parseInt(recountProduct);
			product = new MySQLProductDAO().getProductById(productId);
			numberGeneralProducts = (int) (session.getAttribute("numberGeneralProducts"));
			System.out.println("Number General Before recount:" + numberGeneralProducts);
			Map<Product, Integer> cartMap;
			cartMap = (Map<Product, Integer>) session.getAttribute("cart");

			if (cartMap.keySet().contains(product)) {
			   long numberCurrentProduct = Integer.parseInt(qnt);
				if (numberCurrentProduct > 0) {
					int numberCurrentProductOld = cartMap.get(product);
					int difference = (int) numberCurrentProduct - numberCurrentProductOld;
					cartMap.put(product, (int) numberCurrentProduct);
					System.out.println("Number of Current Product after recount: " + numberCurrentProduct);
					numberGeneralProducts += difference;
				} else {
					numberCurrentProduct = cartMap.get(product);
					numberGeneralProducts -= numberCurrentProduct;
					cartMap.remove(product);
					System.out.println("Removing product: " + productId);
					
				}
				session.setAttribute("numberGeneralProducts", numberGeneralProducts);
				session.setAttribute("cart", cartMap);
			}
		}
		
		if (clearCart != null) {
			System.out.println(clearCart);
			numberGeneralProducts = 0;
			session.setAttribute("cart", null);
			session.setAttribute("numberGeneralProducts", numberGeneralProducts);
		}
		
		if (productToBuy != null && qnt != null) {

			Integer qnty = Integer.parseInt(qnt);
			productId = Integer.parseInt(productToBuy);
			product = new MySQLProductDAO().getProductById(productId);

			Map<Product, Integer> cartMap;
			long numberCurrentProduct = 0;

			if (session.getAttribute("cart") != null) {

				System.out.println("Old cart");
				cartMap = (Map<Product, Integer>) session.getAttribute("cart");
				numberGeneralProducts = (int) (session.getAttribute("numberGeneralProducts"));
				System.out.println("Number General Before adding:" + numberGeneralProducts);
				numberGeneralProducts += qnty;
				System.out.println("Number General After adding:" + numberGeneralProducts);

				System.out.println(cartMap.keySet().contains(product));

				if (cartMap.keySet().contains(product)) {
					System.out.println("This product has already is");
					System.out.println("It`s ProductId " + productId);
					numberCurrentProduct = cartMap.get(product);
					System.out.println("Number of Current Product is: " + numberCurrentProduct);
					cartMap.put(product, (int) numberCurrentProduct + qnty);
					System.out.println("Adding Product");
					System.out.println("Number of Current Product after adding is: " + numberCurrentProduct + qnt);
				} else {
					System.out.println("This is new product");
					System.out.println("Number of Current Product is: " + numberCurrentProduct);
					cartMap.put(product, (int) numberCurrentProduct + qnty);
					System.out.println("Number of Current Product after adding is: " + (numberCurrentProduct + qnt));
				}

			} else {
				System.out.println("New cart");
				cartMap = new HashMap<Product, Integer>();
				System.out.println("Number of Current Product is: " + numberCurrentProduct);
				cartMap.put(product, (int) qnty);
				System.out.println("Number of Current Product after adding is: " + (numberCurrentProduct + qnt));
				numberGeneralProducts = qnty;
				System.out.println("Number of General Products:" + numberGeneralProducts);
			}

			System.out.println("In the cart is: " + cartMap);
			session.setAttribute("numberCurrentProduct", numberCurrentProduct);
			session.setAttribute("cart", cartMap);
			session.setAttribute("numberGeneralProducts", numberGeneralProducts);
			// model.addAttribute("cart", cartMap);

		}

		return "CartView";
	}

	public static HttpSession mySession() {
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		return attr.getRequest().getSession(true);
	}

}
