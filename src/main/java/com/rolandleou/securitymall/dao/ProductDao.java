package com.rolandleou.securitymall.dao;

import com.rolandleou.securitymall.dto.ProductRequest;
import com.rolandleou.securitymall.model.Product;

public interface ProductDao {

	Product getProductById(Integer productId);
	
	Integer createProduct(ProductRequest productRequest);
	
	void updateProduct(Integer productId, ProductRequest productRequest);
}
