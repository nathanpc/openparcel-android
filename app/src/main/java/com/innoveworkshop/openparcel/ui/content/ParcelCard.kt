package com.innoveworkshop.openparcel.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.innoveworkshop.openparcel.examples.parcelExamples
import com.innoveworkshop.openparcel.model.Parcel
import com.innoveworkshop.openparcel.ui.components.ParcelUpdateBubbleIcon
import com.innoveworkshop.openparcel.ui.components.SimpleParcelProgress
import com.innoveworkshop.openparcel.ui.theme.AppTheme
import com.innoveworkshop.openparcel.utils.DateUtil.Companion.relativeTimeString

/**
 * Summary card representation of a parcel.
 *
 * @param parcel   The parcel to be displayed.
 * @param modifier Component modifier attributes.
 */
@Composable
fun ParcelCard(
    parcel: Parcel,
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {}
) {
    val lastParcelUpdate = parcel.trackingHistory[0]

    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = Color(parcel.surfaceColor())
        ),
        modifier = Modifier
            .padding(8.dp)
            .then(modifier)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            // Heading.
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

            // Shows the progress of the parcel through the system so far.
            SimpleParcelProgress(
                parcel = parcel,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp,
                        bottom = 16.dp
                    )
            )

            // Last update information.
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                ParcelUpdateBubbleIcon(
                    parcel = parcel,
                    update = lastParcelUpdate,
                    modifier = Modifier.padding(end = 12.dp)
                )

                Column(
                    modifier = Modifier.alignByBaseline()
                ) {
                    Text(
                        text = lastParcelUpdate.title,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = lastParcelUpdate.timestamp.relativeTimeString,
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
    AppTheme {
        LazyColumn {
            items(parcelExamples) { ParcelCard(it) }
        }
    }
}
