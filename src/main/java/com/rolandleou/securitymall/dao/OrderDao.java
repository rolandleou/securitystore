package com.rolandleou.securitymall.dao;

import java.util.List;

import com.rolandleou.securitymall.model.OrderItem;

public interface OrderDao {

	Integer createOrder(Integer userId, Integer totalAmount);
	
	void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
