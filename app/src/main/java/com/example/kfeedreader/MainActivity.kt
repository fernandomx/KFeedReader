package com.example.kfeedreader

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pkmmte.pkrss.Article
import com.pkmmte.pkrss.Callback
import com.pkmmte.pkrss.PkRSS

class MainActivity : AppCompatActivity(), Callback {

    lateinit var listView: RecyclerView
    lateinit var adapter: RecyclerView.Adapter<ItemAdapter.ItemViewHolder>

    val listItems = arrayListOf<Item>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val layout = LinearLayoutManager(this)

        listView = findViewById(R.id.rv)
        listView.layoutManager = layout

        adapter = ItemAdapter(listItems,this)
        listView.adapter = adapter


        PkRSS.with(this).load("https://rss.tecmundo.com.br/feed").callback(this).async()

    }

    override fun onLoaded(newArticles: MutableList<Article>?) {

        listItems.clear()

        newArticles?.mapTo(listItems) {
            Item(it.title, it.author, it.date, it.source ,it.enclosure.url)
        }

        //adapter = ItemAdapter(listItems,this)
        adapter.notifyDataSetChanged()

    }

    override fun onLoadFailed() {

    }

    override fun onPreload() {

    }



    data class Item(val titulo:String, val autor: String, val data: Long, val link: Uri, val imagem: String)
}