package com.ca.mas.massample;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringContains.containsString;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;


import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.IdlingResource;
import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.ca.mas.ui.MASLoginActivity;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


@RunWith(AndroidJUnit4.class)
public class LoginUserAuthenticationTest {


    private IdlingResource mIdlingResource;

    @Rule
    public ActivityScenarioRule<MainActivity> mActivityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void LoginTest() throws InterruptedException {

        Thread.sleep(10000);
        onView(withId(R.id.activity_mas_login_edit_text_username)).check(matches(isDisplayed()));
        onView(withId(R.id.activity_mas_login_edit_text_username))
                .perform(typeText(getResourceString(R.string.user_name)), closeSoftKeyboard());

        onView(withId(R.id.activity_mas_login_edit_text_password))
                .perform(typeText(getResourceString(R.string.password)), closeSoftKeyboard());
        onView(withId(R.id.activity_mas_login_button_login)).perform(click());

        Thread.sleep(8000);

        onView(withId(R.id.apiResponse))
                .check(matches(withText("{\"products\":[{\"id\":1,\"name\":\"AC544\",\"Time 13:00\":\"On Time\"},{\"id\":2,\"name\":\"WJ6345\",\"Arrival Time 14:55\":\"Delayed\"},{\"id\":3,\"name\":\"BA2490\",\"Arrival Time 14:55\":\"On Time\"},{\"id\":4,\"name\":\"AA1452\",\"Arrival Time 15:15\":\"Early Arrival\"},{\"id\":5,\"name\":\"WJ2678\",\"Arrival Time 16:32\":\"Delayed\"},{\"id\":6,\"name\":\"AC4455\",\"Arrival Time 17:45\":\"On Time\"}],\"device_geo\":\"\",\"clientCert.subject\":\"CN=admin, OU=6c639b93a7a6dc2b98f37326b261512b3f25c060c1d754540a3f857f968ef49d, DC=AOSPonIAEmulator, O=Broadcom\"}")));

    }

    private String getResourceString(int id) {
        Context targetContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        return targetContext.getResources().getString(id);
    }


}
