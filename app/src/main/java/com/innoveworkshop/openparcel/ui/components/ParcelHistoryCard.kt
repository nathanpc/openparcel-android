package com.innoveworkshop.openparcel.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.innoveworkshop.openparcel.R
import com.innoveworkshop.openparcel.examples.parcelExamples
import com.innoveworkshop.openparcel.model.ParcelStatus
import com.innoveworkshop.openparcel.model.ParcelUpdate
import com.innoveworkshop.openparcel.ui.theme.AppTheme
import java.util.Date

/**
 * A card to represent a tracking history update item in a parcel's timeline.
 *
 * @param update Tracking history update item.
 */
@Composable
fun ParcelHistoryCard(
    update: ParcelUpdate,
    modifier: Modifier = Modifier
) {
    update.status.CardTheme {
        Card(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth(1f)
                .then(modifier)
        ) {
            val innerPadding = 12.dp

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth(1f)
            ) {
                // Left-side content.
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .weight(1f)
                ) {
                    Text(
                        text = update.title,
                        style = MaterialTheme.typography.titleMedium
                    )
                    if (update.description != null) {
                        Text(
                            text = update.description,
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }

                    // Bottom information area.
                    Row(
                        modifier = Modifier.padding(top = 12.dp)
                    ) {
                        // Time of day.
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.nest_clock_farsight_analog),
                                contentDescription = null,
                                modifier = Modifier.width(20.dp)
                            )
                            Text(
                                text = update.getTimeString(LocalContext.current),
                                style = MaterialTheme.typography.labelMedium,
                                modifier = Modifier.padding(
                                    start = 4.dp,
                                    end = 16.dp
                                )
                            )
                        }

                        // Location label.
                        if (update.location?.shortLocation() != null) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = ImageVector.vectorResource(id = R.drawable.location_on),
                                    contentDescription = null,
                                    modifier = Modifier.width(20.dp)
                                )
                                Text(
                                    text = update.location.shortLocation()!!,
                                    style = MaterialTheme.typography.labelMedium,
                                    modifier = Modifier.padding(start = 4.dp)
                                )
                            }
                        }
                    }
                }

                // Right-side icon for special statuses.
                if (update.status.importance.level > ParcelStatus.Importance.REGULAR.level) {
                    Column(
                        modifier = Modifier.padding(end = innerPadding)
                    ) {
                        BubbleIcon(
                            imageVector = update.status.getIcon(),
                            surfaceColor = MaterialTheme.colorScheme.surfaceContainerLow
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ParcelHistoryCardPreview() {
    AppTheme {
        LazyColumn {
            items(parcelExamples[0].trackingHistory) { update ->
                ParcelHistoryCard(update = update)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ParcelHistoryCardColorsPreview() {
    AppTheme {
        Column {
            ParcelHistoryCard(
                ParcelUpdate(
                    title = "Regular card",
                    timestamp = Date(),
                    status = ParcelStatus.IN_TRANSIT
                )
            )
            ParcelHistoryCard(
                ParcelUpdate(
                    title = "Created card",
                    timestamp = Date(),
                    status = ParcelStatus.CREATED
                )
            )
            ParcelHistoryCard(
                ParcelUpdate(
                    title = "Posted card",
                    timestamp = Date(),
                    status = ParcelStatus.POSTED
                )
            )
            ParcelHistoryCard(
                ParcelUpdate(
                    title = "Customs cleared card",
                    timestamp = Date(),
                    status = ParcelStatus.CUSTOMS_CLEARED
                )
            )
            ParcelHistoryCard(
                ParcelUpdate(
                    title = "Delivery attempt card",
                    timestamp = Date(),
                    status = ParcelStatus.DELIVERY_ATTEMPT
                )
            )
            ParcelHistoryCard(
                ParcelUpdate(
                    title = "Waiting pickup card",
                    timestamp = Date(),
                    status = ParcelStatus.WAITING_PICKUP
                )
            )
            ParcelHistoryCard(
                ParcelUpdate(
                    title = "Delivering card",
                    timestamp = Date(),
                    status = ParcelStatus.DELIVERING
                )
            )
            ParcelHistoryCard(
                ParcelUpdate(
                    title = "Delivered card",
                    timestamp = Date(),
                    status = ParcelStatus.DELIVERED
                )
            )
            ParcelHistoryCard(
                ParcelUpdate(
                    title = "Issue card",
                    timestamp = Date(),
                    status = ParcelStatus.ISSUE
                )
            )
        }
    }
}
