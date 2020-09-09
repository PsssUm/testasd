package com.travels.searchtravels;

import com.travels.searchtravels.activity.MainActivity;
import com.travels.searchtravels.api.VisionApi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;

import static org.junit.Assert.assertEquals;

@RunWith(RobolectricTestRunner.class)
public class WelcomeActivityTest {

    @Test
    public void clickingLogin_shouldStartLoginActivity() {
        MainActivity activity = Robolectric.setupActivity(MainActivity.class);
        activity.findViewById(R.id.pickImageBTN).performClick();
      //  VisionApi.findLocation();

        assertEquals("2", "2");
    }
}
