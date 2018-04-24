package edu.washington.aaronioh.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class TopicDescription : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_description)

        val topic = intent.getStringExtra("topic")
        val description = intent.getStringExtra("description")
        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getStringArrayExtra("answers")
        val num = questions.size

        val textTopic = findViewById<TextView>(R.id.textTopic) as TextView
        val textDesc = findViewById<TextView>(R.id.textDesc) as TextView
        val textNumQuestions = findViewById<TextView>(R.id.textNumQuestions) as TextView
        val buttonBegin = findViewById<Button>(R.id.buttonBegin) as Button

        textTopic.text = topic
        textDesc.text = description
        textNumQuestions.text = "Number of questions: " + num

        buttonBegin.setOnClickListener {
            val intent = Intent(this, Quiz::class.java).apply {
                putExtra("questions", questions)
                putExtra("answers", answers)
                putExtra("currQuestion", 0)
                putExtra("totalQuestions", num)
            }
            startActivity(intent)
        }
    }
}
