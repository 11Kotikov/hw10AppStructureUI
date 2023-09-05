package org.homework.controller;

import org.homework.exeptions.InvalidProductException;
import org.homework.exeptions.InvalidQuantityException;
import org.homework.exeptions.MenuOptionNotFoundException;
import org.homework.exeptions.RepositoryException;
import org.homework.model.*;
import org.homework.repository.OrderRepository;
import org.homework.view.ConsoleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс Controller представляет собой контроллер для управления приложением.
 * Он обрабатывает ввод пользователя, взаимодействует с моделью (классами Order и AbstractProduct)
 * и отображает информацию пользователю с помощью объекта ConsoleView.
 */

public class Controller {
    private final OrderRepository orderRepository; // Репозиторий для сохранения и загрузки заказов.
    private final ConsoleView consoleView; // Представление для взаимодействия с пользователем.

    /**
     * Конструктор класса Controller.
     *
     * @param orderRepository Репозиторий заказов, через который происходит взаимодействие с данными.
     * @param consoleView     Представление для отображения информации пользователю и получения ввода.
     */
    public Controller(OrderRepository orderRepository, ConsoleView consoleView) {
        this.orderRepository = orderRepository;
        this.consoleView = consoleView;
    }

    /**
     * Метод run() запускает приложение и обрабатывает пользовательский ввод.
     */
    public void run() {
        consoleView.displayWelcomeMessage();

        // running the Appp
        boolean running = true;
        do {
            consoleView.displayMenu();
            int choice = consoleView.getUserInput();
            try {
                switch (choice) {
                    case 1:
                        createOrder();
                        break;
                    case 2:
                        displayOrders();
                        break;
                    case 3:
                        running = false;
                        break;
                    default:
                        consoleView.displayInvalidChoiceMessage();
                        consoleView.displayErrorMessage("Invalid menu option exception.");
                }
            } catch (MenuOptionNotFoundException e) {

                consoleView.displayErrorMessage(e.getMessage());
            }
        } while (running);
    }

    private void createOrder() {
        List<OrderItem> orderItems = new ArrayList<>();
        boolean addingItems = true;

        while (addingItems) {
            consoleView.displayProductMenu();
            int productId = consoleView.getUserInput();
            AbstractProduct abstractProduct = getProductById(productId);

            if (abstractProduct != null) {
                consoleView.displayEnterQuantityMessage();
                int quantity = consoleView.getUserInput();

                if (quantity > 0) {
                    orderItems.add(new OrderItem(abstractProduct, quantity));
                    consoleView.displayItemAddedMessage();
                } else {
                    consoleView.displayInvalidQuantityMessage();
                    throw new InvalidQuantityException("Invalid quantity exception.");
                }
            } else {
                consoleView.displayInvalidProductMessage();
                throw new InvalidProductException("Invalid abstractProduct exception.");
            }

            consoleView.displayAddAnotherItemMessage();
            int choice = consoleView.getUserInput();

            if (choice != 1) {
                addingItems = false;
            }
        }

        if (!orderItems.isEmpty()) {
            Order order = new Order(orderItems);
            try {
                orderRepository.saveOrder(order);
                consoleView.displayOrderPlacedMessage();
            } catch (RepositoryException e) {
                consoleView.displayErrorMessage("Error while saving order: " + e.getMessage());
            }
        }
    }

    private AbstractProduct getProductById(int productId) {
        switch (productId) {
            case 1:
                return new Coffee(1, "Latte", 2.35);
            case 2:
                return new Coffee(2, "Cappuccino", 2.0);
            case 3:
                return new Coffee(3, "Americano", 1.75);
            case 4:
                return new Donut(4, "Strawberry", 1.5);
            case 5:
                return new Donut(5, "Chocolate", 1.5);
            case 6:
                return new Donut(6, "Caramel", 1.5);
            default:
                throw new InvalidProductException("Invalid product selection. Please select a valid product.");
        }
    }

    private void displayOrders() {
        List<Order> orders = orderRepository.loadAllOrders();
        if (orders.isEmpty()) {
            consoleView.displayNoOrdersMessage();
        } else {
            for (Order order : orders) {
                consoleView.displayOrderDetails(order.toString());
            }
        }
    }
}
