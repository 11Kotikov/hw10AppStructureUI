package org.homework;

import org.homework.controller.Controller;
import org.homework.repository.OrderRepository;
import org.homework.repository.OrderRepositoryImpl;
import org.homework.view.ConsoleView;

public class Main {

    public static void main(String[] args) {

        OrderRepository orderRepository = new OrderRepositoryImpl();
        ConsoleView consoleView = new ConsoleView();
        Controller controller = new Controller(orderRepository, consoleView);

        controller.run();
    }
}