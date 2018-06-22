package com.example.a.alcoholapp.validation;

import android.widget.EditText;

public interface EditTextValidator<I> {
    /**
     * Register an EditText object to be validated by this validator
     * @param editText
     * @param errorMessage Defines a message to be shown to the user on validation fail
     * @param validationFunction ValidationFunction to be used when validating EditText value
     */
    void registerEditText(EditText editText, String errorMessage, ValidationFunction<I> validationFunction);
    boolean validate();
    String getErrorMessage();
}
