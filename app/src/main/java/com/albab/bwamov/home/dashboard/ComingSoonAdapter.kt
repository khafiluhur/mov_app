package com.albab.bwamov.home.dashboard

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.albab.bwamov.R
import com.albab.bwamov.model.Film
import com.bumptech.glide.Glide

class ComingSoonAdapter(private var data: ArrayList<Film>,
                        private val listerner: (Film) -> Unit)
    : RecyclerView.Adapter<ComingSoonAdapter.ViewHolder>() {

    lateinit var contextAdapter:Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ComingSoonAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflaterView = layoutInflater.inflate(R.layout.row_item_coming_soon, parent, false)
        return ViewHolder(inflaterView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: ComingSoonAdapter.ViewHolder, position: Int) {
        holder.bindItem(data[position], listerner, contextAdapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvTitle: TextView = view.findViewById(R.id.tv_kursi)
        private val tvGenre: TextView = view.findViewById(R.id.tv_genre)
        private val tvRate: TextView = view.findViewById(R.id.tv_rate)
        private val tvImage: ImageView = view.findViewById(R.id.iv_poster_image)
        fun bindItem(data:Film, listerner: (Film) -> Unit, context: Context) {
            tvTitle.setText(data.judul)
            tvGenre.setText(data.genre)
            tvRate.setText(data.rating)
            Glide.with(context)
                    .load(data.poster)
                    .into(tvImage)
            itemView.setOnClickListener {
                listerner(data)
            }
        }
    }

}
