package mk.ukim.fk.mpipaud

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import mk.ukim.fk.mpipaud.viewModels.RockPaperScissorsViewModel

class IntentActivity : AppCompatActivity() {

    private lateinit var textUserChoice: TextView
    private lateinit var btnSubmit: Button

    private lateinit var viewModel: RockPaperScissorsViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intent)

        textUserChoice = findViewById(R.id.textUserChoice)
        btnSubmit = findViewById(R.id.btnSubmit)
        viewModel = ViewModelProvider(this)[RockPaperScissorsViewModel::class.java]


        var bundle: Bundle? = intent.extras

        viewModel.setUserChoice(bundle?.getString("userChoice").toString())
        textUserChoice.text = bundle?.getString("userChoice")

        btnSubmit.setOnClickListener { v ->
            Intent().let { i ->
                i.putExtra("userChoice", "TODO")
                setResult(RESULT_OK, i)
                finish()
            }

        }
    }
}