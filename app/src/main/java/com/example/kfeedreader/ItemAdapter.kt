package com.example.kfeedreader

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class ItemAdapter(val list: ArrayList<MainActivity.Item>, val context: Context): RecyclerView.Adapter<ItemAdapter.ItemViewHolder>(){


    class ItemViewHolder(view: View): RecyclerView.ViewHolder(view){
        val titulo = view.findViewById<TextView>(R.id.titulo)
        val autor = view.findViewById<TextView>(R.id.autor)
        val data = view.findViewById<TextView>(R.id.data)
        val imagem = view.findViewById<ImageView>(R.id.imagem)
        val btnVerMais = view.findViewById<Button>(R.id.btnVerMais)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        val v = LayoutInflater.from(parent?.context).inflate(R.layout.item_list,parent, false)
        val ivh = ItemViewHolder(v)

        return ivh

    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        holder?.titulo?.text = list[position].titulo
        holder?.autor?.text = list[position].autor
        holder?.data?.text = SimpleDateFormat("dd/MM/yyyy", Locale("pt","BR")).format(Date(list[position].data))
        holder?.btnVerMais?.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, list[position].link)
            context.startActivity(intent)
        }

        DownloadImageTask(holder?.imagem!!).execute(list[position].imagem)



    }

    override fun getItemCount(): Int = list.size

}