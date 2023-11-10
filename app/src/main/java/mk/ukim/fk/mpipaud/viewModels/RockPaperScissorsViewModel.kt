package mk.ukim.fk.mpipaud.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RockPaperScissorsViewModel:ViewModel() {
    //private var _userChoice:String = ""

    private val _userChoice:MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun getUserChoiceValue():MutableLiveData<String>{
        return this._userChoice
    }

    fun getUserChoice():String{
        //return _userChoice
        return _userChoice.value.toString()
    }
    fun setUserChoice(choice:String){
        //this._userChoice=choice
        this._userChoice.value=choice
    }

    fun submitChoice():String{
        var result = (1..3).random()
        var computerChoice = when(result){
            1 -> "Rock"
            2 -> "Paper"
            else -> "Scissors"
        }

        return computerChoice
    }

    fun winner(computerChoice:String):String{
        var winner = when{

            this._userChoice.value == "Rock" && computerChoice =="Scissors" -> "You win!"
            this._userChoice.value == "Paper" && computerChoice =="Rock" -> "You win!"
            this._userChoice.value == "Scissors" && computerChoice =="Paper" -> "You win!"
            this._userChoice.value == computerChoice -> "It's a tie!"
            else -> "You lose!"
        }

        return winner
    }
}