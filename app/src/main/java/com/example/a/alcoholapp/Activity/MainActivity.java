package com.example.a.alcoholapp.Activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.a.alcoholapp.Database.Entity.Drink;
import com.example.a.alcoholapp.ViewModel.DrinkViewModel;
import com.example.a.alcoholapp.R;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_DRINK_ACTIVITY_REQUEST_CODE = 1;

    private DrinkViewModel mDrinkViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final DrinkListAdapter adapter = new DrinkListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mDrinkViewModel = ViewModelProviders.of(this).get(DrinkViewModel.class);

        mDrinkViewModel.getAllDrinks().observe(this, new Observer<List<Drink>>() {
            @Override
            public void onChanged(@Nullable final List<Drink> drinks) {
                adapter.setDrinks(drinks);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewDrinkActivity.class);
                startActivityForResult(intent, NEW_DRINK_ACTIVITY_REQUEST_CODE);
            }
        });

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
            Drink drink = new Drink(data.getStringExtra(NewDrinkActivity.EXTRA_REPLY),data.getStringExtra(NewDrinkActivity.EXTRA_REPLY2),
                    data.getStringExtra(NewDrinkActivity.EXTRA_REPLY3));
            mDrinkViewModel.insert(drink);
            Context context = getApplicationContext();
            CharSequence text = "Drink saved";
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

}
