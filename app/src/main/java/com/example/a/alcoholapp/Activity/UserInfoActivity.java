package com.example.a.alcoholapp.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.a.alcoholapp.Database.Entity.UserInfo;
import com.example.a.alcoholapp.R;
import com.example.a.alcoholapp.ViewModel.UserInfoViewModel;

public class UserInfoActivity extends AppCompatActivity {

    private EditText mEditWeightView, mEditGenderView;
    private UserInfoViewModel mUserInfoViewModel;
    TextView textWeight,textGender;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        mEditWeightView = findViewById(R.id.editTextWeight);
        mEditGenderView = findViewById(R.id.editTextGender);
        textWeight = findViewById(R.id.textViewWeight);
        textGender = findViewById(R.id.textViewGender);
        mUserInfoViewModel = ViewModelProviders.of(this).get(UserInfoViewModel.class);

        FloatingActionButton button = findViewById(R.id.fabSaveUserInfo);
        button.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                    String weight = mEditWeightView.getText().toString();
                    String gender = mEditGenderView.getText().toString();
                    // UserID is always 1, because there can only be one user at
                    // the same time. Also when user inserts user info again it gets updated,
                    // because UserInfoDao insert-method has OnConflictStrategy.REPLACE.
                    String userId = "1";
                    UserInfo userInfo = new UserInfo(userId, weight, gender);
                    mUserInfoViewModel.insert(userInfo);
                    Context context = getApplicationContext();
                    CharSequence text = "User Info saved";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
            }
        });
    }
}

