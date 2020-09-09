package com.travels.searchtravels

import android.graphics.BitmapFactory
import android.os.Build
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.test.InstrumentationRegistry.getInstrumentation
import androidx.test.core.app.ActivityScenario.launch
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.api.services.vision.v1.model.LatLng
import com.preview.planner.Define
import com.preview.planner.UILApplication
import com.preview.planner.prefs.AppPreferences
import com.travels.searchtravels.activity.MainActivity
import com.travels.searchtravels.api.OnVisionApiListener
import com.travels.searchtravels.api.VisionApi
import org.junit.Assert

import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RuntimeEnvironment
import org.robolectric.RuntimeEnvironment.application
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowApplication
import java.net.URL

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

@RunWith(AndroidJUnit4::class)
@Config(sdk =  intArrayOf(Build.VERSION_CODES.O_MR1))
class TestVisionApi {


    @Test
    fun createActivity() {
        val url = URL("https://imgcy.trivago.com/c_limit,d_dummy.jpeg,f_auto,h_1300,q_auto,w_2000/itemimages/16/07/160773_v8.jpeg")
        val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        Log.d("myLogs","test1")
        VisionApi.findLocation(
            image,
            "ya29.a0AfH6SMCiTvJaQvVaKAeL-QOREBSq7-P_d5xIqp3gjLP7pyHeDAYL-OkGxjXCeMnlRwC8Hdw7M_wPCgMq2KRTdVkOlOl85r411YfLFRkIc_A2yAej3pnu_EaBc3voLLYqxogCQFLrzOZSQtgbmI_l53MPQh-9TRXT3WM",
            object : OnVisionApiListener {

                override fun onSuccess(latLng: LatLng) {
                    Log.d("myLogs","test1")
                    Assert.assertEquals("Not rimini!", 2, 237473)
                }

                override fun onErrorPlace(category: String) {
                    Log.d("myLogs","test2")
                    Assert.assertEquals("Not rimini!", 2, 3)
                }

                override fun onError() {
                    Log.d("myLogs","test3")
                    Assert.assertEquals("On error!", 2, 3)
                }
            })
    }
}