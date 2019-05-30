package com.example.translate.SaveScreen

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import com.example.translate.R
import com.example.translate.ROOM.Word
import kotlinx.android.synthetic.main.fragment.*

class FolderFragment: Fragment() {
    var words: ArrayList<Word> = ArrayList()
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view: View = inflater!!.inflate(R.layout.fragment, container, false)

        return view
    }

//    override fun onActivityCreated(view: View, savedInstanceState: Bundle?)
//    {
//        super.onViewCreated(view, savedInstanceState)
//    }
}