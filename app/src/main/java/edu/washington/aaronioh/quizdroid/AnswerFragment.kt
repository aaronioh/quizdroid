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
    private lateinit var topic : Topic
    var topicPos = 0
    var numCorrect = 0
    var currQuestion = 0
    var selectedAns = ""
    var correctAns = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            topicPos = arguments.getInt("topicPos")
            topic = QuizApp.instance.getTopic(topicPos)
            numCorrect = arguments.getInt("numCorrect")
            currQuestion = arguments.getInt("currQuestion")
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

        val end = currQuestion == topic.questions.size
        buttonNext.text = if (end) "Finish" else "Next"

        buttonNext.setOnClickListener {
            if (!end) {
                val transaction = fragmentManager.beginTransaction()

                val instance = Bundle()
                instance.putInt("topicPos", topicPos)
                instance.putInt("numCorrect", numCorrect)
                instance.putInt("currQuestion", currQuestion)

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