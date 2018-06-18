package com.example.a.alcoholapp.validation;

import android.widget.EditText;

public interface EditTextValidator<I> {
    /**
     * Register an EditText object to be validated by this validator
     * @param editText
     * @param fieldName Used in the error message to specify to the user which field failed the validation
     * @param validationFunction ValidationFunction to be used when validating EditText value
     */
    void registerEditText(EditText editText, String fieldName, ValidationFunction<I> validationFunction);
    boolean validate();
    String getErrorMessage();
}
