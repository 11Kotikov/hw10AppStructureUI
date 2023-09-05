package org.homework.repository.sqlite;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.homework.exeptions.CacheException;
import org.homework.exeptions.OrderNotFoundException;
import org.homework.model.Order;
import org.homework.model.OrderItem;
import org.homework.repository.OrderRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

//Здесь класс, который работает с SQLite базой данных и хранениеем данных заказов в кэше.

public class OrderRepositoryImpl implements OrderRepository {
    private Cache<Integer, Order> orderCache;
    private Connection connection;

    public OrderRepositoryImpl() {
        orderCache = Caffeine.newBuilder()
                .maximumSize(100)
                .expireAfterWrite(30, TimeUnit.MINUTES) // Настройка времени жизни записей в кэше
                .build();

        try {
            connection = DriverManager.getConnection("jdbc:sqlite:orders.db");
            createTableIfNotExists();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to SQLite database: " + e.getMessage(), e);
        }
    }

    private void createTableIfNotExists() {
        try (PreparedStatement statement = connection.prepareStatement(
                "CREATE TABLE IF NOT EXISTS orders (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "data TEXT NOT NULL)")) {
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to create table: " + e.getMessage(), e);
        }
    }

    @Override
    public void saveOrder(Order order) {
        try {
            String insertQuery = "INSERT INTO orders (data) VALUES (?)";
            try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                statement.setString(1, order.toString());
                statement.executeUpdate();
            }

            int orderId = order.hashCode();
            orderCache.put(orderId, order);
        } catch (SQLException e) {
            throw new CacheException("Error saving order: " + e.getMessage());
        }
    }

    @Override
    public Order loadOrder(int orderId) {
        Order cachedOrder = orderCache.getIfPresent(orderId);
        if (cachedOrder != null) {
            System.out.println("Loading order from cache");
            return cachedOrder;
        } else {
            System.out.println("Loading order from database");

            try {
                // Загрузка заказа из базы данных
                String selectQuery = "SELECT data FROM orders WHERE id = ?";
                try (PreparedStatement statement = connection.prepareStatement(selectQuery)) {
                    statement.setInt(1, orderId);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        String orderData = resultSet.getString("data");
                        Order databaseOrder = parseOrderFromString(orderData);
                        orderCache.put(orderId, databaseOrder);
                        return databaseOrder;
                    } else {
                        throw new OrderNotFoundException("Order not found with ID: " + orderId);
                    }
                }
            } catch (SQLException e) {
                throw new CacheException("Error loading order: " + e.getMessage());
            }
        }
    }

    private Order parseOrderFromString(String orderData) { //парсим данные чудесным образом
        List<OrderItem> orderItems = null;
        return new Order(orderItems);
    }

    @Override
    public List<Order> loadAllOrders() {
        List<Order> orders = new ArrayList<>();
        try {
            String selectAllQuery = "SELECT data FROM orders";
            try (PreparedStatement statement = connection.prepareStatement(selectAllQuery)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    String orderData = resultSet.getString("data");
                    Order order = parseOrderFromString(orderData);
                    orders.add(order);
                }
            }
        } catch (SQLException e) {
            throw new CacheException("Error loading all orders: " + e.getMessage());
        }
        return orders;
    }

    public void closeConnection() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error closing database connection: " + e.getMessage(), e);
        }
    }
}

