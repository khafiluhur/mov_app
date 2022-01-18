package com.albab.bwamov.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.albab.bwamov.R
import com.albab.bwamov.model.Checkout
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutAdapater(private val data: ArrayList<Checkout>,
                        private val listener: (Checkout) -> Unit)
    : RecyclerView.Adapter<CheckoutAdapater.ViewHolder>() {

    lateinit var contextAdapter:Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CheckoutAdapater.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        contextAdapter = parent.context
        val inflaterView = layoutInflater.inflate(R.layout.row_item_checkout, parent, false)
        return ViewHolder(inflaterView)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: CheckoutAdapater.ViewHolder, position: Int) {
        holder.bindItem(data[position], listener, contextAdapter)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val tvKursi: TextView = view.findViewById(R.id.tv_kursi)
        private val tvHarga: TextView = view.findViewById(R.id.tv_harga)
        fun bindItem(data: Checkout, listener: (Checkout) -> Unit, context: Context) {

            val localID = Locale("id", "ID")
            val formatRupiah = NumberFormat.getCurrencyInstance(localID)
            tvHarga.setText(formatRupiah.format(data.harga?.toDouble()))

            if (data.kursi!!.startsWith("Total")) {
                tvKursi.setText(data.kursi)
                tvKursi.setCompoundDrawables(null, null, null, null)
            } else {
                tvKursi.setText("Seat No. "+data.kursi)
            }

            itemView.setOnClickListener {
                listener(data)
            }
        }

     }
}