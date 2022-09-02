package com.rolandleou.securitymall.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.rolandleou.securitymall.dao.ProductDao;
import com.rolandleou.securitymall.dto.ProductRequest;
import com.rolandleou.securitymall.model.Product;
import com.rolandleou.securitymall.rowmapper.ProductRowMapper;

@Component
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Override
	public Product getProductById(Integer productId) {
		String sql = "SELECT product_id, product_name, category, image_url, price, stock, "
				+ "description, created_date, last_modified_date "
				+ "FROM product WHERE product_id = :productId";
		
		Map<String, Object> map = new HashMap<>();
		map.put("productId", productId);
		
		List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
		
		if (productList.size() > 0) {
			return productList.get(0);
		} else {
			return null;			
		}

	}

	@Override
	public Integer createProduct(ProductRequest productRequest) {
		String sql = "INSERT INTO product(product_name, category, image_url, price, stock, "
				+ "description, created_date, last_modified_date) "
				+ "VALUES(:productName, :category, :imageUrl, :price, :stock, "
				+ ":description, :createdDate, :lastModifiedDate)";

		Map<String, Object> map = new HashMap<>();
		map.put("productName", productRequest.getProductName());
		map.put("category", productRequest.getCategory().toString());
		map.put("imageUrl", productRequest.getImageUrl());
		map.put("price", productRequest.getPrice());
		map.put("stock", productRequest.getStock());
		map.put("description", productRequest.getDescription());
		
		Date now = new Date();
		map.put("createdDate", now);
		map.put("lastModifiedDate", now);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		
		namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);
		
		Integer productId = keyHolder.getKey().intValue();
		
		return productId;
	}

}