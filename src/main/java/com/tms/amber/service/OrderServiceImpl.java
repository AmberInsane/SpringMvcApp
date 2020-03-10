package com.tms.amber.service;

import com.tms.amber.domain.Order;
import com.tms.amber.repository.OrderRepository;
import com.tms.amber.repository.OrderRepositoryImpl;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {
    private OrderRepository repository = new OrderRepositoryImpl();

    @Override
    public List<Order> getAllOrders() {
        return repository.getAll();
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = repository.getById(id).get();
        repository.deleteOrder(order);
    }

    @Override
    public void addOrder(String title, Double price) {
        Order order = new Order();
        order.setName(title);
        order.setPrice(price);
        long index = repository.getAll().size() + 1;
        while (repository.getById(index).isPresent())
            index++;
        order.setId(index);
        repository.save(order);
    }

    @Override
    public Optional<Order> getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public void updateOrder(Order order) {
        repository.update(order);
    }
}
