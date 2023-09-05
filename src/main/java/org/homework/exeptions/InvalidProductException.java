package org.homework.exeptions;

public class InvalidProductException extends IllegalArgumentException {
    public InvalidProductException(String message) {
        super(message);
    }
}
