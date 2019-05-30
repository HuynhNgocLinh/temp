package com.example.translate

import android.arch.persistence.room.Room
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import com.example.translate.ROOM.AppDatabase
import com.example.translate.ROOM.Word
import com.example.translate.ROOM.WordDAO
import com.example.translate.SaveScreen.SaveScreen
//import com.example.translate.SaveScreen.SaveScreen.Companion.DATABASE_NAME
import com.example.translate.SaveScreen.WordAdapter
import com.facebook.stetho.Stetho
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var mHandler: Handler
    lateinit var dao: WordDAO
    lateinit var wordAdapter: WordAdapter
    //var word = Word()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Stetho.initializeWithDefaults(this)
        initRoomDatabase()
        mHandler = Handler()
        translate.setOnClickListener{
            if(English.text == "English"){
                English.text = "Vietnamese"
                Vietnam.text = "English"
                tvEnglish.text = "VIETNAMESE"
                tvVietnamese.text = "ENGLISH"
            }else{
                English.text = "English"
                Vietnam.text = "Vietnamese"
                tvEnglish.text = "ENGLISH"
                tvVietnamese.text = "VIETNAMESE"
            }
        }
        btTranslate.setOnClickListener{
            if(tvEnglish.text == "ENGLISH"){
                Log.i("Test: ", "Hello")
                var language1 = "en-vi"
                translation(language1)
            }else{
                var language2 = "vi-en"
                translation(language2)
            }

        }
        save.setOnClickListener{

            // Add to database
            val tTranslated_Word = edVietnam.text.toString()
            val tReal_Word = edEnglish.text.toString()
            val word = Word()

            word.real_Word = tReal_Word
            word.translated_Word = tTranslated_Word
            dao.insert(word)
            wordAdapter.appenData(word)
        }
        camera.setOnClickListener{ goToCamera() }

        Save_screen.setOnClickListener(toSaveScreen)


    }

    private val toSaveScreen = View.OnClickListener {
        val intent = Intent(this, SaveScreen::class.java)
        startActivity(intent)
    }


    private fun initRoomDatabase() {
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            DATABASE_NAME
        ).allowMainThreadQueries()
            .build()
        dao = db.wordDAO()
    }

    //
    private fun goToCamera(){
        val intent = Intent(this, CamActivity::class.java)
        startActivity(intent)
    }
    private fun translation(lang: String){
        Log.i("translate", "Haha")
        var text = edEnglish.text
        var link= "https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20190523T053835Z.0b61113b08a5ff52.f4eccc205a7332836a2a4a2444997625d673ad3a&lang="+lang+"&text="+text
        //var link = "https://api.themoviedb.org/3/movie/top_rated?api_key=7519cb3f829ecd53bd9b7007076dbe23"
        Log.i("Link: ", link.toString())
        okhttp(link)
    }
    fun okhttp( a: String){
        Log.i("Okhttp: ", "Haha")
        val client = OkHttpClient()
        val request = Request.Builder()
            .header("Authorization", "token abcd")
            .url(a)
            .build()
        client.newCall(request)
            .enqueue(object: Callback {

                override fun onFailure(call: Call, e: IOException) {
                    Log.i("Fail", "No")
                    print("Fail load data")
                }
                override fun onResponse(call: Call, response: Response) {
                    Log.i("onResponse: ", "Haha")
                    if (response.isSuccessful){
                        Log.i("response: ", "HiHi")
                        var json = response.body()!!.string()
                        Log.i("JSON", json.toString())
                        var jsObect = JSONObject(json)
                        var result = jsObect.getJSONArray("text").toString()
                        var collectionType = object : TypeToken<Collection<String>>() {}.type
                        var word: ArrayList<String> = Gson().fromJson(result, collectionType)
                        Log.i("Data: ", word.toString())
                        mHandler.post(Runnable {
                            edVietnam.text = word.get(0).toString()
                        })

                    }
                }
            })

    }
}
