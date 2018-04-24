package edu.washington.aaronioh.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_quiz.*

class Quiz : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getStringArrayExtra("answers")
        val numCorrect = intent.getIntExtra("numCorrect", 0)
        val currQuestion = intent.getIntExtra("currQuestion", 0)
        val totalQuestions = intent.getIntExtra("totalQuestions", 0)
        val ansIndex = intent.getIntExtra("ansIndex", 0)

        val question = findViewById<TextView>(R.id.textQuestion) as TextView
        val questionNum = findViewById<TextView>(R.id.textQuestionNum) as TextView
        val choices = findViewById<RadioGroup>(R.id.radioGroupAns) as RadioGroup
        val ans1 = findViewById<RadioButton>(R.id.radioAns1) as RadioButton
        val ans2 = findViewById<RadioButton>(R.id.radioAns2) as RadioButton
        val ans3 = findViewById<RadioButton>(R.id.radioAns3) as RadioButton
        val ans4 = findViewById<RadioButton>(R.id.radioAns4) as RadioButton
        val submit = findViewById<Button>(R.id.buttonSubmit) as Button

        submit.isEnabled = false;
        choices.setOnCheckedChangeListener( { group, checkedId ->
            submit.isEnabled = true;
        })

        question.text = questions[currQuestion]
        questionNum.text = "Question #" + (currQuestion + 1)
        ans1.text = answers[ansIndex]
        ans2.text = answers[ansIndex + 1]
        ans3.text = answers[ansIndex + 2]
        ans4.text = answers[ansIndex + 3]
        submit.text = "Submit"

        submit.setOnClickListener {
            val selected = findViewById<RadioButton>(radioGroupAns.checkedRadioButtonId).text
            val answer = answers[ansIndex + 4]
            val newCorrect = if (selected == answer) numCorrect + 1 else numCorrect

            val intent = Intent(this, Answer::class.java).apply {
                putExtra("questions", questions)
                putExtra("answers", answers)
                putExtra("currQuestion", currQuestion + 1)
                putExtra("totalQuestions", totalQuestions)
                putExtra("numCorrect", newCorrect)
                putExtra("selectedAns", selected)
                putExtra("correctAns", answer)
                putExtra("ansIndex", ansIndex + 5)
            }
            startActivity(intent)
        }
    }
}
