package edu.washington.aaronioh.quizdroid

interface TopicRepository {
    fun getTopics() : List<Topic>
    fun getTopic(num : Int) : Topic
}

data class Topic(val title : String, val shortDesc : String, val longDesc : String, val questions : List<Quiz>)

data class Quiz(val question : String, val answers : Array<String>, val answerIndex : Int)

