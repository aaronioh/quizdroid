package edu.washington.aaronioh.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val topics = arrayOf("Math", "Physics", "Marvel Super Heroes")
        val descriptions = arrayOf("Numbers", "Gravity", "Comics")
        val topic1 = arrayOf("7x + 13 = 27, x = ?", "What is the probability of rolling an even number twice with a fair die?")
        val topic2 = arrayOf("What is force?", "What is the acceleration of gravity?")
        val topic3 = arrayOf("Peter Parker works as a photographer for:", "Captain America was frozen in which war?")
        val questions = mapOf(topics[0] to topic1, topics[1] to topic2, topics[2] to topic3)
        val topic1Ans = arrayOf("1", "2", "3", "2.5", "2",
                "1/3", "1/4", "1/2", "1", "1/4")
        val topic2Ans = arrayOf("F = ma", "F = mv", "F = 1/2 mv^2", "F = m/v", "F = ma",
                "100 m/s^2", "29.6 m/s^2", "9.8 m/s^2", "4.98 m/s^2", "9.8 m/s^2")
        val topic3Ans = arrayOf("The Daily News", "The New York Times", "The Daily Bugle", "The Daily Star", "The Daily Bugle",
                "World War II", "The Cold War", "World War III", "World War I", "World War II")
        val answers = mapOf(topics[0] to topic1Ans, topics[1] to topic2Ans, topics[2] to topic3Ans)
        val listView = findViewById<ListView>(R.id.listViewTopics) as ListView

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, topics)
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, id ->
            val intent = Intent(this, QuizFragment::class.java).apply {
                putExtra("topic", topics[position])
                putExtra("description", descriptions[position])
                putExtra("questions", questions[topics[position]])
                putExtra("answers", answers[topics[position]])
            }
            startActivity(intent)
        }
    }
}
