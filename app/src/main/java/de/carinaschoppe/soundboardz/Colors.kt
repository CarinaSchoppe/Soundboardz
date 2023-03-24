package de.carinaschoppe.soundboardz

import androidx.compose.ui.graphics.Color

object Colors {
    val red = Color(0xFFE53935)
    val pink = Color(0xFFEC407A)
    val purple = Color(0xFFAB47BC)
    val deepPurple = Color(0xFF7E57C2)
    val indigo = Color(0xFF5C6BC0)
    val blue = Color(0xFF2196F3)
    val lightBlue = Color(0xFF03A9F4)
    val cyan = Color(0xFF00BCD4)
    val teal = Color(0xFF009688)
    val green = Color(0xFF4CAF50)
    val lightGreen = Color(0xFF8BC34A)
    val lime = Color(0xFFCDDC39)
    val yellow = Color(0xFFFFEB3B)
    val amber = Color(0xFFFFC107)
    val orange = Color(0xFFFF9800)
    val deepOrange = Color(0xFFFF5722)
    val brown = Color(0xFF795548)
    val grey = Color(0xFF9E9E9E)
    val blueGrey = Color(0xFF607D8B)

    val colors = listOf(red, pink, purple, deepPurple, indigo, blue, lightBlue, cyan, teal, green, lightGreen, lime, yellow, amber, orange, deepOrange, brown, grey, blueGrey).shuffled()
}