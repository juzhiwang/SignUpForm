package com.example.signupform

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private var selectedProgram: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val etName = findViewById<EditText>(R.id.etName)
        val etEmail = findViewById<EditText>(R.id.etEmail)
        val spProgram = findViewById<Spinner>(R.id.spProgram)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etPasswordConfirm = findViewById<EditText>(R.id.etPasswordConfirm)
        val btnSave = findViewById<Button>(R.id.btnSave)


        val programs = arrayOf("Please select your program", "Information Science", "Computer Science", "Math and CS", "Data Science", "Other")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, programs)
        spProgram.adapter = adapter

        spProgram.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                if (position != 0) {
                    selectedProgram = programs[position]
                } else {
                    selectedProgram = null
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                selectedProgram = null
            }
        }

        btnSave.setOnClickListener {
            val name = etName.text.toString()
            val email = etEmail.text.toString()
            val program = selectedProgram
            val password = etPassword.text.toString()
            val passwordConfirm = etPasswordConfirm.text.toString()

            if (validateInputs(name, email, program, password, passwordConfirm)) {
                Toast.makeText(this, "Welcome, $name, to the SignUpForm App", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun validateInputs(name: String, email: String, program: String?, password: String, passwordConfirm: String): Boolean {
        if (name.isBlank() || email.isBlank() || program == null || password.isBlank() || passwordConfirm.isBlank()) {
            Toast.makeText(this, "Please fill in all fields and select a program.", Toast.LENGTH_SHORT).show()
            return false
        }

        if (password != passwordConfirm) {
            Toast.makeText(this, "Passwords do not match.", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }
}
