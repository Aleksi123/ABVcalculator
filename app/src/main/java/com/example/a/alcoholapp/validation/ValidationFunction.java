package com.example.a.alcoholapp.validation;

/**
 * Defines the method signature for all validation functions.
 * Used in EditTextValidator
 * All validation functions must implement this interface.
 * @param <I>
 */
public interface ValidationFunction<I> {
    boolean validate(I input);
}
