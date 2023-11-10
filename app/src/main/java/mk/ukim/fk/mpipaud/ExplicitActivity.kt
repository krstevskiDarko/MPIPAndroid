package mk.ukim.fk.mpipaud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import mk.ukim.fk.mpipaud.viewModels.RockPaperScissorsViewModel

class ExplicitActivity : AppCompatActivity() {
    private lateinit var textUserChoice: TextView

    private lateinit var viewModel: RockPaperScissorsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_explicit)

        textUserChoice = findViewById(R.id.textUserChoice)

        viewModel = ViewModelProvider(this)[RockPaperScissorsViewModel::class.java]

        viewModel.getUserChoiceValue().observe(this) {
            textUserChoice.text = viewModel.getUserChoiceValue().value
        }

        var bundle: Bundle? = intent.extras

        val choice: String? = bundle?.getString("userChoice") ?: "No choice!"

        viewModel.setUserChoice(choice.toString())

        textUserChoice.text = choice.toString()
    }
}