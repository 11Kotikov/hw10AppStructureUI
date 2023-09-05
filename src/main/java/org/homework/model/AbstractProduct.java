
package org.homework.model;

/**
 * Абстрактный класс AbstractProduct представляет собой базовый класс для всех продуктов.
 * Каждый продукт имеет уникальный идентификатор, название и цену.
 * Этот класс предоставляет общие характеристики для всех продуктов, а также определяет метод
 * getDescription(), который должен быть реализован в подклассах для предоставления
 * текстового описания продукта.
 */

public abstract class AbstractProduct {
    private int id;
    private String name;
    private double price;

    /**
     * Конструктор класса AbstractProduct.
     *
     * @param id    Уникальный идентификатор продукта.
     * @param name  Название продукта.
     * @param price Цена продукта.
     */

    public AbstractProduct(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    /**
     * Абстрактный метод, который должен быть реализован в подклассах.
     * Возвращает текстовое описание продукта.
     *
     * @return Текстовое описание продукта.
     */
    public abstract String getDescription();
}
