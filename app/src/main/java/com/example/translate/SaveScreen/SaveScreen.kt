package com.example.translate.SaveScreen

import android.arch.persistence.room.Room
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.translate.DATABASE_NAME
import com.example.translate.R
//import com.example.translate.R
import com.example.translate.ROOM.AppDatabase
import com.example.translate.ROOM.Word
import com.example.translate.ROOM.WordDAO
import kotlinx.android.synthetic.main.activity_save_screen.*
import java.util.ArrayList

class SaveScreen : AppCompatActivity()
{
    var Words: ArrayList<Word> = ArrayList()
    lateinit var dao: WordDAO

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_save_screen)

        val fragmentAdapter = tabSlideAdapter(supportFragmentManager)
        viewpager.adapter = fragmentAdapter
        main_tab.setupWithViewPager(viewpager)

        initRoomDatabase()
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

//    companion object {
//        const val DATABASE_NAME = "Group7.db"
//    }
}
