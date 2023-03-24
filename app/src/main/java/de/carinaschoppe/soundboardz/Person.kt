package de.carinaschoppe.soundboardz

data class Person(
    val name: String,
    val audioButtons: MutableSet<AudioButton>,
    val passcode: String
)
