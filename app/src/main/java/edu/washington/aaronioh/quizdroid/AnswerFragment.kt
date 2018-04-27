package edu.washington.aaronioh.quizdroid

import android.app.Fragment
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class AnswerFragment : Fragment() {
    var questions : Array<String> = arrayOf()
    var answers : Array<String> = arrayOf()
    var numCorrect = 0;
    var currQuestion = 0;
    var totalQuestions = 0;
    var ansIndex = 0;
    var selectedAns = ""
    var correctAns = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            questions = arguments.getStringArray("questions")
            answers = arguments.getStringArray("answers")
            numCorrect = arguments.getInt("numCorrect")
            currQuestion = arguments.getInt("currQuestion")
            totalQuestions = arguments.getInt("totalQuestions")
            ansIndex = arguments.getInt("ansIndex")
            selectedAns = arguments.getString("selectedAns")
            correctAns = arguments.getString("correctAns")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_answer, container, false) as View

        val userAns = view.findViewById<TextView>(R.id.textUserAns) as TextView
        val correct = view.findViewById<TextView>(R.id.textCorrectAns) as TextView
        val status = view.findViewById<TextView>(R.id.textStatus) as TextView
        val record = view.findViewById<TextView>(R.id.textRecord) as TextView
        val buttonNext = view.findViewById<Button>(R.id.buttonNext) as Button

        userAns.text = "You chose: " + selectedAns
        correct.text = "Correct answer: " + correctAns
        record.text = "You have " + numCorrect + " out of " + currQuestion + " correct"

        if (selectedAns == correctAns) {
            status.text = "Correct"
            status.setTextColor(Color.GREEN)
        } else {
            status.text = "Incorrect"
            status.setTextColor(Color.RED)
        }

        val end = currQuestion == totalQuestions
        buttonNext.text = if (end) "Finish" else "Next"

        buttonNext.setOnClickListener {
            if (!end) {
                val transaction = fragmentManager.beginTransaction()

                val instance = Bundle()
                instance.putStringArray("questions", questions)
                instance.putStringArray("answers", answers)
                instance.putInt("numCorrect", numCorrect)
                instance.putInt("currQuestion", currQuestion)
                instance.putInt("totalQuestions", totalQuestions)
                instance.putInt("ansIndex", ansIndex)

                val questionFragment = QuestionFragment()
                questionFragment.arguments = instance

                transaction.replace(R.id.fragmentContainer, questionFragment).commit()
            } else {
                (activity as QuizFragment).restart()
                /*
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                */
            }
        }
        return view
    }
}