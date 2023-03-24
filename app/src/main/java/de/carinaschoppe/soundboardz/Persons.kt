/*
 * Copyright Notice for Soundboardz
 * Copyright (c) at Carina Sophie Schoppe 2023
 * File created on 3/24/23, 11:54 PM by Carina The Latest changes made by Carina on 3/24/23, 11:18 PM All contents of "Persons.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

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
            mutableSetOf(
                AudioButton("Ehre", "ehre.wav"),
                AudioButton("Erste Sahne", "erste-sahne.wav")
            ),
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