package com.tms.amber.service;

import com.tms.amber.domain.Order;
import com.tms.amber.repository.OrderRepository;
import com.tms.amber.repository.OrderRepositoryImpl;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    List<Order> getAllOrders();

    Optional<Order> getById(Long id);

    void deleteOrder(Long id);

    void addOrder(String title, Double price);

    void updateOrder(Order order);
}
