package com.example.realtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabase.databinding.ActivityMainBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Users")

        binding.registerBtn.setOnClickListener {
            val name = binding.name.text.toString().trim()
            val city = binding.city.text.toString().trim()
            val age = binding.age.text.toString().trim()
            val userName = binding.userName.text.toString().trim()

            if (userName.isNotEmpty()) {
                val user = User(name, city, age, userName)
                database.child(userName).setValue(user).addOnSuccessListener {
                    clearInputFields()
                    Toast.makeText(this, "Successfully Saved", Toast.LENGTH_SHORT).show()
                }.addOnFailureListener {
                    Toast.makeText(this, "Failed to Save", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun clearInputFields() {
        with(binding) {
            name.text.clear()
            city.text.clear()
            age.text.clear()
            userName.text.clear()
        }
    }
}
