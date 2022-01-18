package com.albab.bwamov.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.albab.bwamov.R
import com.albab.bwamov.model.Film
import com.albab.bwamov.model.Plays
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class PlaysAdapter(private val data: ArrayList<Plays>,
                    private val listener: (Plays) -> Unit)
    : RecyclerView.Adapter<PlaysAdapter.ViewHolder>() {

    lateinit var contextAdapter:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlaysAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflaterView = layoutInflater.inflate(R.layout.row_item_who_play, parent, false)
        return ViewHolder(inflaterView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: PlaysAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivImage: ImageView = view.findViewById(R.id.iv_poster_image)
        private val tvName: TextView = view.findViewById(R.id.tv_kursi)
        fun bindItem(data:Plays, listener: (Plays) -> Unit, context: Context) {
            tvName.setText(data.nama)
            Glide.with(context)
                    .load(data.url)
                    .apply(RequestOptions.circleCropTransform())
                    .into(ivImage)

            itemView.setOnClickListener {
                listener(data)
            }

        }
    }
}