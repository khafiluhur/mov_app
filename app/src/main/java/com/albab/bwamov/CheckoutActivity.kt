package com.albab.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import com.albab.bwamov.model.Checkout
import com.albab.bwamov.utli.Preference
import com.albab.bwamov.adapter.CheckoutAdapater
import com.albab.bwamov.home.HomeActivity
import kotlinx.android.synthetic.main.activity_checkout.*
import kotlinx.android.synthetic.main.activity_checkout.tv_saldo
import java.text.NumberFormat
import java.util.*
import kotlin.collections.ArrayList

class CheckoutActivity : AppCompatActivity() {

    private var dataList = ArrayList<Checkout>()
    private var total:Int = 0
    private lateinit var preference: Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)

        preference = Preference(this)
        dataList = intent.getSerializableExtra("data") as ArrayList<Checkout>

        if (preference.getValue("saldo").equals(preference.getValue("saldo"))) {
            currency(preference.getValue("saldo")!!.toDouble(), tv_saldo)
        }

        for (a in dataList.indices) {
            total += dataList[a].harga?.toInt() ?: 0
        }

        if (total > preference.getValue("saldo")!!.toDouble()) {
            tv_saldo.setTextColor(getResources().getColor(R.color.red))
            tv_kurang.visibility = View.VISIBLE
            btn_tiket.visibility = View.INVISIBLE
        } else {
            tv_saldo.setTextColor(getResources().getColor(R.color.green))
            tv_kurang.visibility = View.INVISIBLE
            btn_tiket.visibility = View.VISIBLE
        }

        dataList.add(Checkout("Total harus dibayar", total.toString()))

        rv_checkout.layoutManager = LinearLayoutManager(this)
        rv_checkout.adapter = CheckoutAdapater(dataList) {

        }

        btn_tiket.setOnClickListener {
            var intent = Intent(this, CheckoutSuccessActivity::class.java)
            startActivity(intent)
        }

        btn_home.setOnClickListener {
            finishAffinity()

            var intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun currency(harga: Double, textView: TextView?) {
        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        textView?.setText(format.format(harga))
    }

    private fun currencyTwo(harga: Double): String {
        val localID = Locale("in", "ID")
        val format = NumberFormat.getCurrencyInstance(localID)
        return format.format(harga)
    }
}