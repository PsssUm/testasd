package com.travels.searchtravels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import org.junit.Test;

public class CustomTestJava {
    @Test
    public void addition_isCorrect() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inPreferredConfig = Bitmap.Config.ARGB_8888;
        Bitmap bitmap = BitmapFactory.decodeFile("/src/test/resources/test1.jpg", options);
        System.out.println("bitmap = " + bitmap);
    }
}
