package de.carinaschoppe.soundboardz

data class Person(val name: String,
                  val audioButtons: MutableList<AudioButton>,
                  val passcode: String)
