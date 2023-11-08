package com.example.webshopdip.exceptions;

public class CustomersAlreadyExistException extends Exception{
    public CustomersAlreadyExistException(String message) {
        super(message);
    }
}