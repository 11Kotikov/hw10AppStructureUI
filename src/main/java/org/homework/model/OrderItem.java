package org.homework.model;

public class OrderItem {
    private AbstractProduct abstractProduct;
    private int quantity;

    public OrderItem(AbstractProduct abstractProduct, int quantity) {
        this.abstractProduct = abstractProduct;
        this.quantity = quantity;
    }

    public AbstractProduct getProduct() {
        return abstractProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getTotalPrice() {
        return abstractProduct.getPrice() * quantity;
    }
}
