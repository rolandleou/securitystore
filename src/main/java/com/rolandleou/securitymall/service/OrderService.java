package com.rolandleou.securitymall.service;

import com.rolandleou.securitymall.dto.CreateOrderRequest;

public interface OrderService {

	Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
