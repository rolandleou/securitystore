package com.rolandleou.securitymall.dao;

import com.rolandleou.securitymall.model.Product;

public interface ProductDao {

	Product getProductById(Integer productId);
	
}
