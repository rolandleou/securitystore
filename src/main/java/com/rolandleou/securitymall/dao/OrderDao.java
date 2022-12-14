package com.rolandleou.securitymall.dao;

import java.util.List;

import com.rolandleou.securitymall.dto.OrderQueryParams;
import com.rolandleou.securitymall.model.Order;
import com.rolandleou.securitymall.model.OrderItem;

public interface OrderDao {

	Integer createOrder(Integer userId, Integer totalAmount);
	
	void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
	
	Order getOrderById(Integer orderId);
	
	List<OrderItem> getOrderItemsByOrderId(Integer orderId);
	
	List<Order> getOrders(OrderQueryParams orderQueryParams);
	
	Integer countOrder(OrderQueryParams orderQueryParams);
}
