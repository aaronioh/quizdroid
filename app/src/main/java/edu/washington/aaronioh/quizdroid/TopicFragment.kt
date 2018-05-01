package edu.washington.aaronioh.quizdroid

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView

class TopicFragment : Fragment() {

    private lateinit var topic : Topic
    var topicPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (arguments != null) {
            topicPos = arguments.getInt("topicPos")
            topic = QuizApp.instance.getTopic(topicPos)
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = inflater?.inflate(R.layout.fragment_topic, container, false) as View

        val textTopic = view.findViewById<TextView>(R.id.textTopic) as TextView
        val textDesc = view.findViewById<TextView>(R.id.textDesc) as TextView
        val textNumQuestions = view.findViewById<TextView>(R.id.textNumQuestions) as TextView
        val buttonBegin = view.findViewById<Button>(R.id.buttonBegin) as Button

        textTopic.text = topic.title
        textDesc.text = topic.shortDesc
        textNumQuestions.text = "Number of questions: " + topic.questions.size

        buttonBegin.setOnClickListener {
            val transaction = fragmentManager.beginTransaction()
            val questionFragment = QuestionFragment()

            val instance = Bundle()
            instance.putInt("topicPos", topicPos)
            instance.putInt("numCorrect", 0)
            instance.putInt("currQuestion", 0)

            questionFragment.arguments = instance
            transaction.replace(R.id.fragmentContainer, questionFragment).commit()
        }
        return view
    }
}
