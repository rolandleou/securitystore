package com.rolandleou.securitymall.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rolandleou.securitymall.constant.ProductCategory;
import com.rolandleou.securitymall.dto.ProductQueryParams;
import com.rolandleou.securitymall.dto.ProductRequest;
import com.rolandleou.securitymall.model.Product;
import com.rolandleou.securitymall.service.ProductService;
import com.rolandleou.securitymall.util.Page;

@RestController
public class ProductController {

	@Autowired
	private ProductService productService;
	
	
    @GetMapping("/products")
    public ResponseEntity<Page<Product>> getProducts(
			// filtering 查詢條件
            @RequestParam(required = false) ProductCategory category,
            @RequestParam(required = false) String search,
			// Sorting 排序
            @RequestParam(defaultValue = "created_date") String orderBy,
            @RequestParam(defaultValue = "desc") String sort,
			// Pagination 分頁           
            @RequestParam(defaultValue = "5") @Max(1000) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset
    ) {
    	
        ProductQueryParams productQueryParams = new ProductQueryParams();
        productQueryParams.setProductCategory(category);
        productQueryParams.setSearch(search);
        productQueryParams.setOrderBy(orderBy);
        productQueryParams.setSort(sort);
        productQueryParams.setLimit(limit);
        productQueryParams.setOffset(offset);
        
        List<Product> productList = productService.getProducts(productQueryParams);
 
		// 取得 product 總數
        Integer total = productService.countProduct(productQueryParams);
        
        Page<Product> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(productList);
        
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
    
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {
		
		Product product = productService.getProductById(productId);
		
		if (product != null) {
			return ResponseEntity.status(HttpStatus.OK).body(product);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}

	}
	
	@PostMapping("/products")
	public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
		
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
	
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer productId) {
		
		productService.deleteProductById(productId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
		
}
