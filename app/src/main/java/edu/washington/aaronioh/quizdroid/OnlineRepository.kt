package edu.washington.aaronioh.quizdroid

import android.os.AsyncTask
import org.json.JSONArray
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

class OnlineRepository : TopicRepository, AsyncTask<String, String, String> {
    val topics = ArrayList<Topic>()
    var url = ""

    constructor() {
        url = "http://tednewardsandbox.site44.com/questions.json"
        execute(url).get()
    }

    override fun getTopics(): List<Topic> {
        return topics
    }

    override fun getTopic(num: Int): Topic {
        return topics[num]
    }

    override fun doInBackground(vararg p0: String?): String {
        val connection = URL(url).openConnection() as HttpURLConnection

        var input = ""

        try {
            input = BufferedInputStream(connection.inputStream).use { it.reader().use { reader -> reader.readText() } }
        } finally {
            connection.disconnect()
        }

        val obj = JSONArray(input)

        for (i in 0..(obj.length() - 1)) {
            val topic = obj.getJSONObject(i)
            val title = topic.getString("title")
            val desc = topic.getString("desc")
            val questions = topic.getJSONArray("questions")
            val quiz = ArrayList<Quiz>()
            for (j in 0..(questions.length() - 1)) {
                val question = questions.getJSONObject(j)
                val text = question.getString("text")
                val answer = question.getInt("answer") - 1
                val jsonAnswers = question.getJSONArray("answers")
                val answers = Array(jsonAnswers.length()) { "" }
                for (k in 0..(jsonAnswers.length() - 1)) {
                    answers[k] = jsonAnswers[k].toString()
                }
                quiz.add(Quiz(text, answers, answer))
            }
            topics.add(Topic(title, desc, desc, quiz))
        }
        return input
    }
}