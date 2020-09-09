package com.travels.searchtravels

import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.util.Log
import com.google.api.services.vision.v1.model.LatLng
import com.travels.searchtravels.api.OnVisionApiListener
import com.travels.searchtravels.api.VisionApi
import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import java.net.URL

class StandartTest{
    @Test
    fun createActivity() {

        val url = URL("https://imgcy.trivago.com/c_limit,d_dummy.jpeg,f_auto,h_1300,q_auto,w_2000/itemimages/16/07/160773_v8.jpeg")
        val image = BitmapFactory.decodeStream(url.openConnection().getInputStream())
        Log.d("myLogs","image = " + image)
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