package com.example.a.alcoholapp;

import android.content.Intent;

import com.example.a.alcoholapp.Activity.NewDrinkActivity;
import com.example.a.alcoholapp.Activity.ShowDrinksActivity;
import com.example.a.alcoholapp.Database.Entity.Drink;
import com.example.a.alcoholapp.Database.Repository.DrinkRepository;
import com.example.a.alcoholapp.ViewModel.DrinkViewModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.lang.reflect.Field;

import static android.app.Activity.RESULT_OK;
import static com.example.a.alcoholapp.Activity.ShowDrinksActivity.MODIFY_DRINK_ACTIVITY_REQUEST_CODE;
import static com.example.a.alcoholapp.Activity.ShowDrinksActivity.NEW_DRINK_ACTIVITY_REQUEST_CODE;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.robolectric.Robolectric.buildActivity;

@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class ShowDrinksUnitTest {

    private ShowDrinksActivity activityUnderTest;

    @Mock
    private DrinkRepository mockRepo;

    @Captor
    private ArgumentCaptor<Drink> argumentCaptor;

    /*
    Hack method which can be used to retrieve or insert objects to a field via reflection.
    Used to retrieve viewModel from activity and insert a mock on viemodels repository field.
     */
    private Object getFieldValueByName(Object instance, String fieldName, Object payload){
        Object value = null;
        Class<?> clazz = null;
        try {
            clazz = Class.forName(instance.getClass().getName());
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            value = field.get(instance);
            //if payload has a value insert it
            if(payload != null)
                field.set(instance, payload);
        } catch (ClassNotFoundException | IllegalAccessException | NoSuchFieldException e) {
            e.printStackTrace();
        }
        return value;
    }

    @Before
    public void setUp(){

        //Bug with Roboelectric crashes when run in Coverage
        MockitoAnnotations.initMocks(this);

        //Use buildActivity(ShowDrinksActivity.class).create().get()
        //instead of Robolectric.setupActivity(ShowDrinksActivity.class)
        //otherwise following exceptions gets thrown during tests
        //java.lang.IllegalStateException: Illegal connection pointer 64. Current pointers for thread Thread
        //Problem : a database connection presists between test cases so when first test has ended with open db connection. Other test
        //tries to open a new db connection an thus following exception gets

        activityUnderTest = buildActivity(ShowDrinksActivity.class).create().get();

        //Fetch activity's viewModel
        DrinkViewModel viewModel = (DrinkViewModel) getFieldValueByName(activityUnderTest, "mDrinkViewModel", null);
        //Replace the viewmodel's repository implementation with a mock
        getFieldValueByName(viewModel, "mRepository", mockRepo);
    }

    @Test
    public void insertNewDrinkUsingIntentData(){
        //Set intent data
        Intent intent = new Intent();
        intent.putExtra(NewDrinkActivity.EXTRA_DRINK_NAME, "test");
        intent.putExtra(NewDrinkActivity.EXTRA_DRINK_CL, "4");
        intent.putExtra(NewDrinkActivity.EXTRA_DRINK_CALORIES, "5");

        //Trigger method which will cause a database insert operation
        activityUnderTest.onActivityResult(NEW_DRINK_ACTIVITY_REQUEST_CODE, RESULT_OK, intent);

        //Take the parameter which was passed to our mock
        verify(mockRepo).insert(argumentCaptor.capture());

        //Get the drink object that was passed as a paremeter for our mock
        Drink drink = argumentCaptor.getValue();

        //Verify data
        assertEquals("Drink Name should be same the same as intents","test", drink.getDrink());
        assertEquals("Drink CL should be same the same as intents","4", drink.getCl());
        assertEquals("Drink Calories should be same the same as intents","5", drink.getCalories());
    }

    @Test
    public void updateDrinkUsingIntentData(){
        //Set intent data
        Intent intent = new Intent();
        intent.putExtra(NewDrinkActivity.EXTRA_DRINK_NAME, "test");
        intent.putExtra(NewDrinkActivity.EXTRA_DRINK_CL, "4");
        intent.putExtra(NewDrinkActivity.EXTRA_DRINK_CALORIES, "5");
        intent.putExtra(NewDrinkActivity.EXTRA_DRINKID, 1L);

        //Trigger method which will cause a database insert operation
        activityUnderTest.onActivityResult(MODIFY_DRINK_ACTIVITY_REQUEST_CODE, RESULT_OK, intent);

        //Take the parameter which was passed to our mock
        verify(mockRepo).insert(argumentCaptor.capture());

        //Get the drink object that was passed as a paremeter for our mock
        Drink drink = argumentCaptor.getValue();

        //Verify data
        assertEquals("Drink Name should be same the same as intent's","test", drink.getDrink());
        assertEquals("Drink CL should be same the same as intent's","4", drink.getCl());
        assertEquals("Drink Calories should be same the same as intent's","5", drink.getCalories());
        assertEquals("Drink Id should be same the same as intent's",1L, drink.getId());
    }



}
