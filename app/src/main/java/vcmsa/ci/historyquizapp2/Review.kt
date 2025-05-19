package vcmsa.ci.historyquizapp2

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Review : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_review)

        val container = findViewById<LinearLayout>(R.id.questionsContainer)
        val mainButton = findViewById<Button>(R.id.mainButton)
        val questions = intent.getStringArrayExtra("QUESTIONS") ?: arrayOf()
        val answers = intent.getBooleanArrayExtra("ANSWERS") ?: booleanArrayOf()


        mainButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }


        if (questions.size != answers.size) {
            finish()
            return
        }
        container.removeAllViews()
        for (i in questions.indices) {

            TextView(this).apply {
                text = "${i + 1}. ${questions[i]}"
                textSize = 20f
                setPadding(16, 16, 16, 8)
                setTextColor(ContextCompat.getColor(this@Review, R.color.black))
                container.addView(this)
            }


            TextView(this).apply {
                text = "Correct answer: ${if (answers[i]) "TRUE" else "FALSE"}"
                textSize = 18f
                setTextColor(ContextCompat.getColor(this@Review, R.color.white))
                setPadding(16, 0, 16, 16)
                container.addView(this)


            }


        }
    }
}
