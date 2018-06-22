package com.example.a.alcoholapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a.alcoholapp.R;
import com.example.a.alcoholapp.validation.NaturalNumberValidation;
import com.example.a.alcoholapp.validation.NewDrinkValidator;
import com.example.a.alcoholapp.validation.NotEmptyStringValidation;
import com.example.a.alcoholapp.validation.PercentageValidation;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

public class NewDrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINK_NAME = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_DRINK_CL = "com.example.android.wordlistsql.REPLY2";
    public static final String EXTRA_DRINK_CALORIES = "com.example.android.wordlistsql.REPLY3";
    public static final String EXTRA_DRINK_ALCOHOLPERCENTAGE = "com.example.android.wordlistsql.REPLY4";
    public static final String EXTRA_DRINK_ID = "com.example.android.wordlistsql.DRINK_ID";
    private EditText mEditDrinkView, mEditVolumeView, mEditCaloriesView, mEditAlcoholPercentage;
    @Inject
    NewDrinkValidator validator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        AndroidInjection.inject(this);
        super.onCreate(savedInstanceState);
        //Set up validator
        setContentView(R.layout.activity_new_drink);
        mEditDrinkView = findViewById(R.id.edit_drink);
        mEditVolumeView = findViewById(R.id.edit_cl);
        mEditCaloriesView = findViewById(R.id.edit_calories);
        mEditAlcoholPercentage = findViewById(R.id.alcoholPercentage);
        //Register EditTexts to the validator
        validator.registerEditText(mEditDrinkView, getString(R.string.DrinkNameEmpty), new NotEmptyStringValidation());
        validator.registerEditText(mEditVolumeView, getString(R.string.InvalidVolumeValue), new NaturalNumberValidation());
        validator.registerEditText(mEditCaloriesView, getString(R.string.InvalidCaloriesValue), new NaturalNumberValidation());
        validator.registerEditText(mEditAlcoholPercentage, getString(R.string.InvalidPercentageValue), new PercentageValidation());
        Intent intent = getIntent();
        final long drinkId = intent.getLongExtra(EXTRA_DRINK_ID, (long)-1);
        //Check if drink id was set
        if(drinkId>-1){
            //Intent has values of our drink so we are modifying EXISTING drink
            mEditDrinkView.setText(intent.getStringExtra(EXTRA_DRINK_NAME));
            mEditVolumeView.setText(String.valueOf(intent.getIntExtra(EXTRA_DRINK_CL, 0)));
            mEditCaloriesView.setText(String.valueOf(intent.getIntExtra(EXTRA_DRINK_CALORIES, 0)));
            mEditAlcoholPercentage.setText(String.valueOf(intent.getDoubleExtra(EXTRA_DRINK_ALCOHOLPERCENTAGE, 0)));
            //Set a listener for the delete drink button
            findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent replyIntent = new Intent();
                    replyIntent.putExtra(EXTRA_DRINK_ID, drinkId);
                    //Set delete flag to true
                    replyIntent.putExtra("DELETE", true);
                    setResult(RESULT_OK, replyIntent);
                    finish();
                }
            });
        } else {
            //We are creating a new drink -> hide delete button
            findViewById(R.id.deleteButton).setVisibility(View.GONE);
        }
        FloatingActionButton button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                //Validation
                if(!validator.validate()){
                    //If validation failed simply return.
                    return;
                }

                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditDrinkView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String drink = mEditDrinkView.getText().toString();
                    replyIntent.putExtra(EXTRA_DRINK_NAME, drink);

                    int cl = Integer.parseInt(mEditVolumeView.getText().toString());
                    replyIntent.putExtra(EXTRA_DRINK_CL, cl);

                    int calories = Integer.parseInt(mEditCaloriesView.getText().toString());
                    replyIntent.putExtra(EXTRA_DRINK_CALORIES, calories);

                    double percentage = Double.parseDouble(mEditAlcoholPercentage.getText().toString());
                    replyIntent.putExtra(EXTRA_DRINK_ALCOHOLPERCENTAGE, percentage);

                    //Simple check if drinkId has a valid value
                    if(drinkId>-1)
                        replyIntent.putExtra(EXTRA_DRINK_ID, drinkId);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}