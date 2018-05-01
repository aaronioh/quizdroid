package edu.washington.aaronioh.quizdroid

import android.app.Fragment
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class QuizFragment : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_fragment)

        val topicPos = intent.getIntExtra("topicPos", 0)

        val transaction = fragmentManager.beginTransaction()

        val bundle = Bundle()
        bundle.putInt("topicPos", topicPos)

        val topicFragment = TopicFragment()
        topicFragment.arguments = bundle

        transaction.add(R.id.fragmentContainer, topicFragment).commit()
    }

    fun restart() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
