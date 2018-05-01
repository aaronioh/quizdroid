package edu.washington.aaronioh.quizdroid

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView

class QuestionFragment : Fragment() {
    lateinit var topic : Topic
    var topicPos = 0
    var numCorrect = 0
    var currQuestion = 0
    var ansIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            topicPos = arguments.getInt("topicPos")
            topic = QuizApp.instance.getTopic(topicPos)
            numCorrect = arguments.getInt("numCorrect")
            currQuestion = arguments.getInt("currQuestion")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_question, container, false) as View

        val questionNum = view.findViewById<TextView>(R.id.textQuestionNum) as TextView
        val question = view.findViewById<TextView>(R.id.textQuestion) as TextView
        val choices = view.findViewById<RadioGroup>(R.id.radioGroupAns) as RadioGroup
        val ans1 = view.findViewById<RadioButton>(R.id.radioAns1) as RadioButton
        val ans2 = view.findViewById<RadioButton>(R.id.radioAns2) as RadioButton
        val ans3 = view.findViewById<RadioButton>(R.id.radioAns3) as RadioButton
        val ans4 = view.findViewById<RadioButton>(R.id.radioAns4) as RadioButton
        val submit = view.findViewById<Button>(R.id.buttonSubmit) as Button

        submit.isEnabled = false
        choices.setOnCheckedChangeListener( { group, checkedId ->
            submit.isEnabled = true
        })

        val quiz = topic.questions[currQuestion]
        question.text = quiz.question
        questionNum.text = "Question #" + (currQuestion + 1)
        ans1.text = quiz.answers[ansIndex]
        ans2.text = quiz.answers[ansIndex + 1]
        ans3.text = quiz.answers[ansIndex + 2]
        ans4.text = quiz.answers[ansIndex + 3]
        submit.text = "Submit"

        submit.setOnClickListener {
            val selected = view.findViewById<RadioButton>(choices.checkedRadioButtonId).text.toString()
            val answer = quiz.answers[quiz.answerIndex]
            val newCorrect = if (selected == answer) numCorrect + 1 else numCorrect

            val transaction = fragmentManager.beginTransaction()
            val answerFragment = AnswerFragment()

            val instance = Bundle()
            instance.putInt("topicPos", topicPos)
            instance.putInt("numCorrect", newCorrect)
            instance.putInt("currQuestion", currQuestion + 1)
            instance.putString("selectedAns", selected)
            instance.putString("correctAns", answer)

            answerFragment.arguments = instance
            transaction.replace(R.id.fragmentContainer, answerFragment).commit()
        }

        return view;
    }
}
