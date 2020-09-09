package com.travels.searchtravels

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Handler
import android.os.HandlerThread
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.api.services.vision.v1.model.LatLng
import com.travels.searchtravels.api.OnVisionApiListener
import com.travels.searchtravels.api.VisionApi
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.robolectric.annotation.Config
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@Config(maxSdk = Build.VERSION_CODES.P)
class ExampleInstrumentedTest {
    val googleToken = "ya29.a0AfH6SMBlOMZxxOuPO_y1DAhHxEbmRp4k-ISJepVVXx_OV6gLDDhXKeQGUC6IRYLaOBx9u0HIx7GjLz-HP_lPZZY0jfVAnQyUWMrHVbdULHH0CBuN46Wf7RYDHGiG-29hBjoCVjda9yXFnYMit0VjfU_N7SkD4SgTVZN2"

    private fun getImage(src: String?): Bitmap? {
        return try {
            val url = URL(src)
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
            connection.doInput = true
            connection.connect()
            val input: InputStream = connection.inputStream
            BitmapFactory.decodeStream(input)
        } catch (e: IOException) {
            null
        }
    }
    @Test
    fun imagesCategoryTest() {

        val photos = arrayOf<Array<String>>(
            arrayOf("https://klike.net/uploads/posts/2019-06/1559370578_1.jpg", "mountain"),
            arrayOf("https://img1.goodfon.ru/original/2048x1365/b/3f/assiniboine-provincial-park-2914.jpg", "mountain"),
            arrayOf("https://blog.apltravel.ua/wp-content/uploads/2018/05/Jamajka-ostrov.jpg", "beach"),
            arrayOf("https://krot.info/uploads/posts/2020-01/1580232261_1-p-foni-s-zakatami-na-more-1.jpg", "sea"),
            arrayOf("https://img2.goodfon.ru/original/7420x5064/4/c7/elki-sneg-zima-les-oblaka.jpg", "snow"),
            arrayOf("https://www.blackpantera.ru/upload/iblock/fd0/Sonnik-okean.jpg", "ocean"),
            arrayOf("https://img3.goodfon.ru/original/1920x1408/8/c9/kanada-vankuver-noch-zdaniya.jpg", "other")
        )

        val handlerThread = HandlerThread("solution!")
        handlerThread.start()
        val r = Runnable {

            }
        val handler = Handler(handlerThread.looper)
        handler.postDelayed(r, 1000)
        for (photo in photos) {
            val bitmap = getImage(photo[0])
            VisionApi.findLocation(
                bitmap,
                googleToken,
                object : OnVisionApiListener {
                    override fun onSuccess(latLng: LatLng?) {
                        System.out.println("onSuccess");
                    }

                    override fun onErrorPlace(category: String) {
                        System.out.println("onErrorPlace category = " + category);
                        Assert.assertEquals(category, photo[1])
                    }

                    override fun onError() {
                        System.out.println("onError");
                        Assert.fail("Неверная категория, ожидаемый ответ: ${photo[1]}")
                    }
                })

        }
    }

    @Test
    fun imagesLocationTest() {
        val photos = arrayOf<String>("https://funart.pro/uploads/posts/2019-11/1573817042_petropavlovskaja-krepost-rossija-12.jpg",
            "https://globusmira.ru/wp-content/uploads/2019/07/s1200-1-1.jpg",
            "https://cdn.fishki.net/upload/post/201511/22/1747071/9491952.jpg"
        )
        for (photo in photos) {
            val bitmap = getImage(photo)
            VisionApi.findLocation(
                bitmap,
                googleToken,
                object : OnVisionApiListener {
                    override fun onSuccess(latLng: LatLng?) {
                        System.out.println("onSuccess");
                    }

                    override fun onErrorPlace(category: String) {
                        System.out.println("category = " + category);
                        Assert.fail("Нет данных о местоположении")
                    }

                    override fun onError() {
                        System.out.println("onError ");
                        Assert.fail("Нет данных о местоположении")
                    }
                })



        }
    }
}