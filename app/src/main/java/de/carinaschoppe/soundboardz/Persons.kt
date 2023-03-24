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

import android.content.Context
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import java.io.IOException

object Persons {


    val persons = mutableSetOf<Person>()

    //create example of random strings and persons
    val currentPerson = mutableStateOf<Person?>(null)

    val unlockedPersons = mutableStateListOf<Person>()


    fun getPersonsFromFile(context: Context) {

        val lines = mutableListOf<String>()
        try {
            val inputStream = context.assets.open("persons.csv")
            inputStream.bufferedReader().useLines { data ->
                data.forEach {
                    lines.add(it)
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        //iterate over the lines beginning at the second line
        for (line in lines.subList(1, lines.size)) {
            val values = line.split(",")
            val name = values[0]
            //passcode is the last element
            val passcode = values[values.size - 1]
            //audiobuttons are from the second element to the last element
            val audioButtonsText = values.subList(1, values.size - 1)
            val audioButtons = mutableSetOf<AudioButton>()
            for (audioButtonText in audioButtonsText) {
                val buttonName = audioButtonText.substringBefore(";")
                val buttonPath = audioButtonText.substringAfter(";")
                val audioButton = AudioButton(buttonName, buttonPath)
                audioButtons.add(audioButton)
            }
            val person = Person(name, audioButtons.shuffled().toMutableSet(), passcode)
            persons.add(person)
        }

    }
}

