package de.carinaschoppe.soundboardz

object Util {
    val silvi = Person("Silvi", mutableListOf(AudioButton("Bodenlos", "")), "1234");
    val persons = mutableSetOf(silvi)
    //create example of random strings and persons
    lateinit var currentPerson: Person

    val unlockedPersons = mutableSetOf<Person>()
}