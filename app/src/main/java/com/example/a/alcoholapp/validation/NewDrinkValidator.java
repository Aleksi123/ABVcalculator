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
    private List<String> fieldNames = new ArrayList<>();
    private List<ValidationFunction<String>> validators = new ArrayList<>();
    private String errorMessage = "";


    @Override
    public void registerEditText(EditText editText, String fieldName, ValidationFunction<String> validationFunction) {
        editTexts.add(editText);
        fieldNames.add(fieldName);
        validators.add(validationFunction);
    }

    @Override
    public boolean validate() {
        errorMessage = "";
        for(int i = 0; i<editTexts.size() ;i++){
            if(!validators.get(i).validate(editTexts.get(i).getText().toString())){
                //If invalid set errorMessage
                errorMessage = fieldNames.get(i) + " field has invalid value! Please check the field value";
                editTexts.get(i).setError(errorMessage);
                return false;
            }
        }
        return true;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
