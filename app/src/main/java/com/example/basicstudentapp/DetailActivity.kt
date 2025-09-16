package com.example.basicstudentapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvDetails = findViewById<TextView>(R.id.tvDetails)
        val btnBack = findViewById<Button>(R.id.btnBack)

        val name = intent.getStringExtra("name").orEmpty()
        val grades = intent.getIntegerArrayListExtra("grades") ?: arrayListOf()

        val max = grades.maxOrNull() ?: 0
        val min = grades.minOrNull() ?: 0
        val avg = if (grades.isNotEmpty()) grades.average() else 0.0

        tvName.text = name
        tvDetails.text = "Nilai: $grades\nMax: $max, Min: $min, Avg: %.2f".format(avg)

        btnBack.setOnClickListener { finish() }
    }
}
