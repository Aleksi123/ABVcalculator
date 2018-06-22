package com.example.a.alcoholapp.validation;

public class NotEmptyStringValidation implements ValidationFunction<String>{
    @Override
    public boolean validate(String input) {
        return input != null && !input.isEmpty();
    }
}
