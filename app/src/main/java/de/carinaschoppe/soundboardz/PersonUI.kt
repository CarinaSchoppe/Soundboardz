package de.carinaschoppe.soundboardz

import android.media.AudioAttributes
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import de.carinaschoppe.soundboardz.ui.theme.SoundboardzTheme


class PersonUI : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoundboardzTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    createPerson(person = Persons.currentPerson.value!!)
                }
            }
        }
    }


    @Composable
    private fun createPerson(person: Person) {
        //add a scrollable list of the buttons in different colors
        //add a button to add a new button
        var mediaPlayer = remember {
            MediaPlayer()
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .verticalScroll(rememberScrollState())
        ) {
            for ((index, audioFile) in person.audioButtons.withIndex()) {
                val backgroundColor = Colors.colors[index % Colors.colors.size]

                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 450.dp)
                ) {
                    Button(
                        onClick = {
                            mediaPlayer.stop()
                            mediaPlayer.reset()
                            Log.d("Soundboardz Audio", "Playing audio file: ${audioFile.audioFile}")
                            val assetFileDescriptor = assets.openFd("${person.name.lowercase()}/${audioFile.audioFile}")
                            Log.d("Soundboardz Audio", "File descriptor: ${assetFileDescriptor}")
                            mediaPlayer.setVolume(1.0f, 1.0f)

// Set the audio stream to the media stream to play the audio through the loud speaker
                            val audioAttributes = AudioAttributes.Builder()
                                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                                .setUsage(AudioAttributes.USAGE_MEDIA)
                                .build()

                            mediaPlayer.setAudioAttributes(audioAttributes)
                            mediaPlayer.setDataSource(
                                assetFileDescriptor.fileDescriptor,
                                assetFileDescriptor.startOffset,
                                assetFileDescriptor.length
                            )
                            mediaPlayer.prepare()
                            mediaPlayer.start()
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .background(backgroundColor)

                    ) {
                        Text(text = audioFile.buttonText, fontSize = 40.sp)
                    }
                }
            }
        }

    }
}


