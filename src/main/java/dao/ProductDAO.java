package dao;

import java.util.List;
import java.util.Map;

import models.Product;

public interface ProductDAO {
	
	public List<Product> getProductList();

	public Product getProductById(long id);

	public List<Product> getProductByCategory(int category);
}
