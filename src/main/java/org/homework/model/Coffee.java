package org.homework.model;

public class Coffee extends AbstractProduct
{
    public Coffee(int id, String name, double price)
    {
        super(id, name, price);
    }

    public String getDescription() {
        return "Coffee{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", price=" + getPrice() +
                '}';
    }

}
