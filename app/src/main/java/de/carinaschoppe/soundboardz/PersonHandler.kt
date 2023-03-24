package de.carinaschoppe.soundboardz

import android.content.Context
import java.io.File

object PersonHandler {


    fun loadPersonen(context: Context) {
        val file: File = try {
            File(context.filesDir, "personen.txt")
        } catch (e: Exception) {
            return
        }
        if (!file.exists()) {
            return
        }
        val persons = mutableSetOf<Person>()
        val lines = file.readLines()
        for (line in lines) {
            //check if the line is a person
            if (line.startsWith("Person:")) {
                //create a new person
                val name = line.substringAfter("Person:").substringBefore("Passcode:")
                val passcode = line.substringAfter("Passcode:")
                //find the person in personen that  matches
                val person = Persons.persons.find { it.name == name && it.passcode == passcode }
                if (person != null) {
                    persons.add(person)
                }

            }
        }

        Persons.unlockedPersons.addAll(persons)


    }

    fun savePersonen(context: Context) {
        val file = File(context.filesDir, "personen.txt")
        file.writeText("")
        for (person in Persons.unlockedPersons) {
            file.appendText("Person:${person.name}Passcode:${person.passcode}")
        }
    }
}