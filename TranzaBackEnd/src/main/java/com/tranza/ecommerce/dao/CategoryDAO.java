package com.tranza.ecommerce.dao;

import java.util.List;

import com.tranza.ecommerce.model.Category;
import com.tranza.ecommerce.model.Product;

public interface CategoryDAO {
	
	//Method to save new category
	public boolean addCategory(Category category);
	
	public Boolean  updateCategory(Category category);
	
	//Method to fetch list of category
	List<Category> getAllCategory();
	
	//GFet active category
	public List<Category> getActiveCategory();
	
	
	//Method to fetch category based on category id
	public Category getCategoryById(int categoryId);

}
