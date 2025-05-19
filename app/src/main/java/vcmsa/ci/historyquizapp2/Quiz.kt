package vcmsa.ci.historyquizapp2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Quiz : AppCompatActivity() {

    private var currentQuestionIndex = 0
    private var score = 0

    private val questions = listOf(
        "Slavery was legal in every U.S state before Civil War",
        "The Anglo-Boer War ended with a British victory.",
        "The Dutch were the first Europeans to bring enslaved people of South Africa",
        "Slavery at the Cape was abolished in 1795",
        "Robben Island was once a slave trading post."
    )

    private val answers = listOf(
        true,  // Slavery was legal in every U.S state before Civil War
        true,  // The Anglo-Boer War ended with a British victory.
        true,  // The Dutch were the first Europeans to bring enslaved people of South Africa
        false, // Slavery at the cape was abolished in 1795
        true   // Robben Island was once a slave trading post.
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val trueButton = findViewById<android.widget.Button>(R.id.trueButton)
        val falseButton = findViewById<android.widget.Button>(R.id.falseButton)
        val nextButton = findViewById<android.widget.Button>(R.id.nextButton)
        val feedbackTextView = findViewById<android.widget.TextView>(R.id.feedbackTextView)
        val questionTextView = findViewById<android.widget.TextView>(R.id.questionTextView)

        displayQuestion(questionTextView)

        trueButton.setOnClickListener {
            checkAnswer(true, feedbackTextView)
        }

        falseButton.setOnClickListener {
            checkAnswer(false, feedbackTextView)
        }

        nextButton.setOnClickListener {
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                displayQuestion(questionTextView)
                feedbackTextView.visibility = android.view.View.VISIBLE
            } else {
                showFinalScore()
            }
        }
    }

    private fun displayQuestion(questionTextView: android.widget.TextView) {
        questionTextView.text = questions[currentQuestionIndex]
    }

    private fun checkAnswer(userAnswer: Boolean, feedbackTextView: android.widget.TextView) {
        val isCorrect = (userAnswer == answers[currentQuestionIndex])
        if (isCorrect) {
            score++
            feedbackTextView.text = "CORRECT!"
            feedbackTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_green_dark))
        } else {
            feedbackTextView.text = "INCORRECT!"
            feedbackTextView.setTextColor(ContextCompat.getColor(this, android.R.color.holo_red_dark))
        }
        feedbackTextView.visibility = android.view.View.VISIBLE
    }

    private fun showFinalScore() {
        Intent(this, Score::class.java).apply {
            putExtra("SCORE", score)
            putExtra("TOTAL_QUESTIONS", questions.size)
            startActivity(this)
        }
        finish()
    }
}