package com.rolandleou.securitymall.service;

import java.util.List;

import com.rolandleou.securitymall.dto.CreateOrderRequest;
import com.rolandleou.securitymall.dto.OrderQueryParams;
import com.rolandleou.securitymall.model.Order;

public interface OrderService {

	Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
	
	Order getOrderById(Integer orderId);
	
	List<Order> getOrders(OrderQueryParams orderQueryParams);
	
	Integer countOrder(OrderQueryParams orderQueryParams);
}
