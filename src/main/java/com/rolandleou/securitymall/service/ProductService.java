package com.rolandleou.securitymall.service;

import com.rolandleou.securitymall.dto.ProductRequest;
import com.rolandleou.securitymall.model.Product;

public interface ProductService {

	Product getProductById(Integer productId);
	
	Integer createProduct(ProductRequest productRequest);
	
	void updateProduct(Integer productId, ProductRequest productRequest);
	
	void deleteProductById(Integer productId);
}
