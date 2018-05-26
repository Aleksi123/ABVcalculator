package com.example.a.alcoholapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.a.alcoholapp.R;

public class NewDrinkActivity extends AppCompatActivity {

    public static final String EXTRA_DRINK_NAME = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_DRINK_CL = "com.example.android.wordlistsql.REPLY2";
    public static final String EXTRA_DRINK_CALORIES = "com.example.android.wordlistsql.REPLY3";
    public static final String EXTRA_DRINKID = "com.example.android.wordlistsql.DRINK_ID";
    private EditText mEditDrinkView, mEditClView, mEditCaloriesView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_drink);
        mEditDrinkView = findViewById(R.id.edit_drink);
        mEditClView = findViewById(R.id.edit_cl);
        mEditCaloriesView = findViewById(R.id.edit_calories);
        Intent intent = getIntent();
        final long drinkId = intent.getLongExtra(EXTRA_DRINKID, (long)-1);
        //Check if drink id was set
        if(drinkId>-1){
            //Intent has values of our drink so we are modifying EXISTING drink
            mEditDrinkView.setText(intent.getStringExtra(EXTRA_DRINK_NAME));
            mEditClView.setText(intent.getStringExtra(EXTRA_DRINK_CL));
            mEditCaloriesView.setText(intent.getStringExtra(EXTRA_DRINK_CALORIES));
            //Set a listener for the delete drink button
            findViewById(R.id.deleteButton).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent replyIntent = new Intent();
                    replyIntent.putExtra(EXTRA_DRINKID, drinkId);
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
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditDrinkView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String drink = mEditDrinkView.getText().toString();
                    replyIntent.putExtra(EXTRA_DRINK_NAME, drink);

                    String cl = mEditClView.getText().toString();
                    replyIntent.putExtra(EXTRA_DRINK_CL, cl);

                    String calories = mEditCaloriesView.getText().toString();
                    replyIntent.putExtra(EXTRA_DRINK_CALORIES, calories);

                    //Simple check if drinkId has a valid value
                    if(drinkId>-1)
                        replyIntent.putExtra(EXTRA_DRINKID, drinkId);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}