package mk.ukim.fk.mpipaud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import mk.ukim.fk.mpipaud.viewModels.RockPaperScissorsViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var textViewTitle:TextView
    private lateinit var editTextChoice: EditText
    private lateinit var btnSubmit: Button
    private lateinit var textViewComputerChoice:TextView
    private lateinit var textViewResult: TextView

    private lateinit var btnGoToExplicitActivity: Button
    private lateinit var btnGoToImplicitActivity: Button
    private lateinit var btnGoToIntentActivity: Button

    private lateinit var viewModel:RockPaperScissorsViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        textViewTitle = findViewById(R.id.textViewTitle)
        editTextChoice = findViewById(R.id.editTextChoice)
        btnSubmit = findViewById(R.id.btnSubmit)
        textViewComputerChoice = findViewById(R.id.textViewComputerChoice)
        textViewResult = findViewById(R.id.textViewResult)

        btnGoToExplicitActivity = findViewById(R.id.btnGoToExplicitActivity)
        btnGoToImplicitActivity = findViewById(R.id.btnGoToImplicitActivity)
        btnGoToIntentActivity = findViewById(R.id.btnGoToIntentActivity)

        viewModel = ViewModelProvider(this)[RockPaperScissorsViewModel::class.java]

        btnGoToExplicitActivity.setOnClickListener {
            Intent(this, ExplicitActivity::class.java).let{i->
                i.putExtra("userChoice", editTextChoice.text.toString())
                startActivity(i)
            }
        }

//        btnSubmit.setOnClickListener {
//            viewModel.setUserChoice(editTextChoice.text.toString())
//            val computerChoice: String = viewModel.submitChoice()
//            textViewComputerChoice.text = computerChoice
//            textViewResult.text = viewModel.winner(computerChoice)
//        }

        editTextChoice.addTextChangedListener{ newText ->
            viewModel.setUserChoice(newText.toString())
            textViewComputerChoice.text=viewModel.submitChoice()
            textViewResult.text = viewModel.winner(textViewComputerChoice.text.toString())
        }

        viewModel.getUserChoiceValue().observe(this){
            textViewComputerChoice.text=viewModel.submitChoice()
            textViewResult.text = viewModel.winner(textViewComputerChoice.text.toString())
        }
    }


}