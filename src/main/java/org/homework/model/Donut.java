package org.homework.model;

public class Donut extends AbstractProduct {
    public Donut(int id, String name, double price) {
        super(id, name, price);
    }

    public String getDescription() {
        return "Donut{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                '}';
    }
}
