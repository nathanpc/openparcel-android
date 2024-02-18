package com.innoveworkshop.openparcel.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.innoveworkshop.openparcel.model.Parcel
import com.innoveworkshop.openparcel.model.ParcelUpdate
import java.net.URI

@Composable
fun ParcelCard(parcel: Parcel) {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = Color(parcel.surfaceColor())
        ),
        modifier = Modifier.padding(8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Column {
                Text(
                    text = parcel.name,
                    style = MaterialTheme.typography.titleLarge
                )
                Text(
                    text = parcel.trackingCode,
                    style = MaterialTheme.typography.labelMedium
                )
            }

            LinearProgressIndicator(
                progress = .3f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp,
                        bottom = 16.dp
                    ),
                color = Color(parcel.onSurfaceColor()),
                trackColor = Color(parcel.onSurfaceDividerColor()),
                strokeCap = StrokeCap.Round
            )

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Surface(
                    modifier = Modifier.padding(end = 8.dp),
                    color = Color(parcel.onSurfaceDividerColor()),
                    shape = RoundedCornerShape(50)
                ) {
                    Icon(
                        imageVector = Icons.Rounded.MailOutline,
                        contentDescription = "In transit",
                        tint = Color(parcel.onSurfaceColor()),
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Column (
                    modifier = Modifier.alignByBaseline()
                ) {
                    Text(
                        text = parcel.trackingHistory[parcel.trackingHistory.lastIndex].title,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "2 days ago",
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParcelCardPreview() {
    ParcelCard(
        Parcel(
            name = "Panasonic Lumix DMC-GF1",
            delivered = false,
            color = Color(0xFFDE0024).toArgb(),
            trackingCode = "CA767344619DE",
            trackingUrl = URI.create("https://www.dhl.com/pt-pt/home/tracking.html?tracking-id=CA767344619DE&submit=1"),
            trackingHistory = arrayOf(
                ParcelUpdate(
                    title = "Order data transmitted electronically"
                )
            )
        )
    )
}
