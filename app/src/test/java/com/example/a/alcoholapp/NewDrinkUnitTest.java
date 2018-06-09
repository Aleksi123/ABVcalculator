package com.example.a.alcoholapp;

import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.a.alcoholapp.Activity.MainActivity;
import com.example.a.alcoholapp.Activity.NewDrinkActivity;
import com.example.a.alcoholapp.Activity.ShowDrinksActivity;
import com.example.a.alcoholapp.Database.AppDatabase;
import com.example.a.alcoholapp.Database.Repository.DrinkRepository;
import com.example.a.alcoholapp.di.DatabaseModule;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static android.app.Activity.RESULT_OK;
import static com.example.a.alcoholapp.Activity.NewDrinkActivity.EXTRA_DRINK_CALORIES;
import static com.example.a.alcoholapp.Activity.NewDrinkActivity.EXTRA_DRINK_CL;
import static com.example.a.alcoholapp.Activity.NewDrinkActivity.EXTRA_DRINK_NAME;
import static org.junit.Assert.*;
import static org.robolectric.Robolectric.buildActivity;
import static org.robolectric.Shadows.shadowOf;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class NewDrinkUnitTest {

    @Test
    public void switchFromDrinksListToNewDrink() {
        ShowDrinksActivity activity = Robolectric.setupActivity(ShowDrinksActivity.class);

        //Set mocks
        DrinkRepository mockRepo = Mockito.mock(DrinkRepository.class);

        RecyclerView recyclerView = activity.findViewById(R.id.recyclerview);
        System.out.println("Number of drinks " + recyclerView.getAdapter().getItemCount());

        activity.findViewById(R.id.fab).performClick();

        Intent expectedIntent = new Intent(activity, NewDrinkActivity.class);
        Intent actual = ShadowApplication.getInstance().getNextStartedActivity();
        assertEquals(expectedIntent.getComponent(), actual.getComponent());

        //TODO find a way to elegantly mock Room database
        Intent in = new Intent();
        in.putExtra(EXTRA_DRINK_NAME, "l");
        in.putExtra(EXTRA_DRINK_CL, "2");
        in.putExtra(EXTRA_DRINK_CALORIES, "3");

        //Send a response intent
        //This throws errors or does nothing when using real google room implementation
        //shadowOf(activity).receiveResult(actual, RESULT_OK, in);

        /*NewDrinkActivity activityB = buildActivity(NewDrinkActivity.class, actual)
                .create().get();

        activityB.findViewById(R.id.button_save).performClick();
        */

        //assertEquals(DummyActivity.RESULT_CUSTOM, activityB.);
        //assertTrue(shadowActivity.isFinishing());

        //recyclerView = activity.findViewById(R.id.recyclerview);
    }

    @Test
    public void createNewDrink(){
        Intent intent = new Intent();
        NewDrinkActivity newDrinkActivity = buildActivity(NewDrinkActivity.class, intent)
                .create().get();

        assertTrue("Save button should be visible when creating a new drink", newDrinkActivity.findViewById(R.id.button_save).getVisibility() == View.VISIBLE);
        assertFalse("Delete button should NOT be visible when creating a new drink", newDrinkActivity.findViewById(R.id.deleteButton).getVisibility() == View.VISIBLE);

        //set text values
        EditText name = newDrinkActivity.findViewById(R.id.edit_drink);
        EditText cl = newDrinkActivity.findViewById(R.id.edit_cl);
        EditText calories = newDrinkActivity.findViewById(R.id.edit_calories);

        name.setText("Test");
        cl.setText("4");
        calories.setText("5");

        //Click the save button which should trigger setResult() with intent
        newDrinkActivity.findViewById(R.id.button_save).performClick();

        //Validate results
        Intent resultIntent = shadowOf(newDrinkActivity).getResultIntent();
        assertTrue("Result code should be 200",shadowOf(newDrinkActivity).getResultCode() == RESULT_OK);
        assertEquals("New drink name should be Test","Test", resultIntent.getStringExtra(NewDrinkActivity.EXTRA_DRINK_NAME));
        assertEquals("New drink cl should be 4","4", resultIntent.getStringExtra(NewDrinkActivity.EXTRA_DRINK_CL));
        assertEquals("New drink calorie value should be 5","5", resultIntent.getStringExtra(NewDrinkActivity.EXTRA_DRINK_CALORIES));
    }
}