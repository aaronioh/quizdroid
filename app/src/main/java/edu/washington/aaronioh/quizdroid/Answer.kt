package edu.washington.aaronioh.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Answer : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getStringArrayExtra("answers")
        val selectedAns = intent.getStringExtra("selectedAns")
        val correct = intent.getStringExtra("correctAns")
        val numCorrect = intent.getIntExtra("numCorrect", 0)
        val totalQuestions = intent.getIntExtra("totalQuestions", 0)
        val currQuestion = intent.getIntExtra("currQuestion", 0)
        val ansIndex = intent.getIntExtra("ansIndex", 0)

        val userAns = findViewById<TextView>(R.id.textUserAns) as TextView
        val correctAns = findViewById<TextView>(R.id.textCorrectAns) as TextView
        val record = findViewById<TextView>(R.id.textRecord) as TextView
        val buttonNext = findViewById<Button>(R.id.buttonNext) as Button

        userAns.text = "You chose: " + selectedAns
        correctAns.text = "Correct answer: " + correct
        record.text = "You have " + numCorrect + " out of " + currQuestion + " correct"

        val end = currQuestion == totalQuestions
        buttonNext.text = if (end) "Finish" else "Next"

        buttonNext.setOnClickListener {
            val intent : Intent
            if (!end) {
                intent = Intent(this, Quiz::class.java).apply {
                    putExtra("questions", questions)
                    putExtra("answers", answers)
                    putExtra("currQuestion", currQuestion)
                    putExtra("totalQuestions", totalQuestions)
                    putExtra("numCorrect", numCorrect)
                    putExtra("ansIndex", ansIndex)
                }
            } else {
                intent = Intent(this, MainActivity::class.java)
            }
            startActivity(intent)
        }
    }
}
