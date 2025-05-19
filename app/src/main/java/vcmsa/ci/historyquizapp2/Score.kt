package vcmsa.ci.historyquizapp2

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Score : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score)


        val scoreTextView = findViewById<android.widget.TextView>(R.id.scoreTextView)
        val feedbackTextView = findViewById<android.widget.TextView>(R.id.feedbackTextView)
        val reviewButton = findViewById<android.widget.Button>(R.id.reviewButton)
        val exitButton = findViewById<android.widget.Button>(R.id.exitButton)

        val score = intent.getIntExtra("SCORE", 0)
        val total = intent.getIntExtra("TOTAL_QUESTIONS", 5)

        scoreTextView.text = "Your score: $score/$total"
        val feedback = if (score >= 3) "Perfect Score!!" else "Growing Genius:)"
        feedbackTextView.text = feedback

        reviewButton.setOnClickListener {
            Intent(this, Review::class.java).apply {
                putExtra("SCORE", score)
                putExtra("TOTAL_QUESTIONS", total)
                startActivity(this)
            }
        }

        exitButton.setOnClickListener {
            finishAffinity()
        }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}