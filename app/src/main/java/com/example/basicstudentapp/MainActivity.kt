package com.example.basicstudentapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private val students = mutableListOf<Pair<String, List<Int>>>()
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.listView)
        val btnAdd = findViewById<Button>(R.id.btnAdd)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf<String>())
        listView.adapter = adapter

        btnAdd.setOnClickListener {
            val intent = Intent(this, AddActivity::class.java)
            startActivityForResult(intent, 100) // simple & cepat
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val student = students[position]
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("name", student.first)
            intent.putIntegerArrayListExtra("grades", ArrayList(student.second))
            startActivity(intent)
        }
    }

    @Deprecated("Only for simplicity in this practice; still works")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            val name = data.getStringExtra("name").orEmpty()
            val grades = data.getIntegerArrayListExtra("grades")?.toList() ?: emptyList()
            students.add(name to grades)

            // refresh list (tampilkan hanya nama di ListView)
            adapter.clear()
            adapter.addAll(students.map { it.first })
            adapter.notifyDataSetChanged()
        }
    }
}
