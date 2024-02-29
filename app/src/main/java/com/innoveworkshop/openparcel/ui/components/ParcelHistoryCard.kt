package com.innoveworkshop.openparcel.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import com.innoveworkshop.openparcel.model.Parcel
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
fun ParcelHistoryCardPreview() {
    val parcel = Parcel.fromJson(
        """
            {
              "accentColor": "#FFCC00",
              "cached": true,
              "carrier": {
                "id": "dhl",
                "name": "DHL"
              },
              "creationDate": null,
              "destination": {
                "addressLine": null,
                "city": null,
                "coords": {
                  "lat": null,
                  "lng": null
                },
                "country": "Portugal",
                "postalCode": null,
                "searchQuery": "Portugal",
                "state": null
              },
              "history": [
                {
                  "description": "Some example of a lengthy description to show.",
                  "location": {
                    "addressLine": null,
                    "city": null,
                    "coords": {
                      "lat": null,
                      "lng": null
                    },
                    "country": "Portugal",
                    "postalCode": null,
                    "searchQuery": "Portugal",
                    "state": null
                  },
                  "status": {
                    "data": {
                      "description": "Delivery successful",
                      "to": null
                    },
                    "type": "delivered"
                  },
                  "timestamp": "2024-02-19T11:02:00.000Z",
                  "title": "Delivery successful"
                },
                {
                  "description": null,
                  "location": {
                    "addressLine": null,
                    "city": null,
                    "coords": {
                      "lat": null,
                      "lng": null
                    },
                    "country": "Portugal",
                    "postalCode": null,
                    "searchQuery": "Portugal",
                    "state": null
                  },
                  "status": {
                    "data": {
                      "description": "Being delivered"
                    },
                    "type": "delivering"
                  },
                  "timestamp": "2024-02-19T08:41:00.000Z",
                  "title": "Being delivered"
                },
                {
                  "description": null,
                  "location": {
                    "addressLine": null,
                    "city": null,
                    "coords": {
                      "lat": null,
                      "lng": null
                    },
                    "country": "Portugal",
                    "postalCode": null,
                    "searchQuery": "Portugal",
                    "state": null
                  },
                  "status": null,
                  "timestamp": "2024-02-19T07:29:00.000Z",
                  "title": "Shipment processed at delivery depot"
                },
                {
                  "description": "Some example of a lengthy description to show.",
                  "location": {
                    "addressLine": null,
                    "city": null,
                    "coords": {
                      "lat": null,
                      "lng": null
                    },
                    "country": "Portugal",
                    "postalCode": null,
                    "searchQuery": "Portugal",
                    "state": null
                  },
                  "status": null,
                  "timestamp": "2024-02-19T04:13:00.000Z",
                  "title": "Arrival in the destination country/destination area"
                },
                {
                  "description": "Very small description.",
                  "location": {
                    "addressLine": null,
                    "city": null,
                    "coords": {
                      "lat": null,
                      "lng": null
                    },
                    "country": "Germany",
                    "postalCode": null,
                    "searchQuery": "Germany",
                    "state": null
                  },
                  "status": null,
                  "timestamp": "2024-02-16T08:24:00.000Z",
                  "title": "Export parcel center"
                },
                {
                  "description": "Some example of a very very very very lengthy description to show.",
                  "location": {
                    "addressLine": null,
                    "city": null,
                    "coords": {
                      "lat": null,
                      "lng": null
                    },
                    "country": "Germany",
                    "postalCode": null,
                    "searchQuery": "Germany",
                    "state": null
                  },
                  "status": null,
                  "timestamp": "2024-02-14T19:40:00.000Z",
                  "title": "Parcel center of origin"
                },
                {
                  "description": null,
                  "location": {
                    "addressLine": null,
                    "city": null,
                    "coords": {
                      "lat": null,
                      "lng": null
                    },
                    "country": "",
                    "postalCode": null,
                    "searchQuery": null,
                    "state": null
                  },
                  "status": {
                    "data": {
                      "description": "Order data transmitted electronically",
                      "timestamp": null
                    },
                    "type": "created"
                  },
                  "timestamp": "2024-02-14T15:31:00.000Z",
                  "title": "Order data transmitted electronically"
                }
              ],
              "id": 8,
              "lastUpdated": "2024-02-21T14:30:37.763294+00:00",
              "name": "Panasonic Lumix DMC-GF1",
              "origin": {
                "addressLine": null,
                "city": null,
                "coords": {
                  "lat": null,
                  "lng": null
                },
                "country": "Germany",
                "postalCode": null,
                "searchQuery": "Germany",
                "state": null
              },
              "status": {
                "data": {
                  "description": "Delivery successful",
                  "to": null
                },
                "type": "delivered"
              },
              "trackingCode": "CA767344619DE",
              "trackingUrl": "https://www.dhl.com/us-en/home/tracking/tracking-parcel.html?submit=1&tracking-id=CA767344619DE"
            }
        """.trimIndent()
    )
    
    AppTheme {
        LazyColumn {
            items(parcel.trackingHistory) { update ->
                ParcelHistoryCard(update = update)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParcelHistoryCardColorsPreview() {
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
