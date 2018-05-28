package com.example.a.alcoholapp.Activity;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.a.alcoholapp.Database.Entity.Drink;
import com.example.a.alcoholapp.R;
import com.example.a.alcoholapp.ViewModel.DrinkViewModel;

public class MainActivity extends AppCompatActivity {

    Button addDrink, showDrinks, userInfo;
    public static final int NEW_DRINK_ACTIVITY_REQUEST_CODE = 1;
    public static final int MODIFY_DRINK_ACTIVITY_REQUEST_CODE = 2;
    private DrinkViewModel mDrinkViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar2);
        setSupportActionBar(toolbar);
        addDrink = findViewById(R.id.buttonAddDrink);
        showDrinks = findViewById(R.id.buttonShowDrinks);
        userInfo = findViewById(R.id.buttonUserInfo);
        addDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewDrinkActivity.class);
                startActivityForResult(intent, NEW_DRINK_ACTIVITY_REQUEST_CODE);
            }
        });
        showDrinks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ShowDrinksActivity.class);
                startActivity(intent);
            }
        });
        userInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UserInfoActivity.class);
                startActivity(intent);
            }
        });
        mDrinkViewModel = ViewModelProviders.of(this).get(DrinkViewModel.class);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_DRINK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Drink drink = new Drink(data.getStringExtra(NewDrinkActivity.EXTRA_DRINK_NAME),data.getStringExtra(NewDrinkActivity.EXTRA_DRINK_CL),
                    data.getStringExtra(NewDrinkActivity.EXTRA_DRINK_CALORIES));
            mDrinkViewModel.insert(drink);
            Context context = getApplicationContext();
            CharSequence text = "Drink saved";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        } else if(requestCode == MODIFY_DRINK_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK){
            //Check for DELETE flag
            if(data.getBooleanExtra("DELETE", false)){
                mDrinkViewModel.delete(data.getLongExtra(NewDrinkActivity.EXTRA_DRINKID, 0));
                Toast.makeText(getApplicationContext(), "DRINK DELETED", Toast.LENGTH_SHORT).show();
            }else{
                //User modified the drink. Simply insert it to database.
                //The new drink will be replaced by the old one in the database.
                Drink drink = new Drink(data.getStringExtra(NewDrinkActivity.EXTRA_DRINK_NAME),data.getStringExtra(NewDrinkActivity.EXTRA_DRINK_CL),
                        data.getStringExtra(NewDrinkActivity.EXTRA_DRINK_CALORIES));
                drink.setId(data.getLongExtra(NewDrinkActivity.EXTRA_DRINKID, 0));
                mDrinkViewModel.insert(drink);
                Toast.makeText(getApplicationContext(), "DRINK UPDATED", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
