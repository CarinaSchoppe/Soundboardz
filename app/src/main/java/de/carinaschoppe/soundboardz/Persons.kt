package de.carinaschoppe.soundboardz

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

object Persons {


    val persons = mutableSetOf(
        Person(
            "Silvi", mutableSetOf(
                AudioButton("Bodenlos", "bodenlos.wav"),
                AudioButton("Gottlos", "gottlos.wav"),
                AudioButton("Bruda", "bruda.wav")
            ),
            "jg54j"
        ),
        Person(
            "Paul",
            mutableSetOf(),
            "j85g"
        ),
        Person(
            "Carina", mutableSetOf(),
            "4j3g23g"
        )
    )

    //create example of random strings and persons
    val currentPerson = mutableStateOf<Person?>(null)

    val unlockedPersons = mutableStateListOf<Person>()


}