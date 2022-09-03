package com.rolandleou.securitymall.service;

import java.util.List;

import com.rolandleou.securitymall.constant.ProductCategory;
import com.rolandleou.securitymall.dto.ProductQueryParams;
import com.rolandleou.securitymall.dto.ProductRequest;
import com.rolandleou.securitymall.model.Product;

public interface ProductService {

	Product getProductById(Integer productId);
	
	Integer createProduct(ProductRequest productRequest);
	
	void updateProduct(Integer productId, ProductRequest productRequest);
	
	void deleteProductById(Integer productId);
	
	List<Product> getProducts(ProductQueryParams productQueryParams);
}
