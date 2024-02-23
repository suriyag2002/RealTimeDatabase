package com.example.realtimedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtimedatabase.databinding.ActivityUpdateDataBinding
import com.google.firebase.database.FirebaseDatabase

class UpdateData : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateDataBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.updateBtn.setOnClickListener {
            val userName = binding.userName.text.toString().trim()
            val name = binding.name.text.toString().trim()
            val city = binding.city.text.toString().trim()
            val age = binding.age.text.toString().trim()

            if (validateInput(userName, name, city, age)) {
                updateData(userName, name, city, age)
            }
        }
    }

    private fun validateInput(userName: String, name: String, city: String, age: String): Boolean {
        if (userName.isEmpty() || name.isEmpty() || city.isEmpty() || age.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }

    private fun updateData(userName: String, name: String, city: String, age: String) {
        val database = FirebaseDatabase.getInstance().getReference("Users")
        val userUpdates = mapOf(
            "name" to name,
            "city" to city,
            "age" to age
        )

        database.child(userName).updateChildren(userUpdates).addOnSuccessListener {
            Toast.makeText(this, "Successfully Updated", Toast.LENGTH_SHORT).show()
            clearInputFields()
        }.addOnFailureListener {
            Toast.makeText(this, "Failed to Update", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clearInputFields() {
        with(binding) {
            userName.text.clear()
            name.text.clear()
            city.text.clear()
            age.text.clear()
        }
    }
}
