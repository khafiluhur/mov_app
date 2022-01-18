package com.albab.bwamov

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.albab.bwamov.model.Checkout
import com.albab.bwamov.model.Film
import kotlinx.android.synthetic.main.activity_choose_bench.*

class ChooseBenchActivity : AppCompatActivity() {

    var statusA1:Boolean = false
    var statusA2:Boolean = false
    var statusA3:Boolean = false
    var statusA4:Boolean = false
    var statusA5:Boolean = false
    var statusA6:Boolean = false
    var statusA7:Boolean = false
    var statusA8:Boolean = false
    var total:Int = 0

    private var dataList = ArrayList<Checkout>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_bench)

        val data = intent.getParcelableExtra<Film>("data")
        tv_kursi.text = data?.judul ?: ""

        a1.setOnClickListener {
            if (statusA1) {
                a1.setImageResource(R.drawable.ic_rectangle_empty)
                statusA1 = false
                total -= 1
                beliTiket(total)
            } else {
                a1.setImageResource(R.drawable.ic_rectangle_selected)
                statusA1 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A1", "70000")
                dataList.add(data)
            }
        }

        a2.setOnClickListener {
            if (statusA2) {
                a2.setImageResource(R.drawable.ic_rectangle_empty)
                statusA2 = false
                total -= 1
                beliTiket(total)
            } else {
                a2.setImageResource(R.drawable.ic_rectangle_selected)
                statusA2 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A2", "70000")
                dataList.add(data)
            }
        }

        a3.setOnClickListener {
            if (statusA3) {
                a3.setImageResource(R.drawable.ic_rectangle_empty)
                statusA3 = false
                total -= 1
                beliTiket(total)
            } else {
                a3.setImageResource(R.drawable.ic_rectangle_selected)
                statusA3 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A3", "70000")
                dataList.add(data)
            }
        }

        a4.setOnClickListener {
            if (statusA4) {
                a4.setImageResource(R.drawable.ic_rectangle_empty)
                statusA4 = false
                total -= 1
                beliTiket(total)
            } else {
                a4.setImageResource(R.drawable.ic_rectangle_selected)
                statusA4 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A4", "70000")
                dataList.add(data)
            }
        }

        a5.setOnClickListener {
            if (statusA5) {
                a5.setImageResource(R.drawable.ic_rectangle_empty)
                statusA5 = false
                total -= 1
                beliTiket(total)
            } else {
                a5.setImageResource(R.drawable.ic_rectangle_selected)
                statusA5 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A5", "70000")
                dataList.add(data)
            }
        }

        a6.setOnClickListener {
            if (statusA6) {
                a6.setImageResource(R.drawable.ic_rectangle_empty)
                statusA6 = false
                total -= 1
                beliTiket(total)
            } else {
                a6.setImageResource(R.drawable.ic_rectangle_selected)
                statusA6 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A6", "70000")
                dataList.add(data)
            }
        }

        a7.setOnClickListener {
            if (statusA7) {
                a7.setImageResource(R.drawable.ic_rectangle_empty)
                statusA7 = false
                total -= 1
                beliTiket(total)
            } else {
                a7.setImageResource(R.drawable.ic_rectangle_selected)
                statusA7 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A7", "70000")
                dataList.add(data)
            }
        }

        a8.setOnClickListener {
            if (statusA8) {
                a8.setImageResource(R.drawable.ic_rectangle_empty)
                statusA8 = false
                total -= 1
                beliTiket(total)
            } else {
                a8.setImageResource(R.drawable.ic_rectangle_selected)
                statusA8 = true
                total += 1
                beliTiket(total)

                val data = Checkout("A8", "70000")
                dataList.add(data)
            }
        }

        btn_beli.setOnClickListener {
            var intent = Intent(this, CheckoutActivity::class.java).putExtra("data", dataList)
            startActivity(intent)
        }

    }

    private fun beliTiket(total: Int) {
        if (total == 0) {
            btn_beli.setText("Beli Tiket")
            btn_beli.visibility = View.INVISIBLE
        } else {
            btn_beli.setText("Beli Tiket ("+total+")")
            btn_beli.visibility = View.VISIBLE
        }
    }
}