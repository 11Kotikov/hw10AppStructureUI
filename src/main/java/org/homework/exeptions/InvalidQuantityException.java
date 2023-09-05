package org.homework.exeptions;

public class InvalidQuantityException extends IllegalArgumentException {
    public InvalidQuantityException(String message) {
        super(message);
    }
}
