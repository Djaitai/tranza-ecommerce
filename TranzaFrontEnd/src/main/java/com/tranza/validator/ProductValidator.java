package com.tranza.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.tranza.ecommerce.model.Product;

public class ProductValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return Product.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Product product = (Product) target;
		
		//Check whetehre file as been selected
		
		if(product.getFile()==null || product.getFile().getOriginalFilename().equals("")) {
			errors.rejectValue("file",null , "Veuillez s�lectionner un fichier image � t�l�charger!");
			return;
		}

		if(!
				(product.getFile().getContentType().equals("image/jpeg") ||
			 product.getFile().getContentType().equals("image/png")||
			 product.getFile().getContentType().equals("image/gif"))
				){
			errors.rejectValue("file",null, "S'il vous pla�t utiliser uniquement le fichier image � t�l�charge!");
			return;
		}
		
		//Adding more if statement to check
	}

}
