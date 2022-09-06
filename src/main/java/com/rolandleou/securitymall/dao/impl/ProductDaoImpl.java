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

import com.rolandleou.securitymall.constant.ProductCategory;
import com.rolandleou.securitymall.dao.ProductDao;
import com.rolandleou.securitymall.dto.ProductQueryParams;
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

	@Override
	public void updateProduct(Integer productId, ProductRequest productRequest) {
		String sql = "UPDATE product SET product_name = :productName, category = :category, "
				+ "image_url = :imageUrl, price = :price, stock = :stock, description = :description, "
				+ "last_modified_date = :lastModifiedDate WHERE product_id = :productId";
		
		Map<String, Object> map = new HashMap<>();	
		map.put("productId", productId);
		
		map.put("productName", productRequest.getProductName());
		map.put("category", productRequest.getCategory().toString());
		map.put("imageUrl", productRequest.getImageUrl());
		map.put("price", productRequest.getPrice());
		map.put("stock", productRequest.getStock());
		map.put("description", productRequest.getDescription());
		
		Date now = new Date();
		map.put("lastModifiedDate", now);		
		
		namedParameterJdbcTemplate.update(sql, map);
		
	}

	@Override
	public void deleteProductById(Integer productId) {
		String sql = "DELETE FROM product WHERE product_id = :productId";
		
		Map<String, Object> map = new HashMap<>();	
		map.put("productId", productId);
		
		namedParameterJdbcTemplate.update(sql, map);
	}

	@Override
	public List<Product> getProducts(ProductQueryParams productQueryParams) {
		String sql = "SELECT product_id, product_name, category, image_url, price, stock, "
				+ "description, created_date, last_modified_date "
				+ "FROM product WHERE 1 = 1";
		
		Map<String, Object> map = new HashMap<>();
		
		// 查詢條件
		sql = addFilteringSql(sql, map, productQueryParams);
		
		// 排序
		sql = sql + " ORDER BY " + productQueryParams.getOrderBy() + " " + productQueryParams.getSort();
		
		// 分頁
		sql = sql + " LIMIT :limit OFFSET :offset";
		map.put("limit", productQueryParams.getLimit());
		map.put("offset", productQueryParams.getOffset());
		
		List<Product> productList = namedParameterJdbcTemplate.query(sql, map, new ProductRowMapper());
		
		return productList;
	}

	@Override
	public Integer countProduct(ProductQueryParams productQueryParams) {
		String sql = "SELECT COUNT(*) FROM product WHERE 1 = 1";
		
		Map<String, Object> map = new HashMap<>();
		
		// 查詢條件
		sql = addFilteringSql(sql, map, productQueryParams);
		
		Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
		
		return total;
	}

	@Override
	public void updateStock(Integer productId, Integer stock) {
		String sql = "UPDATE product SET stock = :stock WHERE product_id = :productId";
		
		Map<String, Object> map = new HashMap<>();
		map.put("stock", stock);
		map.put("productId", productId);
		map.put("lastModifiedDate", new Date());
		
		namedParameterJdbcTemplate.update(sql, map);
		
	}
	
	private String addFilteringSql(String sql, Map<String, Object> map, ProductQueryParams productQueryParams) {
	
		// 查詢條件
		if (productQueryParams.getProductCategory() != null) {
			sql = sql + " AND category = :category";
			map.put("category", productQueryParams.getProductCategory().name());
		}
		
		if (productQueryParams.getSearch() != null) {
			sql = sql + " AND product_name LIKE :search";
			map.put("search", "%" + productQueryParams.getSearch() + "%");
		}
		
		return sql;
	}


}
