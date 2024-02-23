package com.innoveworkshop.openparcel.ui.content

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.innoveworkshop.openparcel.model.Carrier
import com.innoveworkshop.openparcel.model.Parcel
import com.innoveworkshop.openparcel.model.ParcelStatus
import com.innoveworkshop.openparcel.model.ParcelUpdate
import com.innoveworkshop.openparcel.ui.components.SimpleParcelProgress
import com.innoveworkshop.openparcel.utils.DateUtil
import java.net.URI

/**
 * Summary card representation of a parcel.
 *
 * @param parcel   The parcel to be displayed.
 * @param modifier Component modifier attributes.
 */
@Composable
fun ParcelCard(parcel: Parcel, modifier: Modifier = Modifier) {
    val lastParcelUpdate = parcel.trackingHistory[0]

    Card(
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

            SimpleParcelProgress(
                parcel = parcel,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 8.dp,
                        bottom = 16.dp
                    )
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
                        imageVector = lastParcelUpdate.status.getIcon(),
                        contentDescription = lastParcelUpdate.status.label,
                        tint = Color(parcel.onSurfaceColor()),
                        modifier = Modifier.padding(8.dp)
                    )
                }

                Column(
                    modifier = Modifier.alignByBaseline()
                ) {
                    Text(
                        text = lastParcelUpdate.title,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = DateUtil.getRelativeTimeString(lastParcelUpdate.timestamp),
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParcelCardManualPreview() {
    ParcelCard(
        Parcel(
            id = 0,
            name = "Panasonic Lumix DMC-GF1",
            delivered = false,
            carrier = Carrier(
                id = "ctt",
                name = "CTT"
            ),
            color = Color(0xFFDE0024).toArgb(),
            trackingCode = "CA767344619DE",
            trackingUrl = URI.create("https://www.dhl.com/pt-pt/home/tracking.html?tracking-id=CA767344619DE&submit=1"),
            trackingHistory = arrayOf(
                ParcelUpdate(
                    title = "Order data transmitted electronically",
                    timestamp = DateUtil.fromISO8601("2024-02-19T07:29:00.000Z"),
                    status = ParcelStatus.CREATED
                )
            ),
            lastUpdated = DateUtil.fromISO8601("2024-02-19T07:29:00.000Z")
        )
    )
}

@Preview(showBackground = true)
@Composable
fun ParcelCardJsonPreview() {
    ParcelCard(
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
