package models;

import java.util.List;
import java.util.Map;

public class Cart {
	
	Map <Product, Integer> products;
	
	public Map<Product,Integer> getProducts() {
		return products;
	}

	public void setProduts(Map<Product,Integer> products) {
		this.products = products;
	}

	
}
