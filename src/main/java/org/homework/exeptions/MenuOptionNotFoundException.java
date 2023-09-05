package org.homework.exeptions;

public class MenuOptionNotFoundException extends IllegalArgumentException {
    public MenuOptionNotFoundException(String message) {
        super(message);
    }
}
