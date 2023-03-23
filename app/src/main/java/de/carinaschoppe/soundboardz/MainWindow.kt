package de.carinaschoppe.soundboardz

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.unit.dp
import de.carinaschoppe.soundboardz.ui.theme.SoundboardzTheme

class MainWindow : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoundboardzTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    PersonenHandler.loadPersonen()
                    personenScroller()
                }
            }
        }
        }


        @OptIn(ExperimentalMaterial3Api::class)
        @Composable
       private fun personenScroller() {
            val passcode = remember { mutableStateOf("") }
            val persons = Util.persons.toList()
            val unlockedPersons = Util.unlockedPersons.toMutableList()

            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    TextField(
                        value = passcode.value,
                        onValueChange = { passcode.value = it },
                        label = { Text("Passcode") }
                    )

                    Button(
                        onClick = {
                            val unlocked = persons.firstOrNull { it.passcode == passcode.value }
                            if (unlocked != null) {
                                Util.unlockedPersons.add(unlocked)
                                unlockedPersons.add(unlocked)
                                passcode.value = ""
                                PersonenHandler.savePersonen()
                            }
                        }
                    ) {
                        Text("Unlock")
                    }
                }
            }

            Column {
                Text("Unlocked persons:")
                LazyColumn(
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
                ) {
                    items(Util.unlockedPersons.size) { index ->
                        val person = Util.unlockedPersons.elementAt(index)
                        Button(
                            onClick = {
                                val intent = Intent(this@MainWindow, PersonUI::class.java)
                                Util.currentPerson = person
                                startActivity(intent)
                            },
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            Text(text = person.name)
                        }
                    }
                }
            }

        }
    }




