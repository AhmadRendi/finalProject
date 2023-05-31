package org.example.exception;

public class ErrorInput extends RuntimeException{

    public RuntimeException notNull(String obj){
        throw  new RuntimeException(obj + "tidak boleh Kosong");
    }

}
