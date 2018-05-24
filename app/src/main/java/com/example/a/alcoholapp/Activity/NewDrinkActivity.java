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

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static final String EXTRA_REPLY2 = "com.example.android.wordlistsql.REPLY2";
    public static final String EXTRA_REPLY3 = "com.example.android.wordlistsql.REPLY3";
    private EditText mEditDrinkView, mEditClView, mEditCaloriesView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_drink);
        mEditDrinkView = findViewById(R.id.edit_drink);
        mEditClView = findViewById(R.id.edit_cl);
        mEditCaloriesView = findViewById(R.id.edit_calories);

        FloatingActionButton button = findViewById(R.id.button_save);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mEditDrinkView.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {
                    String drink = mEditDrinkView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, drink);

                    String cl = mEditClView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY2, cl);

                    String calories = mEditCaloriesView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY3, calories);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}