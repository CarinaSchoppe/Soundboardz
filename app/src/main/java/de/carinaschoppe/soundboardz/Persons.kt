package de.carinaschoppe.soundboardz

import androidx.compose.runtime.mutableStateListOf

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
    lateinit var currentPerson: Person

    val unlockedPersons = mutableStateListOf<Person>()


}