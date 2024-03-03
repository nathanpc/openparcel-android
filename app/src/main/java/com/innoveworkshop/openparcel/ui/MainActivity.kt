package com.innoveworkshop.openparcel.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.innoveworkshop.openparcel.R
import com.innoveworkshop.openparcel.model.Carrier
import com.innoveworkshop.openparcel.model.Parcel
import com.innoveworkshop.openparcel.model.ParcelStatus
import com.innoveworkshop.openparcel.model.ParcelUpdate
import com.innoveworkshop.openparcel.ui.content.ParcelCard
import com.innoveworkshop.openparcel.ui.content.ParcelDetailSheet
import com.innoveworkshop.openparcel.ui.theme.AppTheme
import com.innoveworkshop.openparcel.utils.DateUtil
import java.net.URI

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainViewPreview()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainView(parcels: List<Parcel>) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    var showBottomSheet by remember { mutableStateOf(false) }
    var selectedParcel: Parcel? = null

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Image(
                        painter = painterResource(id = R.drawable.ic_logo),
                        contentDescription = "Open Parcel",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onPrimaryContainer)
                    )
                },
                navigationIcon = {
                     IconButton(onClick = { /* TODO: Open navigation drawer. */ }) {
                         Icon(
                             imageVector = Icons.Filled.Menu,
                             contentDescription = stringResource(id = R.string.options),
                             tint = MaterialTheme.colorScheme.onPrimaryContainer
                         )
                     }
                },
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* TODO: Open the Add Parcel screen. */ }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = stringResource(id = R.string.add_parcel)
                )
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            // Parcel cards list.
            items(parcels) { parcel ->
                ParcelCard(
                    parcel = parcel,
                    onClick = {
                        // Opens up the parcel detail bottom sheet.
                        selectedParcel = parcel
                        showBottomSheet = true
                    }
                )
            }
        }

        // Bottom sheet with parcel details.
        ParcelDetailSheet(
            parcel = selectedParcel,
            isVisible = showBottomSheet,
            onDismissRequest = {
                showBottomSheet = false
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainViewPreview() {
    AppTheme {
        MainView(
            parcels = listOf(
                Parcel(
                    id = 0,
                    name = "Macbook Pro (2019)",
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
                            title = "Customs cleared at SOMEWHERE",
                            timestamp = DateUtil.fromISO8601("2024-02-19T07:29:00.000Z"),
                            status = ParcelStatus.CUSTOMS_CLEARED
                        )
                    ),
                    lastUpdated = DateUtil.fromISO8601("2024-02-19T07:29:00.000Z")
                ),
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
                ),
                Parcel(
                    id = 0,
                    name = "Meta Quest 3",
                    delivered = false,
                    carrier = Carrier(
                        id = "dpd-pt",
                        name = "DPD (PT)"
                    ),
                    color = Color(0xFFDC1332).toArgb(),
                    trackingCode = "CA767344619DE",
                    trackingUrl = URI.create("https://www.dhl.com/pt-pt/home/tracking.html?tracking-id=CA767344619DE&submit=1"),
                    trackingHistory = arrayOf(
                        ParcelUpdate(
                            title = "Out for delivery",
                            timestamp = DateUtil.fromISO8601("2024-02-19T07:29:00.000Z"),
                            status = ParcelStatus.DELIVERING
                        )
                    ),
                    lastUpdated = DateUtil.fromISO8601("2024-02-19T07:29:00.000Z")
                )
            )
        )
    }
}
