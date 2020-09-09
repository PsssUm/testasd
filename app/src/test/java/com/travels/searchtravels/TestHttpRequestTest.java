package com.travels.searchtravels;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;

import com.travels.searchtravels.api.OnVisionApiListener;
import com.travels.searchtravels.api.VisionApi;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.robolectric.annotation.Config;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;

import static com.google.android.gms.tasks.Tasks.await;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;

@Config(manifest = Config.NONE, sdk = Build.VERSION_CODES.LOLLIPOP)
public class TestHttpRequestTest {

    @Rule
    public MockWebServer mockWebServer = new MockWebServer();
    Bitmap image = getBitmapFromURL("https://imgcy.trivago.com/c_limit,d_dummy.jpeg,f_auto,h_1300,q_auto,w_2000/itemimages/16/07/160773_v8.jpeg");
    @Test
    public void getData_onSuccess_doesSomething() throws InterruptedException, IOException {
       // mockStatic(BitmapFactory.class);
        mockStatic(Looper.class);
        mockStatic(Handler.class);


        System.out.println("image = " + image);
        OnVisionApiListener callback = mock(OnVisionApiListener.class);
        mockWebServer.enqueue(new MockResponse().setBody("success"));
       VisionApi.findLocation(image, "ya29.a0AfH6SMDssUvn29mrYjoJYVWR6ifXkaKaSY1z0TQBUyaZae7yFBiNZuEkG4VgFgVETHHTphwzl_Ga-AW9lcAdgVyiGbyF-zoJE1I4ZybAXGWiisYQZowNKvlV3DjfiuQT56FjcPsmusOu5Cd2HxZ4v7YmXnhThZz0kik",
               callback);
        verify(callback, timeout(5000).times(1)).onErrorPlace("sea"); // using verify of mockito
        verify(callback, timeout(5000).times(1)).onErrorPlace("ocean");

    }
    private Bitmap loadImage (){
        URL url = null;
        try {
            url = new URL("https://imgcy.trivago.com/c_limit,d_dummy.jpeg,f_auto,h_1300,q_auto,w_2000/itemimages/16/07/160773_v8.jpeg");
            System.out.println("image url = " + url);
            Bitmap image = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            System.out.println("image return = " + image);
            return image;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        // URL url = new URL("https://imgcy.trivago.com/c_limit,d_dummy.jpeg,f_auto,h_1300,q_auto,w_2000/itemimages/16/07/160773_v8.jpeg");


    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
