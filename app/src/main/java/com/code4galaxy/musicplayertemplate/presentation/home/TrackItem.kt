package com.code4galaxy.musicplayertemplate.presentation.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage


/** Displays a single track item containing the track artwork,
 * title, artist name, and a play icon. If [artworkUrl] is available, the track artwork is loaded and displayed.
 * Otherwise, a default music icon is shown as a placeholder.
 * The entire track card is clickable and invokes [onClick] when selected.
 * @param title Title of the track displayed in the item.
 * @param artist Name of the artist associated with the track.
 * @param artworkUrl Optional URL of the track artwork. If null or empty,
 * a default music icon is displayed.
 * @param onClick Callback invoked when the user clicks the track item.
 */
@Composable
fun TrackItem(
    title: String,
    artist: String,
    artworkUrl: String?,
    onClick: () -> Unit,
) {

    Card(
        modifier = Modifier
            .width(280.dp)
            .height(100.dp)
            .clickable {
                onClick()
            },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF2B1B3D)
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        )
    ) {

        Row(
            modifier = Modifier.padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (!artworkUrl.isNullOrEmpty()) {

                AsyncImage(
                    model = artworkUrl,
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(64.dp)
                        .clip(RoundedCornerShape(12.dp))
                )

            } else {

                Card(
                    modifier = Modifier.size(64.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFFBB86FC)
                    )
                ) {

                    Icon(
                        imageVector = Icons.Default.MusicNote,
                        contentDescription = "Track",
                        tint = Color.White,
                        modifier = Modifier
                            .padding(16.dp)
                            .size(32.dp)
                    )
                }
            }

            Spacer(
                modifier = Modifier.width(14.dp)
            )

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(
                    modifier = Modifier.height(4.dp)
                )

                Text(
                    text = artist,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.LightGray,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(
                modifier = Modifier.width(8.dp)
            )

            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = "Play",
                tint = Color(0xFFBB86FC),
                modifier = Modifier.size(30.dp)
            )
        }
    }
}