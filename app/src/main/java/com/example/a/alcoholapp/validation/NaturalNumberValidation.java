package com.example.a.alcoholapp.validation;

/**
 * Simple validation class that checks the given input is a natural number integer
 */
public class NaturalNumberValidation implements ValidationFunction<String>{
    @Override
    public boolean validate(String input) {
        int result;
        try{
            result = Integer.parseInt(input);
        }catch(Exception e){
            return false;
        }
        return result >=0;
    }
}
