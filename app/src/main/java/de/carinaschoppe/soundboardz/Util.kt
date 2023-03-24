package de.carinaschoppe.soundboardz

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf

object Util {
    val silvi = Person("Silvi", mutableListOf(AudioButton("Bodenlos", "bodenlos.wav"), AudioButton("Gottlos", "gottlos.wav"), AudioButton("Bruda", "bruda.wav")), "1234")
    val paul = Person("Paul", mutableListOf(), "2345")
    val persons = mutableSetOf(silvi, paul)

    //create example of random strings and persons
    lateinit var currentPerson: Person

    val unlockedPersons = mutableStateListOf<Person>()


    @Composable
    fun wrongPasscode(context: Context) {

    }

}