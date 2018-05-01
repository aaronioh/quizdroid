package edu.washington.aaronioh.quizdroid

import android.app.Application
import android.util.Log
import java.io.Serializable

class QuizApp : Application(), TopicRepository {

    companion object {
        lateinit var instance : QuizApp
            private set

        lateinit var topics : List<Topic>
    }

    override fun onCreate() {
        super.onCreate()

        instance = this

        val t1_q1 = Quiz("7x + 13 = 27, x = ?", arrayOf("1", "2", "3", "2.5"), 1)
        val t1_q2 = Quiz("What is the probability of rolling an even number twice with a fair die?",
                arrayOf("1/3", "1/4", "1/2", "1"), 1)

        val t2_q1 = Quiz("What is force?", arrayOf("F = ma", "F = mv", "F = 1/2 mv^2", "F = m/v"), 0)
        val t2_q2 = Quiz("What is the acceleration of gravity?",
                arrayOf("100 m/s^2", "29.6 m/s^2", "9.8 m/s^2", "4.98 m/s^2"), 2)

        val t3_q1 = Quiz("Peter Parker works as a photographer for:",
                arrayOf("The Daily News", "The New York Times", "The Daily Bugle", "The Daily Star"), 2)
        val t3_q2 = Quiz("Captain America was frozen in which war?",
                arrayOf("World War II", "The Cold War", "World War III", "World War I"), 0)

        val topic1Quiz = listOf(t1_q1, t1_q2)
        val topic2Quiz = listOf(t2_q1, t2_q2)
        val topic3Quiz = listOf(t3_q1, t3_q2)

        val topic1 = Topic("Math", "Numbers!", "Questions about numbers and such", topic1Quiz)
        val topic2 = Topic("Physics", "Gravity!", "Questions about physical laws", topic2Quiz)
        val topic3 = Topic("Marvel Super Heroes", "Comics!", "Questions about the Marvel Universe", topic3Quiz)

        topics = listOf(topic1, topic2, topic3);

        Log.i("QuizApp", "QuizApp onCreate running")
    }

    override fun getTopics() : List<Topic> {
        return topics;
    }

    override fun getTopic(num : Int) : Topic {
        return topics[num];
    }

}

data class Topic(val title : String, val shortDesc : String, val longDesc : String, val questions : List<Quiz>)

data class Quiz(val question : String, val answers : Array<String>, val answerIndex : Int)

interface TopicRepository {
    fun getTopics() : List<Topic>
    fun getTopic(num : Int) : Topic
}
