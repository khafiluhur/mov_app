package com.albab.bwamov.sign

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.albab.bwamov.home.HomeActivity
import com.albab.bwamov.R
import com.albab.bwamov.model.User
import com.albab.bwamov.utli.Preference
import com.google.firebase.database.*
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal
import kotlinx.android.synthetic.main.activity_sign_in.*


class SignInActivity : AppCompatActivity() {

    lateinit var iUsername:String
    lateinit var iPassword:String

    lateinit var mDatabase:DatabaseReference
    lateinit var preference:Preference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

//        val mediaPlayer = MediaPlayer.create(this, R.raw.jingle)
//        mediaPlayer?.start()

        mDatabase = FirebaseDatabase.getInstance().getReference("User")
        preference = Preference(this)

        preference.setValue("onboarding", "1")
        if (preference.getValue("status").equals("1")) {
            finishAffinity()
            var intent = Intent(this@SignInActivity, HomeActivity::class.java)
            startActivity(intent)
        }
        btn_sign_in.setOnClickListener {
            iUsername = username.text.toString()
            iPassword = password.text.toString()

            if (iUsername.equals("")) {
                username.error = "Silakan tulis username anda"
                username.requestFocus()
            } else if (iPassword.equals("")) {
                password.error = "Silakan tulis password anda"
                password.requestFocus()
            } else {
                pushLogin(iUsername, iPassword)
            }
        }

        btn_sign_up.setOnClickListener {
            var intent = Intent(this@SignInActivity, SignUpActivity::class.java)
            startActivity(intent)
        }
        
    }


    private fun pushLogin(iUsername: String, iPassword: String) {
        mDatabase.child(iUsername).addValueEventListener(object : ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignInActivity, databaseError.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataShapshot: DataSnapshot) {
                var user = dataShapshot.getValue(User::class.java)
                if (user == null) {
                    Toast.makeText(this@SignInActivity, "Username tidak ditemukan", Toast.LENGTH_LONG).show()
                } else {
                    if (user.password.equals(iPassword)) {
                        preference.setValue("nama", user.nama.toString())
                        preference.setValue("username", user.username.toString())
                        preference.setValue("url", user.url.toString())
                        preference.setValue("email", user.email.toString())
                        preference.setValue("saldo", user.saldo.toString())
                        preference.setValue("status", "1")
                        var intent = Intent(this@SignInActivity, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this@SignInActivity, "Password anda salah", Toast.LENGTH_LONG).show()
                    }

                }
            }
        })
    }
}