package com.tranza.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tranza.ecommerce.dao.CategoryDAO;
import com.tranza.ecommerce.dao.ProductDAO;
import com.tranza.ecommerce.model.Category;
import com.tranza.ecommerce.model.Product;
import com.tranza.util.FileUploadUtility;
import com.tranza.validator.ProductValidator;

@Controller
@RequestMapping("/manage")
public class AdminController {
	
	private final static Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@RequestMapping(value = "/products" , method = RequestMethod.GET)
	public ModelAndView shoWManageProducts(@RequestParam(name="operation" , required = false) String operation) {
		
		ModelAndView modelAndView = new ModelAndView("manageProduct");
		modelAndView.addObject("userClickManageProducts", true);
		modelAndView.addObject("title" , "Manage Products");
		
		Product nProduct = new Product();
		//set few of the fields
		nProduct.setSupplierId("S0005");
		nProduct.setiSactive(true);
		modelAndView.addObject("product" , nProduct);
		
		if(operation!=null){
			
			if(operation.equals("product")) {
				modelAndView.addObject("message" , "Product submitted successfully");
			}
			
			else if (operation.equals("category")) {
				modelAndView.addObject("message" , "Category submitted successfully");
			}
		}
		
		
		return modelAndView;
	}
	
	@RequestMapping(value="/{productId}/product" , method = RequestMethod.GET)
public ModelAndView shoWEditProducts(@PathVariable int productId) {
		
		ModelAndView modelAndView = new ModelAndView("manageProduct");
		modelAndView.addObject("userClickManageProducts", true);
		modelAndView.addObject("title" , "Manage Products");
		
		//Fetch product from database
		Product nProduct = productDAO.getProductById(productId);
		
		//set the product fetched from DB
		modelAndView.addObject("product" , nProduct);	
		return modelAndView;
	}
	
	
	
	//Handling submission
	@RequestMapping(value = "/products" , method = RequestMethod.POST)
	public  String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct , BindingResult results , 
			Model model  , HttpServletRequest request) {
		
		//Handling image validation for new product
		
		if(mProduct.getProductId() == 0) {
			new ProductValidator().validate(mProduct, results);
		}
		else
		{
			if(!mProduct.getFile().getOriginalFilename().equals("")) {
				new ProductValidator().validate(mProduct, results);
			}
		}	
		//Check if there are any errors
		if(results.hasErrors()) {
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title" , "Manage Products");
			model.addAttribute("message" , "Validation failed for product subimission");
			//return "home";
			return "manageProduct";
			 //Or manageProduct if message does not display
		}	
		logger.info(mProduct.toString());	
		if(mProduct.getProductId() == 0) {
			//Create a new product record if product is is = to zero
			productDAO.addProduct(mProduct);
		}
		else {
			//Updater the product if product id is not = to zero
			productDAO.updateProduct(mProduct);		
		}	
		if(!mProduct.getFile().getOriginalFilename().equals("")) {
			FileUploadUtility.uploadFile(request , mProduct.getFile() , mProduct.getProdCode());
		}
		
		return "redirect:/manage/products?operation=product";
	}
	
	@RequestMapping(value = "/product/{productId}/activation" , method = RequestMethod.POST)
	@ResponseBody
	public String handleProductActivation(@PathVariable int productId) {
		//Going to fetch the product from the database
		Product product = productDAO.getProductById(productId);
		boolean isActive = product.getiSactive();
		//Activating and deactivating based on based on the value of active field
		product.setiSactive(!product.getiSactive());
		productDAO.updateProduct(product);
		
		return (isActive)? "You are succssfully deactivate the product with ID " + product.getProductId() : 
			"You are succssfully deactivate the product with ID " + product.getProductId();
	}
	
	
	//To handleCategory submission
	@RequestMapping(value="/category" , method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category) {
		
		categoryDAO.addCategory(category);
		return "redirect:/manage/products/?operation=category";
	}
	
	
	//Return all Categories for all the request mapping
	@ModelAttribute("categories")
	public List<Category> getCategories(){
		
		return categoryDAO.getAllCategory();
		
	}
	
	@ModelAttribute("category")
	public Category getCategory() {
		return new Category();
	}

}