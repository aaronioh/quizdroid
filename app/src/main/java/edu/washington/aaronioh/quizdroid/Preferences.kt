package edu.washington.aaronioh.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class Preferences : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preferences)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val url = findViewById<EditText>(R.id.editURL) as EditText
        val submitURL = findViewById<Button>(R.id.buttonSubmitURL) as Button
        val updateTime = findViewById<Spinner>(R.id.spinnerUpdateTime) as Spinner

        val timeList = arrayOf("5 minutes", "15 minutes", "30 minutes", "60 minutes")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, timeList)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        updateTime.adapter = adapter


    }
}
