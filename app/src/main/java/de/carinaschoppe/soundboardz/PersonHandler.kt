/*
 * Copyright Notice for Soundboardz
 * Copyright (c) at Carina Sophie Schoppe 2023
 * File created on 3/24/23, 11:54 PM by Carina The Latest changes made by Carina on 3/24/23, 10:42 PM All contents of "PersonHandler.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carinaschoppe.soundboardz

import android.content.Context
import android.util.Log
import java.io.File

object PersonHandler {


    fun loadPersons(context: Context) {
        val file: File = try {
            File(context.filesDir, "persons.txt")

        } catch (e: Exception) {
            Log.d("Soundboardz Load", "Loaded Line: failed")
            return
        }


        val persons = mutableSetOf<Person>()
        val lines = file.readLines()
        for (line in lines) {
            Log.d("Soundboardz Load", "Loaded Line:$line")
            //check if the line is a person
            if (line.startsWith("Person:")) {
                //create a new person
                val name = line.substringAfter("Person:").substringBefore("Passcode:")
                val passcode = line.substringAfter("Passcode:")
                //find the person in personen that  matches
                val person = Persons.persons.find { it.name == name && it.passcode == passcode }
                if (person != null) {
                    persons.add(person)
                    Log.d("Soundboardz Load", "Loaded Person:${person.name}Passcode:${person.passcode}")
                }

            }
        }

        Persons.unlockedPersons.addAll(persons)


    }

    fun savePersons(context: Context) {
        val file = File(context.filesDir, "persons.txt")
        file.writeText("")
        for (person in Persons.unlockedPersons) {
            Log.d("Soundboardz Save", "Saved Person:${person.name}Passcode:${person.passcode}")
            file.appendText("Person:${person.name}Passcode:${person.passcode}\n")
        }
    }
}