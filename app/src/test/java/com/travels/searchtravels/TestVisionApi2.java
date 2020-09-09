package com.travels.searchtravels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import com.google.api.services.vision.v1.model.LatLng;
import com.travels.searchtravels.api.OnVisionApiListener;
import com.travels.searchtravels.api.VisionApi;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.net.URL;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;

public class TestVisionApi2 {


    @Test
    public void getData_onSuccess_doesSomething() throws InterruptedException, IOException {
        mockStatic(BitmapFactory.class);
        mockStatic(Looper.class);

        URL url = new URL("https://imgcy.trivago.com/c_limit,d_dummy.jpeg,f_auto,h_1300,q_auto,w_2000/itemimages/16/07/160773_v8.jpeg");
        Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        System.out.println("asd");
        VisionApi.findLocation(image, "ya29.a0AfH6SMCiTvJaQvVaKAeL-QOREBSq7-P_d5xIqp3gjLP7pyHeDAYL-OkGxjXCeMnlRwC8Hdw7M_wPCgMq2KRTdVkOlOl85r411YfLFRkIc_A2yAej3pnu_EaBc3voLLYqxogCQFLrzOZSQtgbmI_l53MPQh-9TRXT3WM",
                new OnVisionApiListener() {
                    @Override
                    public void onSuccess(LatLng latLng) {
                        System.out.println("latLng = " + latLng.getLatitude());
                    }

                    @Override
                    public void onErrorPlace(String category) {
                        System.out.println("onErrorPlace = " + category);
                    }

                    @Override
                    public void onError() {
                        System.out.println("onError");
                    }
                });
    }
}
