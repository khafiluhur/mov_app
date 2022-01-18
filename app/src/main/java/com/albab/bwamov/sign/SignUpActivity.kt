package com.albab.bwamov.sign

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.albab.bwamov.R
import com.albab.bwamov.model.User
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUpActivity : AppCompatActivity() {

    lateinit var isUsername:String
    lateinit var isPassword:String
    lateinit var isNama:String
    lateinit var isEmail:String

    lateinit var mDatabaseReference:DatabaseReference
    lateinit var mFirebaseInstance:FirebaseDatabase
    lateinit var mDatabase:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        mFirebaseInstance = FirebaseDatabase.getInstance()
        mDatabase = FirebaseDatabase.getInstance().getReference()
        mDatabaseReference = mFirebaseInstance.getReference("User")

        btn_next.setOnClickListener {
            isUsername = extUsername.text.toString()
            isPassword = extPassword.text.toString()
            isNama = extName.text.toString()
            isEmail = extEmail.text.toString()
            if (isUsername.equals("")) {
                extUsername.error = "Silakan isi username anda"
                extUsername.requestFocus()
            } else if (isPassword.equals("")) {
                extPassword.error = "Silakan isi password anda"
                extPassword.requestFocus()
            } else if (isNama.equals("")) {
                extName.error = "Silakan isi nama anda"
                extName.requestFocus()
            } else if (isEmail.equals("")) {
                extEmail.error = "Silakan isi email anda"
                extEmail.requestFocus()
            } else {
                saveUser(isUsername, isPassword, isNama, isEmail)
            }
        }
    }

    private fun saveUser(isUsername: String, isPassword: String, isNama: String, isEmail: String) {
        var user = User()
        user.email = isEmail
        user.username = isUsername
        user.nama = isNama
        user.password = isPassword

        if (isUsername != null) {
            checkUser(isUsername, user)
        }
    }

    private fun checkUser(isUsername: String, data: User) {
        mDatabaseReference.child(isUsername).addValueEventListener(object:ValueEventListener {
            override fun onCancelled(databaseError: DatabaseError) {
                Toast.makeText(this@SignUpActivity, databaseError.message, Toast.LENGTH_LONG).show()
            }

            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var user = dataSnapshot.getValue(User::class.java)
                if (user == null) {
                    mDatabaseReference.child(isUsername).setValue(data)
                    var intent = Intent(this@SignUpActivity, SignUpPhotoActivity::class.java).putExtra("nama", data?.nama)
                    startActivity(intent)
                } else {
                    Toast.makeText(this@SignUpActivity, "User sudah digunakan", Toast.LENGTH_LONG).show()
                }

            }
        })
    }
}