package org.homework.view;

import java.util.Scanner;

public class ConsoleView {

    public void displayWelcomeMessage() {
        System.out.println("Welcome to my Java Coffee Shop!!");
    }

    public void displayOrderDetails(String details) {
        System.out.println("Order Details: ");
        System.out.println(details);
    }

    public int getUserInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Enter your choice: ");
                int input = scanner.nextInt();
                return input;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    public void displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Create a new order");
        System.out.println("2. Display all orders");
        System.out.println("3. Exit");
    }

    public void displayProductMenu() {
        System.out.println("Select a product:");
        System.out.println("1. Latte");
        System.out.println("2. Cappuccino");
        System.out.println("3. Americano");
        System.out.println("4. Strawberry Donut");
        System.out.println("5. Chocolate Donut");
        System.out.println("6. Caramel Donut");
    }

    public void displayEnterQuantityMessage() {
        System.out.print("// quantity: ");
    }

    public void displayItemAddedMessage() {
        System.out.println("Item added to the order.");
    }

    public void displayInvalidQuantityMessage() {
        System.out.println("Invalid quantity. Please enter a positive number.");
    }

    public void displayInvalidProductMessage() {
        System.out.println("Invalid product selection. Please select a valid product.");
    }

    public void displayAddAnotherItemMessage() {
        System.out.println("Add another item? (1. Yes / 2. No)");
    }

    public void displayNoOrdersMessage() {
        System.out.println("No orders found.");
    }

    public void displayInvalidChoiceMessage() {
        System.out.println("Wrong choice. Please enter a valid choice.");
    }

    public void displayOrderPlacedMessage() {
        System.out.println("Order placed successfully.");
    }

    public void displayErrorMessage(String s) {
        System.out.println("Error: " + s);
    }
}