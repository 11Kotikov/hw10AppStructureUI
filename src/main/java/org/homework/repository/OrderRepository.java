package org.homework.repository;

import org.homework.model.Order;

import java.util.List;

public interface OrderRepository {
    void saveOrder(Order order);
    Order loadOrder(int orderId);
    List<Order> loadAllOrders();
}
