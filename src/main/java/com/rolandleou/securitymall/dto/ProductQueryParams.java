package com.rolandleou.securitymall.dto;

import com.rolandleou.securitymall.constant.ProductCategory;

public class ProductQueryParams {

	private ProductCategory productCategory;
 	private String search;
 	private String OrderBy;
 	private String sort;
 	private Integer limit;
 	private Integer offset;
 	


	public String getSearch() {
		return search;
	}
	public void setSearch(String search) {
		this.search = search;
	}
	public String getOrderBy() {
        return OrderBy;
	}
	public void setOrderBy(String orderBy) {
        OrderBy = orderBy;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
    public ProductCategory getProductCategory() {
        return productCategory;
    }
    public void setProductCategory(ProductCategory productCategory) {
        this.productCategory = productCategory;
    }
	public Integer getLimit() {
		return limit;
	}
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	public Integer getOffset() {
		return offset;
	}
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
 	
 	
 	
}
