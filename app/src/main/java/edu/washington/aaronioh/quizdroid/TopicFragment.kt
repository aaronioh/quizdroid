package edu.washington.aaronioh.quizdroid

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class TopicFragment : Fragment() {

    var topic = ""
    var desc = ""
    var questions : Array<String> = arrayOf()
    var answers : Array<String> = arrayOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            topic = arguments.getString("topic")
            desc = arguments.getString("desc")
            questions = arguments.getStringArray("questions")
            answers = arguments.getStringArray("answers")
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_topic, container, false) as View

        val textTopic = view.findViewById<TextView>(R.id.textTopic) as TextView
        val textDesc = view.findViewById<TextView>(R.id.textDesc) as TextView
        val textNumQuestions = view.findViewById<TextView>(R.id.textNumQuestions) as TextView
        val buttonBegin = view.findViewById<Button>(R.id.buttonBegin) as Button

        textTopic.text = topic
        textDesc.text = desc
        textNumQuestions.text = "Number of questions: " + questions.size

        buttonBegin.setOnClickListener {
            val transaction = fragmentManager.beginTransaction()
            val questionFragment = QuestionFragment()

            val instance = Bundle()
            instance.putStringArray("questions", questions)
            instance.putStringArray("answers", answers)
            instance.putInt("numCorrect", 0)
            instance.putInt("currQuestion", 0)
            instance.putInt("totalQuestions", questions.size)
            instance.putInt("ansIndex", 0)

            questionFragment.arguments = instance
            transaction.replace(R.id.fragmentContainer, questionFragment).commit()
        }
        return view
    }
}
