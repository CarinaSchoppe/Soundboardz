package de.carinaschoppe.soundboardz

import java.io.File

object PersonenHandler {


    fun loadPersonen() {
        val file = File("personen.txt")
        if (!file.exists()) {
            return
        }
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
                    Util.unlockedPersons.add(person)
                }

            }
        }

    }

    fun savePersonen() {
        val file = File("personen.txt")
        file.writeText("")
        for (person in Util.unlockedPersons) {
            file.appendText("Person:${person.name}Passcode:${person.passcode}")
        }
    }
}