
package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    var currentArtwork by remember { mutableIntStateOf(0) }

    val images = listOf(
        R.drawable.art_1,
        R.drawable.art_2,
        R.drawable.art_3
    )

    val titles = listOf(
        R.string.artwork_1_title,
        R.string.artwork_2_title,
        R.string.artwork_3_title
    )

    val artists = listOf(
        R.string.artwork_1_artist,
        R.string.artwork_2_artist,
        R.string.artwork_3_artist
    )

    val years = listOf(
        R.string.artwork_1_year,
        R.string.artwork_2_year,
        R.string.artwork_3_year
    )

    BoxWithConstraints(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val isWideScreen = maxWidth > maxHeight

        if (isWideScreen) {
            Row(
                modifier = Modifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                Image(
                    painter = painterResource(images[currentArtwork]),
                    contentDescription = stringResource(titles[currentArtwork]),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .padding(16.dp)
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceEvenly
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(titles[currentArtwork]),
                            fontSize = 24.sp
                        )
                        Text(
                            text = stringResource(artists[currentArtwork]) +
                                    " (" + stringResource(years[currentArtwork]) + ")"
                        )
                    }

                    NavigationButtons(
                        onPrevious = {
                            currentArtwork =
                                (currentArtwork - 1 + images.size) % images.size
                        },
                        onNext = {
                            currentArtwork = (currentArtwork + 1) % images.size
                        }
                    )
                }
            }
        } else {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(images[currentArtwork]),
                    contentDescription = stringResource(titles[currentArtwork]),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                        .padding(16.dp)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(titles[currentArtwork]),
                        fontSize = 24.sp
                    )
                    Text(
                        text = stringResource(artists[currentArtwork]) +
                                " (" + stringResource(years[currentArtwork]) + ")"
                    )
                }

                NavigationButtons(
                    onPrevious = {
                        currentArtwork =
                            (currentArtwork - 1 + images.size) % images.size
                    },
                    onNext = {
                        currentArtwork = (currentArtwork + 1) % images.size
                    }
                )
            }
        }
    }
}

@Composable
fun NavigationButtons(
    onPrevious: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Button(onClick = onPrevious) {
            Text(stringResource(R.string.previous))
        }

        Button(onClick = onNext) {
            Text(stringResource(R.string.next))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ArtSpacePreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}
