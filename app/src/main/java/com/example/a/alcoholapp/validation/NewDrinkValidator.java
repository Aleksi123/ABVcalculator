package com.example.a.alcoholapp.validation;

import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of EditTextValidator.
 * Validates each editText object with a ValidationFunction.
 */
public class NewDrinkValidator implements EditTextValidator<String> {

    private List<EditText> editTexts = new ArrayList<>();
    private List<String> errorMessages = new ArrayList<>();
    private List<ValidationFunction<String>> validators = new ArrayList<>();
    private String errorMessage = "";


    @Override
    public void registerEditText(EditText editText, String errorMessage, ValidationFunction<String> validationFunction) {
        editTexts.add(editText);
        errorMessages.add(errorMessage);
        validators.add(validationFunction);
    }

    @Override //true on success , false otherwise
    public boolean validate() {
        boolean userInputValid = true;
        for(int i = 0; i<editTexts.size() ;i++){
            if(!validators.get(i).validate(editTexts.get(i).getText().toString())){
                //If invalid set errorMessage
                editTexts.get(i).setError(errorMessages.get(i));
                userInputValid = false;
            }
        }
        return userInputValid;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
