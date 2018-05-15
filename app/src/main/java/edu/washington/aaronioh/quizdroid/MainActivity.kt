package edu.washington.aaronioh.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        if (!OnlineRepository().topics.isEmpty()) {
            val app = QuizApp
            val topics = app.instance.getTopics()

            val listView = findViewById<ListView>(R.id.listViewTopics) as ListView

            val topicsList = listOf(topics[0].title, topics[1].title, topics[2].title)

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, topicsList)
            listView.adapter = adapter

            listView.setOnItemClickListener { _, _, position, id ->
                val intent = Intent(this, QuizFragment::class.java).apply {
                    putExtra("topicPos", position)
                }
                startActivity(intent)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.preferences, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val intent = Intent(this, Preferences::class.java)
        startActivity(intent)
        return true
    }
}
