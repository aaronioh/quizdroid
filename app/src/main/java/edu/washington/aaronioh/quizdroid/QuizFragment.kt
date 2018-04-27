package edu.washington.aaronioh.quizdroid

import android.app.Fragment
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class QuizFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_fragment)

        val topic = intent.getStringExtra("topic")
        val desc = intent.getStringExtra("description")
        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getStringArrayExtra("answers")

        val transaction = fragmentManager.beginTransaction()

        val instance = Bundle()
        instance.putString("topic", topic)
        instance.putString("desc", desc)
        instance.putStringArray("questions", questions)
        instance.putStringArray("answers", answers)

        val topicFragment = TopicFragment()
        topicFragment.arguments = instance

        transaction.add(R.id.fragmentContainer, topicFragment).commit()
    }

    fun restart() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
