package com.example.translate.SaveScreen

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.translate.R
import com.example.translate.ROOM.Word
import kotlinx.android.synthetic.main.item_word.view.*

class WordAdapter (var items: ArrayList<Word>, val context: Context) : RecyclerView.Adapter<WordViewHolder>() {

    //lateinit var mListener: TaskItemClickListener

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): WordViewHolder {
        return WordViewHolder(LayoutInflater.from(context).inflate(R.layout.item_word, p0, false))
    }

    override fun getItemCount(): Int {
        return  items.size
    }

    override fun onBindViewHolder(p0: WordViewHolder, p1: Int) {
        p0.wordEdt.text = "$p1 ${items[p1].real_Word}"

    }

//    fun setListener(listener: TaskItemClickListener)
//    {
//        this.mListener = listener
//    }

    fun appenData(new: Word)
    {
        this.items.add(new)
        notifyItemInserted(items.size - 1)
    }

    fun removeData(position: Int)
    {
        this.items.removeAt(position)
        notifyItemMoved(position, position)
    }

    fun setData(items: ArrayList<Word>){
        this.items = items
        notifyDataSetChanged()
    }
}

class WordViewHolder(view : View) :RecyclerView.ViewHolder(view) {
    var wordEdt = view.wordEditText


}