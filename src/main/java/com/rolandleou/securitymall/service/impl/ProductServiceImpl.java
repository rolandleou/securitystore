package com.rolandleou.securitymall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rolandleou.securitymall.constant.ProductCategory;
import com.rolandleou.securitymall.dao.ProductDao;
import com.rolandleou.securitymall.dto.ProductQueryParams;
import com.rolandleou.securitymall.dto.ProductRequest;
import com.rolandleou.securitymall.model.Product;
import com.rolandleou.securitymall.service.ProductService;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDao productDao;
	
	@Override
	public Product getProductById(Integer productId) {
		return productDao.getProductById(productId);
	}

	@Override
	public Integer createProduct(ProductRequest productRequest) {
		return productDao.createProduct(productRequest);
	}

	@Override
	public void updateProduct(Integer productId, ProductRequest productRequest) {
		productDao.updateProduct(productId, productRequest);
		
	}

	@Override
	public void deleteProductById(Integer productId) {
		productDao.deleteProductById(productId);
		
	}

	@Override
	public List<Product> getProducts(ProductQueryParams productQueryParams) {
		return productDao.getProducts(productQueryParams);
	}

	@Override
	public Integer countProduct(ProductQueryParams productQueryParams) {
		return productDao.countProduct(productQueryParams);
	}

}
