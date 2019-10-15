package servlets;

import java.util.List;

import javax.servlet.RequestDispatcher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import models.Product;
import mySQL.MySQLProductDAO;

@Controller
@RequestMapping ("/products")
public class ProductController {
	@RequestMapping (method = RequestMethod.GET)
	public String productGet(@RequestParam (required = false , name = "category") String categoryProduct, ModelMap model) {
		
		int category;
		List<Product> products;
		
		if (categoryProduct != null) {
			category = Integer.parseInt(categoryProduct);
			products = new MySQLProductDAO().getProductByCategory(category);
		} else {
			products = new MySQLProductDAO().getProductList();
		}
		model.addAttribute("productList", products);
			
		return "ProductsView";
	}
}
