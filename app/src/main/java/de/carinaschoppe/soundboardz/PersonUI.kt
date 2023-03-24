package de.carinaschoppe.soundboardz

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import de.carinaschoppe.soundboardz.ui.theme.SoundboardzTheme


class PersonUI : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SoundboardzTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    createPerson(person = Util.currentPerson)
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
                Button(
                    onClick = {
                        mediaPlayer.stop()
                        mediaPlayer.reset()
                        Log.d("Soundboardz Audio", "Playing audio file: ${audioFile.audioFile}")
                        val assetFileDescriptor = assets.openFd("${person.name.lowercase()}/${audioFile.audioFile}")
                        Log.d("Soundboardz Audio", "File descriptor: ${assetFileDescriptor}")
                        mediaPlayer.setVolume(3.0f, 3.0f)

// Set the audio stream to the media stream to play the audio through the loud speaker
                        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
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
                        .background(backgroundColor)
                ) {
                    Text(text = audioFile.buttonText)
                }
            }
        }

    }
}


