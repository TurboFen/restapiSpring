package com.example.demo.Exceptions;

public class BillNotFoundException extends RuntimeException{
    public BillNotFoundException(Long id){
        super("Could not find this bill: " + id);
    }
}
