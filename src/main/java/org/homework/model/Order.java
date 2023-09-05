package org.homework.model;

import java.util.List;

public class Order {
    private List<OrderItem> orderItems;

    public Order(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public double getTotalPrice() {
        double totalPrice = 0;
        for (OrderItem item : orderItems) {
            totalPrice += item.getTotalPrice();
        }
        return totalPrice;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Order Details:\n");
        for (OrderItem item : orderItems) {
            builder.append(item.getProduct().getDescription());
            builder.append(" x");
            builder.append(item.getQuantity());
            builder.append("\n");
        }
        builder.append("Total Price: $");
        builder.append(getTotalPrice());
        return builder.toString();
    }
}
