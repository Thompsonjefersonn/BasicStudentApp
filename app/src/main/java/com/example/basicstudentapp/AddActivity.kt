package com.example.basicstudentapp

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class AddActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add)

        val etName = findViewById<EditText>(R.id.etName)
        val etGrades = findViewById<EditText>(R.id.etGrades)
        val btnSave = findViewById<Button>(R.id.btnSave)

        btnSave.setOnClickListener {
            val name = etName.text.toString().trim()
            val grades = etGrades.text.toString()
                .split(",")
                .mapNotNull { it.trim().toIntOrNull() }

            val result = Intent()
            result.putExtra("name", name)
            result.putIntegerArrayListExtra("grades", ArrayList(grades))
            setResult(Activity.RESULT_OK, result)
            finish()
        }
    }
}
