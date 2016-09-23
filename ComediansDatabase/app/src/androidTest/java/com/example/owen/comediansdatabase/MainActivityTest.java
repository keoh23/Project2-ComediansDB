package com.example.owen.comediansdatabase;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


/**
 * Created by Owen on 9/9/2016.
 */

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    @Test
    public void testSearch() throws Exception{
        onView(withId(R.id.search))
                .perform(click());
        onView(withId(android.support.design.R.id.search_src_text))
                .perform(typeText("patton"), pressKey(66));
    }
    //TODO add test for clicking into comedian details
    //TODO add test for adding comedians
}
