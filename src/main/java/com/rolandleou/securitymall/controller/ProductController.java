package com.rolandleou.securitymall.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rolandleou.securitymall.dto.ProductRequest;
import com.rolandleou.securitymall.model.Product;
import com.rolandleou.securitymall.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
		
		Product product = productService.getProductById(productId);
		
		if (product != null) {
			return ResponseEntity.status(HttpStatus.OK).body(product);
		} else {
			return null;			
		}

	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest) {
		
		Integer productId = productService.createProduct(productRequest);
		
		Product product = productService.getProductById(productId);

		return ResponseEntity.status(HttpStatus.CREATED).body(product);
	}
	
	@PutMapping("/products/{productId}")
	public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
												@RequestBody @Valid ProductRequest productRequest) {
		
		// check if product exist on product list
		Product product = productService.getProductById(productId);
		
		if (product == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		// then execute to update product info
		productService.updateProduct(productId, productRequest); 
		

		Product updateProduct = productService.getProductById(productId);
				
		return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
	}
}
