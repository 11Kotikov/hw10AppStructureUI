package org.homework.repository;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.homework.exeptions.CacheException;
import org.homework.exeptions.OrderNotFoundException;
import org.homework.model.Order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//

/**
 * !!Для демонстрации работы через консоль без SQLite!!
 *
 * Класс OrderRepositoryImpl представляет собой реализацию интерфейса OrderRepository для работы с заказами.
 * В данной реализации заказы хранятся в памяти в виде кэша и базы данных.
 * Класс использует библиотеку Caffeine для создания и управления кэшем.
 */
public class OrderRepositoryImpl implements OrderRepository {
    private Cache<Integer, Order> orderCache;
    private Map<Integer, Order> database;

    public OrderRepositoryImpl() {
        orderCache = Caffeine.newBuilder()
                .maximumSize(100)
                .build();
        database = new HashMap<>();
    }

    @Override
    public void saveOrder(Order order) {
        int orderId = order.hashCode();
        orderCache.put(orderId, order);
        database.put(orderId, order);
    }

    @Override
    public Order loadOrder(int orderId) {
        Order cachedOrder = orderCache.getIfPresent(orderId);
        if (cachedOrder != null) {
            System.out.println("Loading order from cache");
            return cachedOrder;
        } else {
            System.out.println("Loading order from database");
            Order databaseOrder = database.get(orderId);

            if (databaseOrder != null) {
                try {
                    orderCache.put(orderId, databaseOrder);
                } catch (Exception e) {
                    throw new CacheException("Error updating cache: " + e.getMessage());
                }

                return databaseOrder;
            } else {
                throw new OrderNotFoundException("Order not found with ID: " + orderId);
            }
        }

    }

    @Override
    public List<Order> loadAllOrders() {
        return new ArrayList<>(database.values());
    }
}
