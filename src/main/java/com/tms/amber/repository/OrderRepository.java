package com.tms.amber.repository;

import com.tms.amber.domain.Order;

import java.util.List;
import java.util.Optional;

public interface OrderRepository {
    List<Order> getAll();

    Optional<Order> getById(Long id);

    void deleteOrder(Order order);

    void save(Order order);

    void update(Order order);
}
