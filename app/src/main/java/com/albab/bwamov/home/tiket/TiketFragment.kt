package com.albab.bwamov.home.tiket

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.albab.bwamov.R
import com.albab.bwamov.home.dashboard.ComingSoonAdapter
import com.albab.bwamov.model.Film
import com.albab.bwamov.utli.Preference
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.fragment_tiket.*

class TiketFragment : Fragment() {

    private lateinit var preference: Preference
    private lateinit var mDatabase: DatabaseReference
    private var dataList = ArrayList<Film>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tiket, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        preference = Preference(context!!)
        mDatabase = FirebaseDatabase.getInstance().getReference("Film")

        btn_history.setOnClickListener {
            val intent = Intent(context, HistoryTicketActivity::class.java)
            startActivity(intent)
        }

        rv_tiket.layoutManager = LinearLayoutManager(context)
        getData()
    }

    private fun getData() {
        mDatabase.addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(context, ""+databaseError.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                dataList.clear()
                for (getDataSnapshot in dataSnapshot.children) {
                    val film = getDataSnapshot.getValue(Film::class.java)
                    dataList.add(film!!)
                }

                rv_tiket.adapter = ComingSoonAdapter(dataList) {
                    var intent = Intent(context, TiketActivity::class.java).putExtra("data", it)
                    startActivity(intent)
                }

                tv_total.setText("${dataList.size} Movies")
            }

        })
    }
}