package com.example.a.alcoholapp.validation;

public class PercentageValidation implements ValidationFunction<String> {
    @Override
    public boolean validate(String input) {
        double result;
        try{
            result = Double.parseDouble(input);
        }catch(Exception e){
            return false;
        }
        return result >=0 && result<=100;
    }
}
