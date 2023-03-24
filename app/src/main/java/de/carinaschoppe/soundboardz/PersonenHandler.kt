package de.carinaschoppe.soundboardz

import android.content.Context
import java.io.File

object PersonenHandler {


    fun loadPersonen(context: Context) {
        val file: File = try {
            File(context.filesDir, "personen.txt")
        } catch (e: Exception) {
            return
        }
        if (!file.exists()) {
            return
        }
        val personen = mutableSetOf<Person>()
        val lines = file.readLines()
        for (line in lines) {
            //check if the line is a person
            if (line.startsWith("Person:")) {
                //create a new person
                val name = line.substringAfter("Person:").substringBefore("Passcode:")
                val passcode = line.substringAfter("Passcode:")
                //find the person in personen that  matches
                val person = Util.persons.find { it.name == name && it.passcode == passcode }
                if (person != null) {
                    personen.add(person)
                }

            }
        }

        Util.unlockedPersons.addAll(personen)


    }

    fun savePersonen(context: Context) {
        val file = File(context.filesDir, "personen.txt")
        file.writeText("")
        for (person in Util.unlockedPersons) {
            file.appendText("Person:${person.name}Passcode:${person.passcode}")
        }
    }
}