package com.example.spring_boot_api.exceptions;


public class CustomerDataServiceException extends RuntimeException{

    private static final long serialVersionUID = 3082393528712744275L;

    public CustomerDataServiceException(String message){
        super(message);
    }
}
