package com.rolandleou.securitymall.service;

import com.rolandleou.securitymall.dto.CreateOrderRequest;
import com.rolandleou.securitymall.model.Order;

public interface OrderService {

	Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
	
	Order getOrderById(Integer orderId);
}
