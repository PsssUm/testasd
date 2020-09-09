package com.travels.searchtravels;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.travels.searchtravels.api.VisionApi;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestVisionApiMockito {
    @Mock
    private Bitmap playerBitmap = mock(Bitmap.class);
    @Mock
    Context context = mock(Context.class);
    @Test
    void lookMomICanMockStaticMethods() {
//        playerBitmap = getBitmapFromAsset(context, );
//        assertThat(VisionApi.findLocation()).isEqualTo("John");
//
//        try (MockedStatic<VisionApi> theMock = Mockito.mockStatic(VisionApi.class)) {
//            theMock.when(VisionApi::findLocation);
//            assertThat(VisionApi.findLocation()).isEqualTo("Rafael");
//        }
//
//        assertThat(VisionApi.findLocation()).isEqualTo("John");
    }
    public static Bitmap getBitmapFromAsset(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();

        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            // handle exception
        }

        return bitmap;
    }
}
