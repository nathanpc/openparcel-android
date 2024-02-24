package com.innoveworkshop.openparcel.ui.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.innoveworkshop.openparcel.R
import com.innoveworkshop.openparcel.model.Parcel
import com.innoveworkshop.openparcel.model.ParcelUpdate
import com.innoveworkshop.openparcel.ui.components.DetailedParcelProgress
import com.innoveworkshop.openparcel.ui.components.ParcelHistoryCard
import com.innoveworkshop.openparcel.ui.components.SheetActionButton
import com.innoveworkshop.openparcel.ui.components.SheetActionButtonRow
import com.innoveworkshop.openparcel.ui.theme.AppTheme
import com.innoveworkshop.openparcel.utils.DateUtil
import java.util.Calendar
import java.util.Date

/**
 * Detailed view of a parcel object.
 *
 * @param parcel   The parcel to be displayed.
 * @param modifier Component modifier attributes.
 */
@Composable
fun ParcelDetail(parcel: Parcel, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.then(modifier)
    ) {
        // Top section.
        Column {
            // Title section.
            Column {
                // Title
                Text(
                    text = parcel.name,
                    style = MaterialTheme.typography.headlineSmall
                )

                // Subtitle
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Carrier badge.
                    Surface(
                        modifier = Modifier.height(24.dp),
                        color = Color(parcel.surfaceColor()),
                        shape = MaterialTheme.shapes.small
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight(1f)
                                .padding(horizontal = 8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = parcel.carrier.name,
                                style = MaterialTheme.typography.bodySmall,
                                color = Color(parcel.onSurfaceColor())
                            )
                        }
                    }

                    // Tracking code.
                    Text(
                        text = parcel.trackingCode,
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            // Pretty progress bar.
            DetailedParcelProgress(
                parcel = parcel,
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(top = 12.dp)
            )

            // Last updated label.
            Text(
                text = "Updated " + DateUtil.getRelativeTimeString(parcel.lastUpdated),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth(1f)
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(
                top = 12.dp,
                start = 16.dp,
                end = 16.dp
            )
        )

        // Action buttons.
        SheetActionButtonRow {
            SheetActionButton(
                text = "Edit",
                icon = ImageVector.vectorResource(id = R.drawable.edit),
                onClick = { /* TODO: Do something. */ }
            )
            SheetActionButton(
                text = "Report issue",
                icon = ImageVector.vectorResource(id = R.drawable.report),
                onClick = { /* TODO: Do something. */ }
            )
            SheetActionButton(
                text = "Refresh",
                icon = ImageVector.vectorResource(id = R.drawable.refresh),
                onClick = { /* TODO: Do something. */ }
            )
            SheetActionButton(
                text = "Open in browser",
                icon = ImageVector.vectorResource(id = R.drawable.open_in_browser),
                onClick = { /* TODO: Do something. */ }
            )
        }

        HorizontalDivider(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                bottom = 12.dp
            )
        )

        // Timeline
        LazyColumn(
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            // Keep track of the last date in order to know when to insert date headers.
            val calendar: Calendar = Calendar.getInstance()
            calendar.time = parcel.trackingHistory[0].timestamp

            // Ensure the first date header is created and that they are properly padded.
            calendar.add(Calendar.YEAR, 1)
            var headerPadding = 0.dp

            // Build up the list.
            items(parcel.trackingHistory) { update ->
                // Decide if a new date header is needed.
                if (DateUtil.isDateDifferent(calendar, update.timestamp)) {
                    TimelineDateHeader(
                        update = update,
                        modifier = Modifier.padding(top = headerPadding)
                    )

                    calendar.time = update.timestamp
                    headerPadding = 20.dp
                }

                // History update card.
                ParcelHistoryCard(update)
            }
        }
    }
}

/**
 * Big header with the date and month. Used to group cards by day.
 *
 * @param update   Tracking history update item.
 * @param modifier Component modifier attributes.
 */
@Composable
fun TimelineDateHeader(
    update: ParcelUpdate,
    modifier: Modifier = Modifier
) {
    Text (
        text = update.getDayMonthString(LocalContext.current),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.then(modifier)
    )
}

@Preview(showBackground = true)
@Composable
fun ParcelDetailPreview() {
    AppTheme {
        ParcelDetail(
            Parcel.fromJson(
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
                  "timestamp": "2024-02-19T04:13:00.000Z",
                  "title": "Arrival in the destination country/destination area"
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
                  "description": null,
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
        )
    }
}
