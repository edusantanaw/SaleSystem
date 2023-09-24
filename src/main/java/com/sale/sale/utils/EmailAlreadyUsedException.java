package com.sale.sale.utils;

public class EmailAlreadyUsedException extends Exception {
    public EmailAlreadyUsedException(){
        super("Email already being used!");
    }
}
