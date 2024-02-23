package com.example.realtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabase.databinding.ActivityReadDataBinding
import com.google.firebase.database.FirebaseDatabase

class ReadData : AppCompatActivity() {

    private lateinit var binding: ActivityReadDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReadDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.readdataBtn.setOnClickListener {
            val userName = binding.etusername.text.toString().trim()
            if (userName.isNotEmpty()) {
                readData(userName)
            } else {
                Toast.makeText(this, "Please enter the Username", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun readData(userName: String) {
        val database = FirebaseDatabase.getInstance().getReference("Users")
        database.child(userName).get().addOnSuccessListener { dataSnapshot ->
            if (dataSnapshot.exists()) {
                val name = dataSnapshot.child("name").value.toString()
                val city = dataSnapshot.child("city").value.toString()
                val age = dataSnapshot.child("age").value.toString()

                updateUI(name, city, age)
                Toast.makeText(this, "Successfully Read", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "User Doesn't Exist", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to Read Data", Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateUI(name: String, city: String, age: String) {
        with(binding) {
            etusername.text.clear()
            tvname.text = name
            tvcity.text = city
            tvAge.text = age
        }
    }
}
