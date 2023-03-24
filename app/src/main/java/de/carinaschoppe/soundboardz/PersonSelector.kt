/*
 * Copyright Notice for Soundboardz
 * Copyright (c) at Carina Sophie Schoppe 2023
 * File created on 3/24/23, 11:54 PM by Carina The Latest changes made by Carina on 3/24/23, 11:37 PM All contents of "PersonSelector.kt" are protected by copyright. The copyright law, unless expressly indicated otherwise, is
 * at Carina Sophie Schoppe. All rights reserved
 * Any type of duplication, distribution, rental, sale, award,
 * Public accessibility or other use
 * requires the express written consent of Carina Sophie Schoppe.
 */

package de.carinaschoppe.soundboardz

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.carinaschoppe.soundboardz.ui.theme.SoundboardzTheme

class PersonSelector : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoundboardzTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Persons.getPersonsFromFile(this)
                    PersonHandler.loadPersons(this)
                    personScroller(this)
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun personScroller(context: Context) {
        val passcode = remember { mutableStateOf("") }
        val persons = Persons.persons.toList()
        Column {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    TextField(
                        value = passcode.value,
                        onValueChange = { passcode.value = it },
                        label = { Text("Passcode") },
                        modifier = Modifier
                            .background(Color.White)
                    )
                    val showDialog = remember { mutableStateOf(false) }
                    val delete = remember { mutableStateOf(false) }
                    Button(
                        onClick = {
                            if (passcode.value == "admin") {
                                for (person in Persons.persons) {
                                    if (Persons.unlockedPersons.contains(person))
                                        continue
                                    Persons.unlockedPersons.add(person)
                                    Log.d("SoundBoardz", "Unlocked ${person.name}")
                                }
                            } else if (passcode.value == "delete" || passcode.value == "clear") {
                                //delete the persons.txt file
                                PersonHandler.deletePersons(context)
                                Log.d("SoundBoardz", "Deleted persons.txt")
                                delete.value = true
                                Persons.unlockedPersons.clear()
                            } else {
                                val unlocked = persons.firstOrNull { it.passcode == passcode.value }
                                if (unlocked != null) {
                                    if (Persons.unlockedPersons.contains(unlocked))
                                        return@Button
                                    Log.d("SoundBoardz", "Unlocked ${unlocked.name}")
                                    Persons.unlockedPersons.add(unlocked)
                                } else {
                                    showDialog.value = true
                                    Log.d("SoundBoardz", "Wrong passcode: ${passcode.value}")
                                }
                            }
                            PersonHandler.savePersons(context)
                            passcode.value = ""
                        }, modifier = Modifier
                            .background(Color.Green)
                            .fillMaxWidth()
                    ) {
                        Text(text = "Unlock")
                    }
                    if (showDialog.value) {
                        AlertDialog(
                            onDismissRequest = { showDialog.value = false },
                            title = { Text("Wrong code") },
                            confirmButton = {
                                Button(onClick = { showDialog.value = false }) {
                                    Text("Okay")
                                }
                            }
                        )
                    }
                    if (showDialog.value) {
                        AlertDialog(
                            onDismissRequest = { showDialog.value = false },
                            title = { Text("Alles gelöscht") },
                            confirmButton = {
                                Button(onClick = { showDialog.value = false }) {
                                    Text("Okay")
                                }
                            }
                        )
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                }
            }

            Column {
                Text(text = "Unlocked persons:", fontSize = 25.sp)
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(Persons.unlockedPersons.size) { index ->
                        val person = Persons.unlockedPersons.elementAt(index)
                        val backgroundColor = Colors.colors[index % Colors.colors.size]
                        BoxWithConstraints(
                            modifier = Modifier
                                .fillMaxWidth()
                                .heightIn(max = 450.dp)
                        ) {
                            Button(
                                onClick = {
                                    val intent = Intent(this@PersonSelector, PersonUI::class.java)
                                    Persons.currentPerson.value = person
                                    startActivity(intent)
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight()
                                    .background(backgroundColor)
                            ) {
                                /*   Text(text = person.name, fontSize = 45.sp)*/
                                val personFolderName = person.name.lowercase()
                                val imageAssetPath = "$personFolderName/image.png"
                                val imageBitmap = runCatching {
                                    context.assets.open(imageAssetPath).use {
                                        BitmapFactory.decodeStream(it)
                                    }?.asImageBitmap()
                                }.getOrElse {
                                    // Use a fallback image if there is an exception
                                    null
                                }
                                if (imageBitmap != null) {
                                    Image(bitmap = imageBitmap, contentDescription = person.name)
                                } else {
                                    Text(text = person.name, fontSize = 45.sp)
                                }
                            }
                        }
                    }
                }
            }
        }

    }
}




