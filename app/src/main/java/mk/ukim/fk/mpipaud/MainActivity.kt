package mk.ukim.fk.mpipaud

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import mk.ukim.fk.mpipaud.viewModels.RockPaperScissorsViewModel
import java.util.Calendar

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

        btnGoToImplicitActivity.setOnClickListener {
            //1.Google Maps
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.com/maps/place/Faculty+of+Computer+Science+%26+Engineering/@42.0041182,21.4095479,17z/data=!3m1!4b1!4m6!3m5!1s0x13541443605aa4ab:0x33d56647e5b87264!8m2!3d42.0041182!4d21.4095479!16s%2Fg%2F1hff1_crn?entry=ttu"))
            startActivity(intent)
            //2.Sending Email
//            val intent: Intent = Intent(Intent.ACTION_SEND).let { emailIntent ->
//                emailIntent.setType("text/plain")
//                emailIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf("xxxxx@email.com"))
//                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "email subject" )
//                emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message")
//            }
//            startActivity(intent)
            //3.Calencdar
//            val intent: Intent = Intent(Intent.ACTION_INSERT, CalendarContract.Events.CONTENT_URI).let{cal ->
//                val beginTime: Calendar = Calendar.getInstance()
//                beginTime.set(2023,11,10,19,30)
//                val endTime: Calendar = Calendar.getInstance()
//                endTime.set(2023, 11, 10, 20, 0)
//
//                cal.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.timeInMillis)
//                cal.putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.timeInMillis)
//
//                cal.putExtra(CalendarContract.Events.TITLE, "Learn Android")
//                cal.putExtra(CalendarContract.Events.EVENT_LOCATION, "online")
//
//
//            }
//                startActivity(intent)

            //4.WebPage
            //startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("https://developer.android.com")))

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